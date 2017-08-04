package visual;

import base.IO.log.Log;
import core.Core;
import ojbs.Actor;
import visual.dialog.EditDialog;
import visual.dialog.IMDialog;
import visual.dialog.LogDialog;
import visual.dialog.MsgDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by zyvis on 2017/7/22.
 */
public class MainWindow {
    private JFrame mainWindow;
    private JPopupMenu menu = new JPopupMenu();
    private LogDialog logDialog= new LogDialog();

    public List<Layer> getLayerList() {
        return layerList;
    }

    private List<Layer> layerList;

    public Layer getSelectedLayer() {
        return selectedLayer;
    }

    private Layer selectedLayer;

    public MainWindow(Dimension defaultMainWindowFramsize) {
        importMenu();
        setupWindows();
        layerList = new Stack<>();
        mainWindow.setPreferredSize(defaultMainWindowFramsize);
        mainWindow.pack();
        Log.d("main window width: " + mainWindow.getWidth() + " height : " + mainWindow.getHeight());
        Tool.MidPlay(mainWindow);
        mainWindow.setVisible(true);
        logDialog.setVisible(true);
    }

    private void setupWindows() {
        mainWindow = new JFrame("MainWindows") {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Log.v("mainWindow paint, totally layer: " + layerList.size());
                try {
                    Iterator<Actor> actorIterator = selectedLayer.getObjlist().iterator();
                    int actorindex = 0;
                    while (actorIterator.hasNext()) {
                        Actor tmpActor = actorIterator.next();
                        Log.v("draw actor index: " + actorindex++);
                        g.drawImage(tmpActor.getImage(), tmpActor.getRenderPointX()+selectedLayer.getOffset().x, tmpActor.getRenderPointY()+selectedLayer.getOffset().y, null);
                    }
                }catch (NullPointerException e){
                    Log.w("no basic panel",e);
                    return;
                }

            }
        };
        mainWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    menu.show(mainWindow, e.getX(), e.getY());
                }
            }
        });
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                mainWindow.repaint();
            }
        });
        //mainWindow.add(menu);
    }

    private void importMenu() {
        JMenuItem menu_NP = new JMenuItem("New Panel");
        JMenuItem menu_Refresh = new JMenuItem("Refresh");
        JMenuItem menu_IM = new JMenuItem("ImageManager");
        JMenuItem menu_Log = new JMenuItem("Log");
        JMenu menu_Socket = new JMenu("Socket");
        JMenu menu_Render = new JMenu("Render");
        JMenuItem menu_Render_duration = new JMenuItem("duration");
        JMenu menu_Layers = new JMenu("layer");
        JMenuItem menu_Socket_Listener = new JMenuItem("Port Listener");
        JMenuItem menu_Socket_Active = new JMenuItem("Connect to Remote Host");

        JMenuItem menu_About = new JMenuItem("About");
        menu.add(menu_Refresh);
        menu.add(menu_NP);
        menu_Socket.add(menu_Socket_Listener);
        menu_Socket.add(menu_Socket_Active);
        menu.add(menu_Socket);
        menu.add(menu_IM);
        menu_Render.add(menu_Render_duration);
        menu.add(menu_Layers);
        menu.add(menu_Render);
        menu.add(menu_Log);
        menu.add(menu_About);
        menu_Refresh.addActionListener(e -> mainWindow.repaint());
        menu_IM.addActionListener(e -> {
            new IMDialog(Core.getGlobalImageManager());
            mainWindow.repaint();
        });
        menu_NP.addActionListener(e -> {
            Layer<Actor> baselayer = new Layer<>();
            baselayer.drawNewObj(new Actor(new Point(
                    (int) mainWindow.getPreferredSize().getWidth() / 2,
                    (int) (mainWindow.getPreferredSize().getHeight() / 2))
                    , Core.getGlobalImageManager().get("base")));
            layerList.add(baselayer);
            selectedLayer=baselayer;
            JMenuItem layer=new JMenuItem("layer:"+String.valueOf(layerList.indexOf(baselayer)));
            layer.addActionListener(e1 -> {
                Log.d("choose layer :"+layer.getText());
                selectedLayer= layerList.get(Integer.parseInt((layer.getText().split(":"))[1]));
                Log.d("now selected layer : "+selectedLayer.toString());
            });
            menu_Layers.add(layer);
            Log.d("totally layers: "+layerList.size());
            mainWindow.repaint();
        });
        menu_Log.addActionListener(e -> logDialog.setVisible(true));
        menu_About.addActionListener(e -> {
            new MsgDialog(Core.getAbout());
            mainWindow.repaint();
        });
        menu_Socket_Listener.addActionListener(e -> {
            try {
                EditDialog portedit =new EditDialog("Socket Listener Port","");
                Core.startSocket(Integer.parseInt(portedit.getRelpy()));
            }catch (NumberFormatException e1){
                new MsgDialog("not a legal port",e1);
            }

        });
        menu_Socket_Active.addActionListener(e -> {
            try {
                EditDialog ipedit= new EditDialog("Remote IP address","");
                InetAddress ip=InetAddress.getByName(ipedit.getRelpy());
                EditDialog portedit =new EditDialog("Socket Listener Port","");
                Core.startSocket(ip.getHostAddress(),Integer.parseInt(portedit.getRelpy()));
            }catch (NumberFormatException e1){
                new MsgDialog("not a legal port",e1);
            }catch (UnknownHostException e2){
                new MsgDialog("unknown host",e2);
            }
        });
        menu_Render_duration.addActionListener(e -> {
            EditDialog duration =new EditDialog("duration",Core.getDuration());
            Core.setDuration(Integer.parseInt(duration.getRelpy()));
        });
    }
    public void draw(Actor actor){
        selectedLayer.drawNewObj(actor);
        mainWindow.repaint();
    }
}