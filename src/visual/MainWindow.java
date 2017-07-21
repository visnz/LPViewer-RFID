package visual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyvis on 2017/7/22.
 */
public class MainWindow {
    private JFrame mainWindow;
    private JMenuBar menuBar;
    private JMenu menuFile,menuEdit,menuView,menuPoint,menuAbout;
    private List<JPanel> panelList;
    public MainWindow() {
        importMenu();
        setupWindows();
        panelList=new ArrayList<>();
        mainWindow.setVisible(true);
    }
    private void setupWindows(){
        mainWindow = new JFrame("MainWindows");
        mainWindow.setResizable(false);
        Dimension frameSize=new Dimension(1024,768);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setBounds((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2,frameSize.width,frameSize.height);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setJMenuBar(menuBar);
    }
    private void importMenu() {
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        JMenuItem menuFile_load = new JMenuItem("Load File");
        menuView = new JMenu("View");
        menuPoint = new JMenu("Point");
        menuAbout = new JMenu("About");
        menuBar.add(menuFile);
        menuFile.add(menuFile_load);
        menuBar.add(menuView);
    }
}
