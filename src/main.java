import base.IO.log.Log;
import base.IO.log.PrintLogcat;
import cmd.ImplCmdTranslator;
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


//        ImplCmdTranslator implCmdTranslator=new ImplCmdTranslator();
//        SocketThread socketThread=new SocketThread(10086);
//        socketThread.start();
//
//        for(;;){
//            try {
//                Thread.sleep(1000);
//                implCmdTranslator.getData(socketThread.fetch());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (NullPointerException e){
//                continue;
//            }
//        }

    }
}
