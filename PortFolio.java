package App;

import javax.swing.*;
import java.awt.*;

public class PortFolio extends JPanel{

    JPanel p1;
    PortFolio(){
        JLabel Portfoliolabel=new JLabel("PortFolio");
        Portfoliolabel.setFont(new Font("Arial",Font.PLAIN,40));
        Portfoliolabel.setHorizontalTextPosition(JLabel.LEFT);
        Portfoliolabel.setVerticalTextPosition(JLabel.TOP);
        Portfoliolabel.setHorizontalAlignment(JLabel.LEFT);
        Portfoliolabel.setVerticalAlignment(JLabel.TOP);

        p1=new JPanel();
        p1.add(Portfoliolabel);
        p1.setBackground(Color.red);
        p1.setPreferredSize(new Dimension(100,200));

    }
    public JPanel getpanel(){
        return p1;
    }
}
