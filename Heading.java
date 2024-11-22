package App;

import javax.swing.*;
import java.awt.*;

public class Heading extends JPanel{
    JPanel Header;
    Heading(){
        setLayout(new GridLayout(2,1));
        JLabel Headerlabel=new JLabel("WELCOME TO THE STOCK SIMULATION");
        Headerlabel.setFont(new Font("Arial",Font.PLAIN,40));
        Headerlabel.setHorizontalTextPosition(JLabel.LEFT);
        Headerlabel.setVerticalTextPosition(JLabel.TOP);
        Headerlabel.setHorizontalAlignment(JLabel.LEFT);
        Headerlabel.setVerticalAlignment(JLabel.TOP);

        JLabel Headerlabel1=new JLabel("BALANCE:");
        Headerlabel1.setFont(new Font("Arial",Font.PLAIN,40));
        Headerlabel1.setHorizontalTextPosition(JLabel.LEFT);
        Headerlabel1.setVerticalTextPosition(JLabel.TOP);
        Headerlabel1.setHorizontalAlignment(JLabel.LEFT);
        Headerlabel1.setVerticalAlignment(JLabel.TOP);

        Header=new JPanel();

        Header.setPreferredSize(new Dimension(100,100));
        Header.setBackground(Color.GREEN);
        Header.add(Headerlabel);
        Header.add(Headerlabel1);

    }
    public JPanel getpanel(){
        return Header;
    }
}
