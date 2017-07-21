package visual;

import javax.swing.*;
import java.util.List;
import java.util.Stack;

/**
 * Created by zyvis on 2017/7/22.
 */
public class LocalPanel extends JPanel{
    private List<Layer> layers;

    public LocalPanel() {
        layers=new Stack<>();
    }
}
