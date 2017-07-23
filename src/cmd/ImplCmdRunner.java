package cmd;

import base.IO.log.Log;
import core.Core;
import ojbs.Actor;
import ojbs.Particle;
import visual.MainWindow;


/**
 * Created by zyvis on 2017/7/22.
 */
public class ImplCmdRunner implements CommandRunnable {
    MainWindow mainWindow;
    @Override
    public void run(Command command) {
        Log.d("Run command:"+command.toString());
        if(command.equals(Command.NULL))return;
        switch (command.getParamType()){
            case None:
            case Single:{
                switch (command.getAction()) {
                    case Command.SINGLE_WAIT: {
                        try {
                            Thread.sleep(command.getParam_single());
                        } catch (InterruptedException e) {
                            Log.e("Thread sleep failed ", e);
                        }
                        break;
                    }
                }
            }
            case Pair:{
                switch (command.getAction()) {
                    case Command.PAIR_SETACTOR:{
                        mainWindow.draw(new Particle(command.getParam_pair(), Core.getGlobalImageManager().get("actor")));
                        break;
                    }
                    case Command.PAIR_SETTAG:{
                        mainWindow.draw(new Actor(command.getParam_pair(), Core.getGlobalImageManager().get("tag")));
                        break;

                    }
                }
            }
            case String:
            case Others:
        }
    }

    public ImplCmdRunner(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
