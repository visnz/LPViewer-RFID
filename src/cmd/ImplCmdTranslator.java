package cmd;

import java.awt.*;

/**
 * 此类同时完成翻译、运行的功能
 * 同时包含一个对于操作对象的引用
 * 直接由CommandRunnable接口调用
 *
 * Created by zyvis on 2017/7/21.
 */
public class ImplCmdTranslator implements CommandTransable{
    @Override
    public Command getData(String originCommand) {
        String[] tmp=originCommand.split(" ",2);
        switch (tmp.length){
            case 1:{
                switch (tmp[0]) {
                    case Command.END:
                        return new Command(Command.END);
                    case Command.CLEAR:
                        return new Command(Command.CLEAR);
                    default:
                        return Command.NULL;
                }
            }
            case 2:{
                String action;
                switch (tmp[0]) {
                    case Command.SETACTOR:{
                        action=Command.SETACTOR;
                        break;
                    }case Command.SETTAG:{
                        action=Command.SETTAG;
                        break;
                    }default: {
                        action = null;
                    }
            }
        }
        return Command.NULL;
    }


    public Point getParam(String point){
       String[] tmp=point.split(",",2);
       return new Point(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]));
    }

}
