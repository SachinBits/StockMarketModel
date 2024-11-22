package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PortFolio extends JPanel{

    JPanel p1;

    PortFolio(){

        p1=new JPanel();
        PortFolio_table table=new PortFolio_table();
        p1.add(table.getPortfoliotable());

        p1.setBackground(Color.red);
        p1.setPreferredSize(new Dimension(100,200));

    }
    public JPanel getpanel(){
        return p1;
    }
}
