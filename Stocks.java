package App;
import javax.swing.*;
import java.awt.*;

public class Stocks{
    public static void main(String... args){



        JLabel Portfoliolabel=new JLabel("PortFolio");
        Portfoliolabel.setFont(new Font("Arial",Font.PLAIN,40));
        Portfoliolabel.setHorizontalTextPosition(JLabel.LEFT);
        Portfoliolabel.setVerticalTextPosition(JLabel.TOP);
        Portfoliolabel.setHorizontalAlignment(JLabel.LEFT);
        Portfoliolabel.setVerticalAlignment(JLabel.TOP);

        JLabel Listinglabel=new JLabel("Listing of Companies");
        Listinglabel.setFont(new Font("Arial",Font.PLAIN,40));
        Listinglabel.setHorizontalTextPosition(JLabel.LEFT);
        Listinglabel.setVerticalTextPosition(JLabel.TOP);
        Listinglabel.setHorizontalAlignment(JLabel.LEFT);
        Listinglabel.setVerticalAlignment(JLabel.TOP);

        JLabel Transactionlabel=new JLabel("Transaction");
        Transactionlabel.setFont(new Font("Arial",Font.PLAIN,40));
        Transactionlabel.setHorizontalTextPosition(JLabel.LEFT);
        Transactionlabel.setVerticalTextPosition(JLabel.TOP);
        Transactionlabel.setHorizontalAlignment(JLabel.LEFT);
        Transactionlabel.setVerticalAlignment(JLabel.TOP);

        JLabel Headerlabel=new JLabel("Heading");
        Headerlabel.setFont(new Font("Arial",Font.PLAIN,40));
        Headerlabel.setHorizontalTextPosition(JLabel.LEFT);
        Headerlabel.setVerticalTextPosition(JLabel.TOP);
        Headerlabel.setHorizontalAlignment(JLabel.LEFT);
        Headerlabel.setVerticalAlignment(JLabel.TOP);

        JPanel Portfolio=new JPanel();
        Portfolio.add(Portfoliolabel);
        Portfolio.setBackground(Color.red);
        Portfolio.setPreferredSize(new Dimension(100,200));

        JPanel Listing =new JPanel();

        Listing.setBackground(Color.blue);
        Listing.setPreferredSize(new Dimension(850  ,30));
        Listing.add(Listinglabel);

        JPanel Transaction =new JPanel();
        Transaction.setBackground(Color.GRAY);
        Transaction.setPreferredSize(new Dimension(850  ,30));
        Transaction.add(Transactionlabel);

        JPanel Header=new JPanel();
        Header.setPreferredSize(new Dimension(100,100));
        Header.setBackground(Color.GREEN);
        Header.add(Headerlabel);


        JFrame f1=new JFrame();
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible((true));
        f1.setLayout(new BorderLayout(10,10));
        f1.setBounds(500,500,500,500);
        f1.setResizable(true);

        f1.add(Portfolio,BorderLayout.SOUTH);
        f1.add(Listing,BorderLayout.WEST);
        f1.add(Transaction,BorderLayout.EAST);
        f1.add(Header,BorderLayout.NORTH);

    }
}