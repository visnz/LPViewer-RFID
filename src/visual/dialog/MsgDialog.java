package visual.dialog;

import visual.Tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MsgDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label;

    public MsgDialog(String msg,Throwable tr) {
        this("<html>"+msg+"<br>"+tr.getMessage()+"</html>");
    }

    public MsgDialog(Throwable tr) {
        this(tr.getMessage());
    }

    public MsgDialog(String msg) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        label.setText(msg);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        pack();
        Tool.MidPlay(this);
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

}
