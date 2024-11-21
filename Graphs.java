package App;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Graphs extends JPanel {
    public static double[] Apple = new double[15];
    public static double[] Microsoft=new double[15];
    public static double[] Google=new double[15];
    public static double[] Lenovo=new double[15];
    public static double[] Philips=new double[15];
    private static final int maxPoints = 15;
    JPanel graph;

    public Graphs() {
        graph = this;
        graph.setPreferredSize(new Dimension(300,300));
    }

    public void Gen(ArrayList<Double> a,double[] b) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                double[] values;
                values = getlast15values(a);

                for (int i = 0; i < values.length; i++) {
                    b[i] = values[i];
                }
//                for (int i = 0; i < Apple.length; i++) {
//                    System.out.println("run" + Apple[i]);
//                }
            }
        }, 0, 100);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);

        int originY = getHeight() / 2;
        g2d.drawLine(50, originY, getWidth() - 50, originY); // X-axis
        g2d.drawLine(50, 50, 50, getHeight() - 50); // Y-axis

        // Draw the graph
        g2d.setColor(Color.BLUE);
        int graphWidth = getWidth() - 100; // Width of the graph area
        int pointSpacing = graphWidth / maxPoints; // Spacing between points

//        for (int i = 0; i < maxPoints - 1; i++) {
//            int x1 = 50 + i * pointSpacing;
//            int y1 = originY - (int) (b[i] * 2); // Scale the value for display
//            int x2 = 50 + (i + 1) * pointSpacing;
//            int y2 = originY - (int) (b[i + 1] * 2);
//            g2d.drawLine(x1, y1, x2, y2);
//        }
        int y1;
        int y2;
        graph.repaint();
    }

    public double[] getlast15values(ArrayList<Double> a){
        double[] array=new double[15];
        for(int i=0;i<15;i++){
            int size=a.size();
            int index=size-15+i;
            array[i]=(index>=0)? a.get(index):100;
        }
        return array;
    }
    public double getlatestvalue(double[] a){
        int size=a.length-1;
        double latestvalue=a[size];
        return latestvalue;
    }
    public void start()      {
        Graphs generate=new Graphs();

        Stock apple = new Stock("Apple",100);
        Stock microsoft = new Stock("Apple",100);
        Stock lenovo = new Stock("Apple",100);
        Stock philips = new Stock("Apple",100);
        Stock google = new Stock("Apple",100);

        generate.Gen(apple.stock_history,Apple);
        generate.Gen(microsoft.stock_history,Microsoft);
        generate.Gen(lenovo.stock_history,Lenovo);
        generate.Gen(philips.stock_history,Philips);
        generate.Gen(google.stock_history,Google);
    }
}
