package visual.dialog;

import visual.Tool;

import javax.swing.*;
import java.awt.event.*;

public class EditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField2;
    private JPanel outside;

    public String getRelpy() {
        return relpy;
    }

    private String relpy;

    public EditDialog(String title,String init) {
        outside.setBorder(BorderFactory.createTitledBorder(title));
        setContentPane(contentPane);
        setModal(true);
        this.setResizable(false);
        getRootPane().setDefaultButton(buttonOK);
        textField2.setText(init);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> {onCancel();},
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.pack();
        Tool.MidPlay(this);
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        this.relpy=textField2.getText();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
