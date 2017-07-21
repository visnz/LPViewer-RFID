package cmd;

/**
 * 由CommandTransable完成从String到Command的翻译
 * 翻译成Command后，可以调用这个接口进行运行
 * 通常把两个接口套在一起用
 *
 * Created by zyvis on 2017/7/21.
 */
public interface CommandRunnable {
    void run(Command command);
}
