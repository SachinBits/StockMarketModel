package App;

import javax.swing.*;
import java.awt.*;

public class Stocks{
    public static void main(String... args) {
        JFrame f1=new JFrame();
        f1.setLayout(new BorderLayout(10,10));
        f1.setBounds(500,500,500,500);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setResizable(true);

        PortFolio portfolio=new PortFolio();
        Heading Header=new Heading();
        Listing List=new Listing();
        Transaction transaction=new Transaction();

        f1.add(portfolio.getpanel(),BorderLayout.SOUTH);
        f1.add(List.getpanel(), BorderLayout.WEST);
        f1.add(transaction.getpanel(),BorderLayout.EAST);
        f1.add(Header.getpanel(),BorderLayout.NORTH);

        f1.setVisible((true));

    }
}

