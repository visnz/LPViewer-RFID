package visual;

import visual.dialog.IMdialog;
import core.Core;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyvis on 2017/7/22.
 */
public class MainWindow{
    private JFrame mainWindow;
    private JMenuBar menuBar;
    private JMenu menuFile,menuEdit,menuView,menuPoint,menuAbout;
    private List<JPanel> panelList;

    public MainWindow() {
        importMenu();
        setupWindows();
        panelList=new ArrayList<>();
        mainWindow.setPreferredSize(new Dimension(1024,768));
        mainWindow.pack();
        Tool.MidPlay(mainWindow);
        mainWindow.setVisible(true);
    }
    private void setupWindows(){
        mainWindow = new JFrame("MainWindows");
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setJMenuBar(menuBar);
    }
    private void importMenu() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        JMenuItem menuFile_IM = new JMenuItem("ImageManager");
        menuView = new JMenu("View");
        menuPoint = new JMenu("Point");
        menuAbout = new JMenu("About");
        menuBar.add(menuFile);
        menuFile.add(menuFile_IM);
        menuBar.add(menuView);
        menuFile_IM.addActionListener(e -> new IMdialog(Core.getGlobalImageManager()));
    }
}
