package visual;

import java.awt.*;

/**
 * Created by zyvis on 2017/7/22.
 */
public final class Tool {
    public static Point getScreenMidPoint(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(screenSize.width/2,screenSize.height/2);
    }

    /**
     * 其中包含了对于Component对象的调用，并设置它的初始点
     * 调用该方法之前先调用pack()获取自适应的大小
     *
     * @param component
     */
    public static void MidPlay(Component component){

        Dimension frameSize=new Dimension(component.getWidth(),component.getWidth());
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        component.setBounds((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2,frameSize.width,frameSize.height);
        //component.setVisible(true);
    }
}
