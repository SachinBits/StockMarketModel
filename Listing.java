package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Listing extends JPanel {
    JPanel List;
    private static DefaultTableModel tablemodel;

    Listing(){
        JLabel Listinglabel=new JLabel("Listing of Companies");
        Listinglabel.setFont(new Font("Arial",Font.PLAIN,40));
        Listinglabel.setHorizontalTextPosition(JLabel.LEFT);
        Listinglabel.setVerticalTextPosition(JLabel.TOP);
        Listinglabel.setHorizontalAlignment(JLabel.LEFT);
        Listinglabel.setVerticalAlignment(JLabel.TOP);




//        "Apple","Google","Lenovo","Philips","Microsoft"

        String[] columns={"ID","Companies","Current Stock Price"};

        Object[][] data={{1,"Apple",0.0},
                {2,"Microsoft",0.0},
                {3,"Philips",0.0},
                {4,"Google",0.0},
                {5,"Lenovo",0.0}};

        tablemodel=new DefaultTableModel(data,columns){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        List=new JPanel();

        JTable  Table=new JTable(tablemodel);
        JScrollPane scrollPane=new JScrollPane(Table);
        List.setBackground(Color.blue);
        List.setPreferredSize(new Dimension(850  ,30));


        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                updatetable();



//        List.add(Listinglabel);
                List.add(scrollPane,BorderLayout.CENTER);

            }
        }, 0, 1800);

    }
    public static void updatetable(){
        Graphs g1=new Graphs();

        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Apple),0,2);
//        System.out.println(g1.getlatestvalue(Graphs.Microsoft));
        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Microsoft),1,2);
        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Philips),2,2);
        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Google),3,2);
        tablemodel.setValueAt(g1.getlatestvalue(Graphs.Lenovo),4,2);

    }


    public JPanel getpanel(){
        return List;
    }
}
