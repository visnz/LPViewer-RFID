package cmd;

import base.IO.log.Log;

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
        try{
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
                String action=tmp[0];
                switch (action) {
                    case Command.SETACTOR:
                        break;
                    case Command.SETTAG:
                        break;
                    case Command.WAIT:
                        break;
                    default: {
                        return Command.NULL;
                    }
                }
                if(tmp[1].contains(","))
                    return new Command(action,getParam(tmp[1]));
                else return new Command(action,Integer.parseInt(tmp[1]));
            }
        }
        }catch (NumberFormatException e){
            Log.e("unexpected command",e);
        }
        return Command.NULL;
    }


    public Point getParam(String point){
       String[] tmp=point.split(",",2);
       return new Point(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]));
    }

}
