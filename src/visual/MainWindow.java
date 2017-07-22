package visual;

import base.IO.log.Log;
import core.Core;
import ojbs.Actor;
import visual.dialog.IMDialog;
import visual.dialog.MsgDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zyvis on 2017/7/22.
 */
public class MainWindow{
    private JFrame mainWindow;
    private JPopupMenu menu = new JPopupMenu();

    public List<Layer> getPanelList() {
        return panelList;
    }

    private List<Layer> panelList;

    public MainWindow() {
        importMenu();
        setupWindows();
        panelList=new ArrayList<>();
        mainWindow.setPreferredSize(new Dimension(1024,768));
        mainWindow.pack();
        Log.d("main window width: "+mainWindow.getWidth()+" height : "+mainWindow.getHeight());
        Tool.MidPlay(mainWindow);
        mainWindow.setVisible(true);
    }
    private void setupWindows(){
        mainWindow = new JFrame("MainWindows"){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Iterator<Layer> layerIterator=panelList.iterator();
                Log.d("mainWindow paint, totally layer: "+panelList.size());
                int layer=0;
                while(layerIterator.hasNext()){
                    Iterator<Actor> actorIterator=layerIterator.next().getObjlist().iterator();
                    Log.d("visit layer index: "+layer++);
                    int actorindex=0;
                    while(actorIterator.hasNext()){
                        Actor tmpActor=actorIterator.next();
                        Log.d("draw actor index: "+actorindex++);
                        g.drawImage(tmpActor.getImage(),tmpActor.getX(),tmpActor.getY(),null);
                    }
                }

            }
        };
        mainWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    menu.show(mainWindow, e.getX(), e.getY());
                }
            }
        });
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //mainWindow.add(menu);
    }
    private void importMenu() {
        JMenuItem menu_IM = new JMenuItem("ImageManager");
        JMenuItem menu_NP = new JMenuItem("New Panel");
        JMenuItem menu_About = new JMenuItem("About");
        menu.add(menu_NP);
        menu.add(menu_IM);
        menu.add(menu_About);
        menu_IM.addActionListener(e -> new IMDialog(Core.getGlobalImageManager()));
        menu_NP.addActionListener(e -> {
            Actor base=new Actor(new Point(0,0),Core.getGlobalImageManager().get("base"));
            Layer<Actor> baselayer=new Layer<>();
            baselayer.drawNewObj(base);
            panelList.add(baselayer);
            mainWindow.repaint();
        });
        menu_About.addActionListener(e -> new MsgDialog(Core.getAbout()));
    }

}
