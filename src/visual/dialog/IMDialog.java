package visual.dialog;

import base.IO.log.Log;
import core.Core;
import io.ImageManager;
import visual.Tool;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class IMDialog extends JDialog {
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
    private JComboBox comboBox1;
    private JButton setAsButton;
    private ImageManager imageManagerToList;
    private EditDialog editDialog;
    private Image tmpImage;
    private SurelyDialog surelyDialog;

    private JFileChooser jFileChooser = new JFileChooser();

    public IMDialog(final ImageManager imageManager) {
        imageManagerToList = imageManager;
        setContentPane(contentPane);
        setModal(true);
        setupJFileChooser();
        setupEditDialog();
        setupDeleteDialog();
        setupSetas();
        getList();



        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Log.v("valueChanged" + " " + list.getModel().getElementAt(list.getSelectedIndex()));
                tmpImage = imageManagerToList.getImageTable().get(list.getModel().getElementAt(list.getSelectedIndex()));
                //Log.d(tmpImage.toString());
                picbow.repaint();
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                if (e.getClickCount() == 2)
                    callEditDialog();

            }
        });


        getRootPane().setDefaultButton(buttonOK);
        this.setResizable(false);

        buttonOK.addActionListener(e -> onOK());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        importButton.addActionListener(e -> jFileChooser.showOpenDialog(this));
        this.pack();
        Tool.MidPlay(this);
        this.setVisible(true);
    }

    private void setupSetas() {
        comboBox1.addItem(ImageManager.Type.ACTOR);
        comboBox1.addItem(ImageManager.Type.TAG);
        comboBox1.addItem(ImageManager.Type.BASE);
        setAsButton.addActionListener(e -> {
            Core.getGlobalImageManager().changeTypePoint((ImageManager.Type)comboBox1.getSelectedItem(), (String) list.getSelectedValue());
        });
    }

    private void setupDeleteDialog() {
        deleteButton.addActionListener(e -> {
            try {
                surelyDialog = new SurelyDialog("delete : " + list.getSelectedValue().toString());

                if (surelyDialog.getResult()) {
                    imageManagerToList.getImageTable().remove(list.getSelectedValue());
                    getList();
                }
            }catch (NullPointerException e1){
                Log.e("no selected item",e1);
            }
        });

    }

    private void setupEditDialog() {
        editButton.addActionListener(e -> callEditDialog());

    }

    private void callEditDialog(){
        editDialog=new EditDialog("key Name",(String)list.getSelectedValue());
        try {
            imageManagerToList.changeKeyName((String) list.getSelectedValue(),editDialog.getRelpy());
            getList();
        } catch (Exception e1) {
            Log.e("failed to change the name",e1);
        }
        Log.d(editDialog.getRelpy());
    }
    private void onOK() {
        // add your code here
        dispose();
    }

    private void setupJFileChooser() {
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.getName().endsWith(".jpg") || f.getName().endsWith(".png")) return true;
                return false;
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        jFileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.v("import file");
                try {
                    String file = ((JFileChooser) e.getSource()).getSelectedFile().toString();
                    Log.v("load file " + file);
                    imageManagerToList.importImage(file);
                    getList();
                } catch (IOException e1) {
                    Log.e("import failed", e1);
                } catch (NullPointerException e2) {
                    Log.e("no file selected", e2);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        picbow = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Log.v("picbow repaint");
                if (tmpImage == null) return;
                Dimension panelSize = this.getSize();
                Dimension imageSize = new Dimension(tmpImage.getWidth(null), tmpImage.getHeight(null));
                g.drawImage(tmpImage, (panelSize.width - imageSize.width) / 2, (panelSize.height - imageSize.height) / 2, null);
            }
        };
    }

    private void getList() {
        try {
            list.setListData(imageManagerToList.getImageTable().keySet().toArray());
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("import failed", e);
        }
    }

}
