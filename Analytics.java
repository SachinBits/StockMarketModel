package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class Analytics {
    JPanel analytics;
    JLabel LeaderBoard;
    DefaultTableModel tablemodel2;
    JTable table2;
    ArrayList<Object[]> rows=new ArrayList<>();

    public Analytics(){
        analytics=new JPanel();
        String[] columns={"ID","Companies","Current Stock Price"};
        Object data[][] = new Object[0][];

        tablemodel2=new DefaultTableModel(data,columns){
            @Override
            public boolean isCellEditable(int row,int columns){
                return false;
            }
        };
        LeaderBoard=new JLabel("LeaderBoard");
        LeaderBoard.setFont(new Font("Arial",Font.ITALIC,50));
        LeaderBoard.setVerticalTextPosition(JLabel.CENTER);

        table2=new JTable(tablemodel2);
        PortFolio_table tb=new PortFolio_table();
        tb.setrowwidth(table2,40);

        table2.setPreferredSize(new Dimension(100,1000));
        JScrollPane scrollPane2=new JScrollPane(table2);
        analytics=new JPanel();
        analytics.setLayout((new BorderLayout()));
        analytics.setPreferredSize(new Dimension(600,500));
        Listing getcompanies=new Listing();

        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TableModel table=getcompanies.gettable();
                for(int i=0;i<5;i++){
                    Object[] row =new Object[table.getColumnCount()];
                    for(int j=0;j<table.getColumnCount();j++){
                        row[j]=table.getValueAt(i,j);
                    }
                    rows.add(row);
                }

                rows.sort((row1,row2)->{
                    Double value1=Double.parseDouble(row1[2].toString());
                    Double value2=Double.parseDouble(row2[2].toString());
                    return value2.compareTo(value1);

                });
                SwingUtilities.invokeLater(()->setleaderboard(rows));
            }
        },0,1000);
        analytics.add(LeaderBoard,BorderLayout.NORTH);

        analytics.add(scrollPane2,BorderLayout.CENTER);


    }
    public void setleaderboard(ArrayList<Object[]> company){
        tablemodel2.setRowCount(0);
        for(Object[] z:company){
            tablemodel2.addRow(z);
        }
        rows.clear();
    }


    public JPanel getPanel(){
        return analytics;
    }
    public void leaderboard(){
        new Analytics();
    }

}