package visual.dialog;

import base.IO.log.Log;
import io.ImageManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class IMdialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList list;
    private JPanel basic;
    private JPanel buttons;
    private JPanel keypanel;
    private JPanel imagepanel;
    private JButton importButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel inbuttons;
    private JPanel picbow;
    private ImageManager imageManagerToList;

    private JFileChooser jFileChooser=new JFileChooser();

    public IMdialog(ImageManager imageManager) {
        imageManagerToList=imageManager;
        setContentPane(contentPane);
        setModal(true);
        setjFileChooser();
        setBasic();


        getRootPane().setDefaultButton(buttonOK);
        this.setResizable(false);
        this.pack();
        buttonOK.addActionListener(e-> onOK());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        importButton.addActionListener(e-> jFileChooser.showOpenDialog(this));
        setVisible(true);

    }

    private void onOK() {
        // add your code here
        dispose();
    }
    private  void setBasic(){
        DefaultListModel listModel=new DefaultListModel();
        Object[] tmp=imageManagerToList.getImageTable().keySet().toArray();
        for(Object t:tmp){
            listModel.addElement(t);
        }
        list =new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Log.d("imageManagerToList.getImageTable().keySet().toArray()="+imageManagerToList.getImageTable().keySet().toString());
        list.setVisible(true);

    }
    private void setjFileChooser(){
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.getName().endsWith(".jpg")||f.getName().endsWith(".png"))return true;
                return false;
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
    }
}
