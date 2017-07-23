package cmd;

import java.awt.*;

/**
 * 此类用于解释单条命令和参数
 * 传输到表达层
 *
 * 传输协议在同文件夹下的InteractiveProtocol.md中
 *
 *
 * Created by zyvis on 2017/7/21.
 *
 * 拥有最基础的指令集，可以通过扩展子类扩展指令
 * @version 1.0
 */
public class Command {


    public enum Parameter{
        None,
        Single,
        Pair,
        String,
        Others
    }
    public static final Command NULL = new Command(null);
    public static final String PAIR_SETACTOR ="setactor";
    public static final String PAIR_SETTAG ="settag";
    public static final String SINGLE_WAIT ="wait";

    public String getAction() {
        return action;
    }

    /**
     * 此String指定了行为
     */
    private String action;


    private Parameter parameter;
    private Integer param_single;
    private Point param_pair;
    private String param_string;

    public Integer getParam_single() {
        return param_single;
    }

    public Point getParam_pair() {
        return param_pair;
    }

    public String getParam_string() {
        return param_string;
    }

    public Object getParam_others() {
        return param_others;
    }

    private Object param_others;

    public Parameter getParamType() {
        return parameter;
    }
    public Command(String action) {
        this.action = action;
        parameter=Parameter.None;
    }

    public Command(String action, Integer param_single){
        this.action = action;
        this.param_single=param_single;
        parameter=Parameter.Single;
    }

    public Command(String action, Point param_pair) {
        this.action = action;
        this.param_pair = param_pair;
        parameter=Parameter.Pair;
    }

    public Command(String action, String param_string) {
        this.action = action;
        this.param_string = param_string;
        parameter=Parameter.String;
    }

    public Command(String action, Object param_others) {
        this.action = action;
        this.param_others = param_others;
        parameter=Parameter.Others;
    }

    @Override
    public String toString() {
        return "Command{" +
                "action='" + action + '\'' +
                (param_single==null?"":(", param_single=" + param_single)) +
                (param_pair==null?"":(", param_pair=" + param_pair)) +
                (param_string==null?"":(", param_string='" + param_string + '\'')) +
                (param_others==null?"":(", param_others=" + param_others)) +
                '}';
    }
}
