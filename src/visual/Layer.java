package visual;

import base.IO.log.Log;
import ojbs.Actor;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by zyvis on 2017/7/22.
 */
public class Layer<T extends Actor> extends JPanel {
    public LinkedList<T> getObjlist() {
        return objlist;
    }

    private LinkedList<T> objlist;

    public Point getOffset() {
        return offset;
    }

    //private Iterator<T> iterator;
    private Point offset;

    public Layer() {
        objlist =new LinkedList<>();
    }

    public void drawNewObj(Actor actor){
        objlist.add((T)actor);
        Log.d("layer add new obj, now obj: "+objlist.size());
        if(objlist.size()==1){
            offset=new Point(objlist.get(0).getRenderPointX(),objlist.get(0).getRenderPointY());
        }
        //repaint();
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        iterator=objlist.iterator();
//        Actor tmpActor;
//        while(iterator.hasNext()){
//            tmpActor=iterator.next();
//            g.drawImage(tmpActor.getImage(),
//                    tmpActor.getX()-tmpActor.getWidth()/2,
//                    tmpActor.getY()-tmpActor.getHeigth()/2,
//                    null);
//        }
//        Log.d("layer paint");
//    }
}
