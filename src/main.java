import base.IO.log.PrintLogcat;
import core.Core;
import core.VisntoolCore;
import core.installException;

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


        new Core().start();



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
