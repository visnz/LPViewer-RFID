package ojbs;

import java.awt.*;

/**
 * 此类提供了抽象的绘图对象，指明了大小和描述
 * 通过实现具体的对象进行绘图
 * Created by zyvis on 2017/7/21.
 */
public abstract class Obj {
    private Point waypoint;
    private String description;

    public Obj(Point waypoint, String description) {
        this.waypoint = waypoint;
        this.description = description;
    }

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

    public String getDescription() {
        if(description==null)return "";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
