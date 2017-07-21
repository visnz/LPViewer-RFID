package ojbs;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 相对于抽象的对象只拥有位置和描述信息
 * 具体对象拥有图像和图像大小
 * Created by zyvis on 2017/7/21.
 */
public class Actor extends Obj {

    /**
     * 通过改变image来改变framesize的大小
     */
    private BufferedImage image;
    private Dimension framesize;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.framesize=new Dimension(image.getWidth(),image.getHeight());
    }

    public Actor(Point waypoint, String description, BufferedImage image) {
        super(waypoint, description);
        this.image = image;
        this.framesize=new Dimension(image.getWidth(),image.getHeight());
    }

    public Dimension getFramesize() {
        return framesize;
    }
}
