package App;

import javax.swing.*;
import java.awt.*;

public class GraphFrame {
    public static void main(String[] args) throws InterruptedException {
//        JFrame Graphframe=new JFrame();
//        Graphs graph=new Graphs();
//        graph.start();
//        Thread.sleep(2000);
//
//        Graphframe.setLayout(new BorderLayout(10,10));
//        Graphframe.setBounds(500,500,500,500);
//        Graphframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Graphframe.add(graph,BorderLayout.CENTER);
//        Graphframe.setResizable(true);
//        Graphframe.setVisible((true));
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graphs");
            Graphs graphs = new Graphs();
            frame.add(graphs);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            graphs.start();
        });



    }
}
