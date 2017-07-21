package net;

import base.IO.log.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static net.SocketTrans.Mode.Client;
import static net.SocketTrans.Mode.Server;

/**
 * 类中封装了一个Socket、Scanner、ServerSocket
 * 提供主从两种连接模式：
 * A：直接由构造器构造IP和Port，连接到远程ServerSocket
 *      {@link #SocketTrans(String, int)}
 * B：构建一个本机地址的端口监听器，获得远程socket
 *      {@link #SocketTrans(int)}
 *
 * 使用{@link #getData()}获取数据
 *
 * B范例代码
 *  SocketTrans s=new SocketTrans(10086);
 *  while(true) {
 *    try {
 *      System.out.println(s.getData());
 *    } catch (IOException e) {
 *      Log.e("please reinstall this obj ",e);
 *      break;
 *    }
 *  }
 *
 *
 * B远程端代码
 * PrintStream p=new PrintStream(socket.getOutputStream());
 * p.println(temp);
 *
 * Created by zyvis on 2017/4/10.
 */

public class SocketTrans {
    protected enum Mode{
        Client, Server;
    }
    private Socket insideSocket;
    private Scanner scanner;
    private ServerSocket insideListener;
    private Mode mode;

    /**
     * 此构造器调用其中的socket主动连接到远程的serversocket
     *
     * @param IP    IP address
     * @param port  port
     */
    public SocketTrans(String IP, int port) {

        mode=Client;
        try {
            insideSocket=new Socket(IP,port);
            Log.d("connection successfully : "+IP.toString()+":"+port);
        } catch (IOException e) {
            Log.e("link to remote host failed",e);
        }
    }

    /**
     * 构造器创建本地网络的serversocket监听指定端口
     * 构建的时候包含了一个阻塞线程的方法accept()
     * 建议将使用该构造的对象放于start的线程
     *
     * @param listenPort    监听端口
     */
    public SocketTrans(int listenPort) {
        mode=Server;
        try{
            insideListener=new ServerSocket(listenPort);
            Log.d("listen successfully : "+listenPort);
            //这一块会引发线程阻塞
        }catch (IOException e){
            Log.e("listen socket failed",e);
        }
    }

    public String getData() throws IOException{
        /**
         * 监听模式，如果socket没有连接的话
         * 循环监听，检测到socket，直到连接则断开，进行数据读取
         */
        if(mode==Server&&insideSocket==null) {
            while(true) {
                try {
                    insideSocket = insideListener.accept();
                    //当ss接收到对象连接时候，赋予s
                    Log.d("listener accept the socket: "+insideSocket.getInetAddress()+":"+insideSocket.getPort());
                    if (insideSocket == null) continue;
                    scanner = new Scanner(insideSocket.getInputStream());
                    Log.d("scanner installed");
                    break;
                } catch (IOException e) {
                    Log.e("listener haven't accept the socket", e);
                }
            }
        }else getScannerFromSocket();
        /**
         * 等待数据录入，尝试读取，此处为单线程阻塞
         * 如果连接中断
         */
        try {
            String tmp = scanner.nextLine();
            Log.i(" Data acquire : " + tmp);
            return tmp;
        }catch(NoSuchElementException e){
            Log.e("connection break",e);
            insideSocket=null;
            if(mode==Client)throw new IOException("remote host close the connection");
        }catch (NullPointerException e){
            Log.e("scanner not found",e);
            if(mode==Client)throw new IOException("remote host close the connection");
        }
        return null;
    }

    /**
     * 内部对于serversocket接受socket的线程阻塞式等待
     * 建议用并发start()运行
     */
    private void getAcceptSocket(){
        if(insideSocket==null){
            try{
                insideSocket=insideListener.accept();
                //这一块会引发线程阻塞
                Log.d("listener accept the socket");
            } catch (IOException e) {
                Log.e("listener haven't accept the socket",e);
            }
        }else return;
    }

    /**
     * 从socket中获取scanner
     * 检查scanner对象为空的时候会运行，
     * 每次getData都会检查一遍
     */
    private void getScannerFromSocket(){
        if(scanner!=null)return;
        try {
            scanner=new Scanner(insideSocket.getInputStream());
            Log.d("load scanner from socket ready");
        } catch (IOException e) {
            Log.e("couldn't load scanner from socket",e);
        } catch (NullPointerException e1){
            Log.e("socket is null",e1);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }
}
