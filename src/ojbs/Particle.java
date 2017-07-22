package ojbs;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 以粒子的形式命名，其中多了一个属性为生命（狭义应用上可以理解为透明度）
 * 默认description为null，不再提供描述。
 * Created by zyvis on 2017/7/22.
 */
public class Particle extends Actor {
    private int age=255;
    public Particle(Point waypoint, BufferedImage image) {
        super(waypoint,  image);
    }
}
