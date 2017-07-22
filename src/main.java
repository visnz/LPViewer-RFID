import base.IO.log.PrintLogcat;
import core.VisntoolCore;
import core.installException;
import io.ImageManager;
import visual.dialog.IMdialog;

import java.io.IOException;

/**
 * Created by zyvis on 2017/7/21.
 */
public class main {
    public static void main(String[] args) {
        try {
            VisntoolCore.install();
            VisntoolCore.setPrintLog(PrintLogcat.defaultFilepath);
        } catch (installException e) {
            e.printStackTrace();
        }

//        new MainWindow();
        ImageManager imageManager=new ImageManager();
        try {
            imageManager.importImage("actor","src/io/imgsrc/actor.png");
            imageManager.importImage("base","src/io/imgsrc/base.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }



        new IMdialog(imageManager);

//        ImplCmdTranslator implCmdTranslator=new ImplCmdTranslator();
//        SocketThread socketThread=new SocketThread(10086);
//        socketThread.start();
//
//        for(;;){
//            try {
//                Thread.sleep(1000);
//                Log.i(implCmdTranslator.getData(socketThread.fetch()).toString());
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (NullPointerException e){
//                continue;
//            }
//        }

    }
}
