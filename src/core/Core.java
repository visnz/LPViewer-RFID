package core;

import base.IO.log.Log;
import cmd.CommandRunnable;
import cmd.CommandTransable;
import cmd.ImplCmdRunner;
import cmd.ImplCmdTranslator;
import io.ImageManager;
import net.SocketThread;
import visual.MainWindow;

import java.awt.*;
import java.io.IOException;

/**
 * Created by zyvis on 2017/7/22.
 */
public class Core extends Thread {
    public static ImageManager getGlobalImageManager() {
        return GlobalIM;
    }

    public static MainWindow getMainWindow() {
        return mainWindow;
    }
    private static SocketThread socketThread;
    private static ImageManager GlobalIM;
    private static MainWindow mainWindow;
    private static CommandRunnable cmdRunner;
    private static CommandTransable cmdTranslator;
    private static SocketDataToPaintThread socketDataToPaintThread;
    private static int duration=1000/2;

    public static String getDuration() {
        return String.valueOf(duration);
    }

    public static void setDuration(int duration) {
        Core.duration = duration;
    }

    @Override
    public void run() {
        super.run();
        GlobalIM= new ImageManager();
        mainWindow=new MainWindow(new Dimension(1024,768));
        cmdRunner=new ImplCmdRunner(mainWindow);
        cmdTranslator=new ImplCmdTranslator();
        installData();
    }

    public void installData(){
        try {
            GlobalIM.importImage("src/io/imgsrc/actor.png");
            GlobalIM.importImage("src/io/imgsrc/base.jpg");
            GlobalIM.importImage("src/io/imgsrc/tag.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAbout() {
        return "<html>Location Project Result Print Window by visnz <br><br> Feedback to visn0518@gmail.com <br> scr https://github.com/visnz/LPViewer-RFID.git</html>";
    }
    public static void startSocket(int port){
        if(socketThread!=null)disconnect();
        socketThread=new SocketThread(port);
        socketDataToPaintThread=new SocketDataToPaintThread();
        socketThread.start();
        socketDataToPaintThread.start();
    }
    public static void startSocket(String address,int port){
        if(socketThread!=null)disconnect();
        socketThread=new SocketThread(address,port);
        socketDataToPaintThread=new SocketDataToPaintThread();
        socketThread.start();
        socketDataToPaintThread.start();
    }
    public static void disconnect(){
        socketThread.disconnect();
    }
    public static class SocketDataToPaintThread extends Thread{
        @Override
        public void run() {
            boolean mention=true;
            super.run();
            for(;;){
                try {
                    Thread.sleep(duration);
                    cmdRunner.run(cmdTranslator.getData(socketThread.fetch()));
                    mention=true;
                } catch (InterruptedException e) {
                    Log.e("Thread exception ",e);
                } catch (NullPointerException e1){
                    if(mention){
                        Log.i("waiting for data");
                        mention=false;
                    }
                }
            }
        }
    }
}
