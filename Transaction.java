package App;

import javax.swing.*;
import java.awt.*;

public class Transaction extends JPanel{
    JPanel transaction;
    Transaction(){
        JLabel Transactionlabel=new JLabel("Transaction");
        Transactionlabel.setFont(new Font("Arial",Font.PLAIN,40));
        Transactionlabel.setHorizontalTextPosition(JLabel.LEFT);
        Transactionlabel.setVerticalTextPosition(JLabel.TOP);
        Transactionlabel.setHorizontalAlignment(JLabel.LEFT);
        Transactionlabel.setVerticalAlignment(JLabel.TOP);

        transaction=new JPanel();

        transaction.setBackground(Color.GRAY);
        transaction.setPreferredSize(new Dimension(850  ,30));
        transaction.add(Transactionlabel);

    }
    public JPanel getpanel(){
        return transaction;
    }



}
