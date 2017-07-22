package core;

import io.ImageManager;
import visual.MainWindow;

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

    private static ImageManager GlobalIM;
    private static MainWindow mainWindow;

    @Override
    public void run() {
        super.run();
        GlobalIM= new ImageManager();
        mainWindow=new MainWindow();

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
}
