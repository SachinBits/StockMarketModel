
package App;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GraphsMain extends JPanel {
    private static final int maxPoints = 15;
    public double[] b = new double[15];


     GraphsMain(ArrayList<Double> a) {
        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                double[] values;
                values = getlast15values(a);

                for (int i = 0; i < values.length; i++) {
                    b[i] = values[i];
//                    System.out.println(b[i]);
                }
                SwingUtilities.invokeLater(() -> repaint());
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

        int originY = getHeight() ;
        g2d.drawLine(50, originY, getWidth() - 50, originY); // X-axis
        g2d.drawLine(50, 50, 50, getHeight() - 50); // Y-axis

        // Draw the graph
        g2d.setColor(Color.BLUE);
        int graphWidth = getWidth() - 100; // Width of the graph area
        int pointSpacing = graphWidth / maxPoints; // Spacing between points

        for (int i = 0; i < maxPoints - 1; i++) {
            int x1 = 50 + i * pointSpacing;
            int y1 = originY - (int) (b[i] * 2); // Scale the value for display
            int x2 = 50 + (i + 1) * pointSpacing;
            int y2 = originY - (int) (b[i + 1] * 2);
            g2d.drawLine(x1, y1, x2, y2);
        }
        int y1;
        int y2;

    }

    public double[] getlast15values(ArrayList<Double> a){
        double[] array=new double[15];
        for(int i=0;i<15;i++){
            int size=a.size();
            int index=size-15+i;
            array[i]=(index>=0)? a.get(index):100;
//            System.out.println(a.get(index));
        }
        return array;
    }
    public double getlatestvalue(double[] a){
        int size=a.length-1;
        double latestvalue=a[size];
//        System.out.println(latestvalue);
        return latestvalue;
    }
    public JPanel getJPanel(){
         return this;
    }
}
