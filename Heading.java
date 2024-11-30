package App;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Heading extends JPanel{
    JPanel Header;
    JLabel Headerlabel1=new JLabel();
    Heading(){
        setLayout(new GridLayout(2,1));
        JLabel Headerlabel=new JLabel("WELCOME TO THE STOCK SIMULATION");
        Headerlabel.setFont(new Font("Arial",Font.PLAIN,40));
        Headerlabel.setHorizontalTextPosition(JLabel.CENTER);
        Headerlabel.setVerticalTextPosition(JLabel.CENTER);
        Headerlabel.setHorizontalAlignment(JLabel.CENTER);
        Headerlabel.setVerticalAlignment(JLabel.TOP);
        Timer timer =new Timer();
        Header=new JPanel();
        Header.setLayout(new BorderLayout());

        Header.setPreferredSize(new Dimension(100,100));
        Header.setBackground(Color.GRAY);
        Header.add(Headerlabel,BorderLayout.CENTER);


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Headerlabel1.setText("Balance:"+(int)Buying.Balance+"\nProfit/Loss:"+(int)Buying.profit_loss);
                Headerlabel1.setFont(new Font("Arial",Font.PLAIN,40));
                Headerlabel1.setHorizontalTextPosition(JLabel.CENTER);
                Headerlabel1.setVerticalTextPosition(JLabel.CENTER);
                Headerlabel1.setHorizontalAlignment(JLabel.CENTER);
                Headerlabel1.setVerticalAlignment(JLabel.CENTER);
                Header.add(Headerlabel1,BorderLayout.EAST);


        }},0,1000);
    }

    public JPanel getpanel(){
        return Header;
    }
    }
