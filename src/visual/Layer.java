package visual;

import io.ImageManager;
import ojbs.Actor;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zyvis on 2017/7/22.
 */
public class Layer<T extends Actor> extends JPanel {
    private LinkedList<T> objlist;
    private Iterator<T> iterator;
    private ImageManager imageManager;

    public Layer(ImageManager imageManager) {
        objlist =new LinkedList<>();
        this.imageManager=imageManager;
    }

    public void drawNewObj(Point waypoint,String ImageKey){
        Actor tmp=new Actor(waypoint,null,imageManager.get(ImageKey));
        objlist.add((T)tmp);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        iterator=objlist.iterator();
        Actor tmpActor;
        while(iterator.hasNext()){
            tmpActor=iterator.next();
            g.drawImage(tmpActor.getImage(),
                    tmpActor.getX()-tmpActor.getWidth()/2,
                    tmpActor.getY()-tmpActor.getHeigth()/2,
                    null);
        }
    }
}
