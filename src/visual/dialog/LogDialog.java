package visual.dialog;

import base.IO.log.DefaultLogcat;
import base.IO.log.Log;
import base.IO.log.Logcat;
import base.IO.log.SingleLog;
import visual.Tool;

import javax.swing.*;

public class LogDialog extends JDialog {
    private JPanel contentPane;
    private JTextPane textPane1;
    private JScrollPane jsp;
    private JCheckBox noVerboseCheckBox;
    private Logcat visualLogcat;
    private JScrollBar sBar = jsp.getVerticalScrollBar();

    public LogDialog() {
        setContentPane(contentPane);
        setModal(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setResizable(false);
        noVerboseCheckBox.addActionListener(e -> {
            visualLogcat.setLevelFilter(noVerboseCheckBox.isSelected()?Log.Level.Debug: Log.Level.Verbose);
        });
        visualLogcat=new DefaultLogcat(){
            @Override
            public void print(SingleLog singleLog) {
                //super.print(singleLog);
                textPane1.setText(textPane1.getText()+singleLog+"\n");
                sBar.setValue(sBar.getMaximum());
            }
        };
        Log.addLogcat(visualLogcat);
        this.setTitle("Log");
        pack();
        Tool.RightMidPlay(this);
    }

}
