package net;

import base.IO.log.Log;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 由于等待回复都是线程阻塞式的监听
 * 请使用start()开始并发线程的服务连接工作
 *
 * Created by zyvis on 2017/7/21.
 */
public class SocketThread extends Thread  {
    private SocketTrans socketTrans;
    private Queue<String> queue= new LinkedList<>();

    /**
     * 读取的String对象，会先读入queue对象
     * 通过该方法，抽取出queue的poll方法。
     * 按队列顺序读取
     *
     * @return 顺序的读取内容
     */
    public String fetch(){
        try {
            return queue.poll();
        }catch(NullPointerException e){
            Log.e("no msg in queue, queue length : "+queue.size(),e);
            return null;
        }
    }

    /**
     * 监听模式，等待到此的连接
     * 监听模式下，不会因为远程断开连接而结束
     * 会持续监听，收到信息会继续打印出来
     *
     * 读取的内容保存在该线程附带的一个Queue中
     * 通过fetch()方法读取
     *
     * @param port      监听端口
     */
    public SocketThread(int port) {
        socketTrans=new SocketTrans(port);
        Log.d("SocketThread listen : "+port);
    }

    /**
     * 主动模式，连接到远程的服务器
     * 主动模式下，如果远程服务器关闭端口，该线程直接结束
     *
     * 同时指定一个命令转换器，所有读到的String会调用
     * CommandTransable的接口。实现了该功能的转换器都可以重定向输出
     *
     * @param address   远程服务器地址
     * @param port      服务器端口
     */
    public SocketThread(String address,int port){
        socketTrans=new SocketTrans(address,port);
        Log.d("SocketThread link : "+address+":"+port);
    }

    @Override
    public void run() {
        super.run();
        for(;;) {
            try {
                queue.add(socketTrans.getData());
            } catch (IOException e) {
                Log.e("please reinstall this obj ",e);
                break;
            } catch (NullPointerException e1){
                continue;
            }
        }
    }

    public void disconnect(){
        socketTrans.disconnect();
    }
}
