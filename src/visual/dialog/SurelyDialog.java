package visual.dialog;

import visual.Tool;

import javax.swing.*;
import java.awt.event.*;

public class SurelyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;

    public boolean getResult() {
        return result;
    }

    private boolean result=false;

    public SurelyDialog(String msg) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        label.setText(msg);
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
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        Tool.MidPlay(this);
        this.setVisible(true);
    }

    private void onOK() {
        result=true;
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        result=false;
    }
}
