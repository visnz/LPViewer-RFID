package ojbs;

import java.awt.*;

/**
 * 此类提供了抽象的绘图对象，指明了位置
 * 通过实现具体的对象进行绘图
 * Created by zyvis on 2017/7/21.
 */
public abstract class Obj {
    private Point waypoint;

    public Obj(Point waypoint) {
        this.waypoint = waypoint;
    }


    public Point getWaypoint() {
        return waypoint;
    }

    public int getX(){
        return waypoint.x;
    }

    public int getY(){
        return waypoint.y;
    }

    public void setWaypoint(Point waypoint) {
        this.waypoint = waypoint;
    }
}
