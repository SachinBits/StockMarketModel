package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Listing extends JPanel  {
    JPanel List;
    GraphFrame graphFrame;
    private static DefaultTableModel tablemodel;
    static Stock apple = new Stock("Apple",100);
    static Stock microsoft = new Stock("Microsoft",100);
    static Stock lenovo = new Stock("Lenovo",100);
    static Stock philips = new Stock("Philips",100);
    static Stock google = new Stock("Google",100);
    static GraphsMain generate_apple=new GraphsMain(apple.stock_history);
    static GraphsMain generate_microsoft=new GraphsMain(microsoft.stock_history);
    static GraphsMain generate_lenovo=new GraphsMain(lenovo.stock_history);
    static GraphsMain generate_philips=new GraphsMain(philips.stock_history);
    static GraphsMain generate_google=new GraphsMain(google.stock_history);

    Listing() {

        String[] columns = {"ID", "Companies", "Current Stock Price"};

        Object[][] data = {{1, "Apple", 0.0},
                {2, "Microsoft", 0.0},
                {3, "Philips", 0.0},
                {4, "Google", 0.0},
                {5, "Lenovo", 0.0}};

        tablemodel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List = new JPanel();

        JTable Table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(Table);
        List.setBackground(Color.blue);
        List.setPreferredSize(new Dimension(850, 30));
        List.add(scrollPane, BorderLayout.CENTER);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                updatetable();


            }
        }, 0, 1800);

        Table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row=Table.rowAtPoint(e.getPoint());

                opengraph(row);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void opengraph(int i){
        try{
            graphFrame=new GraphFrame();
        }
        catch(Exception e){
            System.out.println("Yep thats the limit");
        }
    }

    public static void updatetable(){
        tablemodel.setValueAt(generate_apple.getlatestvalue(generate_apple.b),0,2);
        tablemodel.setValueAt(generate_microsoft.getlatestvalue(generate_microsoft.b),1,2);
        tablemodel.setValueAt(generate_lenovo.getlatestvalue(generate_lenovo.b),2,2);
        tablemodel.setValueAt(generate_philips.getlatestvalue(generate_philips.b),3,2);
        tablemodel.setValueAt(generate_google.getlatestvalue(generate_google.b),4,2);
//        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Microsoft),1,2);
//        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Philips),2,2);
//        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Google),3,2);
//        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Lenovo),4,2);


    }
    public JPanel getpanel()
    {
        return List;
    }


}
