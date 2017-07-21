package cmd;

/**
 * 因为读取了String的对象不负责处理该对象
 * 故专门用一个接口，把String引流到专门用于转换命令的对象
 * 从而完成对原本输入流的对象层面重定向
 *
 * 此接口在SocketThread中调用
 * 实现该方法，并传入到SocketThread的构建器中
 * 进行数据的重定向，降低模块耦合
 *
 * Created by zyvis on 2017/7/21.
 */
public interface CommandTransable {
    /**
     * 此类用于完成从接受的数据String到内部Command对象的转换
     *
     * @param originCommand 原命令
     * @return              返回可以被识别的命令
     */
    Command getData(String originCommand);
}
