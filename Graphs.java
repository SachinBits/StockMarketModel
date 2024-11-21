package App;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Graphs extends JPanel {
    static double[] Apple = new double[15];
//    static double[] Microsoft=new double[15];
//    static double[] Google=new double[15];
//    static double[] Lenovo=new double[15];
//    static double[] Philips=new double[15];

    private static final int DATA_POINTS = 15; // Number of points to display
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 300;
    private static final int REFRESH_RATE = 2500;

    JPanel graph;

    public Graphs() {
        graph = this;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    public JPanel getpanel() {
        return graph;
    }
    public void Gen_Apple(ArrayList<Double> data) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (data != null && data.size() >= DATA_POINTS) {
                    Apple = getlast15values(data);
                    SwingUtilities.invokeLater(() -> graph.repaint());// Repaint the graph
                }
            }
        }, 0, REFRESH_RATE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set up the graph dimensions
        int graphWidth = getWidth();
        int graphHeight = getHeight();
        int padding = 20;

        int xStep = (graphWidth - 2 * padding) / (DATA_POINTS - 1);
        int maxY = (int) Math.ceil(getMaxValue(Apple));
        int minY = (int) Math.floor(getMinValue(Apple));
        double yScale = (double) (graphHeight - 2 * padding) / (maxY - minY);

        // Draw the graph lines
        g2d.setPaint(Color.BLUE);
        for (int i = 0; i < Apple.length - 1; i++) {
            int x1 = padding + i * xStep;
            int y1 = graphHeight - padding - (int) ((Apple[i] - minY) * yScale);
            int x2 = padding + (i + 1) * xStep;
            int y2 = graphHeight - padding - (int) ((Apple[i + 1] - minY) * yScale);
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
    private double getMaxValue(double[] values) {
        double max = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            max = Math.max(max, v);
        }
        return max;
    }

    private double getMinValue(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        for (double v : values) {
            min = Math.min(min, v);
        }
        return min;
    }


//    public void Gen_Apple(ArrayList<Double> a) {
//
//        Timer timer = new Timer();
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//
//            @Override
//            public void run() {
//                double[] values;
//                values = getlast15values(a);
//                for (int i = 0; i < values.length; i++) {
//                    Apple[i] = values[i];
//                }
//                for (int i = 0; i < Apple.length; i++) {
//                    System.out.println("run" + Apple[i]);
//
//                }
//
//
//
//            }
//        }, 0, 2500);
//
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        int[] coordinates = new int[15];
//
//        for (int i = 0; i < Apple.length; i++) {
//            System.out.println(("val" + Apple[i]));
//        }
//
//        for (int i = 0; i < coordinates.length; i++) {
//            System.out.println(coordinates[i]);
//        }
//
//
//        g2d.setPaint(Color.BLUE);
//
//        for (int i = 0; i < Apple.length; i++) {
//            coordinates[i] = (int) Apple[i];
//
//        }
//
//        int y1;
//        int y2;
//
//
//        for (int i = 0; i < coordinates.length - 1; i++) {
//            y1 = coordinates[i];
//            y2 = coordinates[i + 1];
//            System.out.println(y1);
//            System.out.println(y2);
//            g.drawLine(i + 300, y1, i + 900, y2);
//        }
//        graph.repaint();
//    }

    public double[] getlast15values(ArrayList<Double> a){
        double[] array=new double[15];
        for(int i=0;i<15;i++){
            int size=a.size();
            int index=size-15+i;
            array[i]=(index>=0)? a.get(index):0;
        }
        return array;
    }





    public void start()      {
        Graphs generate=new Graphs();
        Generating_Values g1=new Generating_Values();
        g1.Apple_Generating_Values();
        g1.Google_Generating_Values();
        g1.Lenovo_Generating_Values();
        g1.Philips_Generating_Values();
        g1.Microsoft_Generating_Values();
        generate.Gen_Apple(Generating_Values.Apple_Stock);



//        generate.Gen(Generating_Values.Microsoft_Stock,generate.Microsoft);
//        generate.Gen(Generating_Values.Lenovo_Stock,generate.Lenovo);
//        generate.Gen(Generating_Values.Philips_Stock,generate.Philips);
//        generate.Gen(Generating_Values.Google_Stock,generate.Google);
    }
}
