package App;

import javax.swing.*;
import java.awt.*;

public class GraphFrame {
    GraphFrame(GraphsMain a) {

        JFrame frame = new JFrame("Graphs");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(250,500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel graphs =a.getJPanel();
        graphs.setPreferredSize(new Dimension(250,200));
        frame.add(graphs);
        frame.pack();
        frame.setVisible(true);
    };
}
