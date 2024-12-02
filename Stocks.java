package App;

import javax.swing.*;
import java.awt.*;

public class Stocks{

    public static void main(String... args) {
        JFrame Main=new JFrame();
        Main.setResizable(true);
        JPanel f1=new JPanel();
        JPanel f2= new JPanel();
        Main.setLayout(new BorderLayout(100,100));
        Main.setBounds(500,500,1000,500);
        Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLayout(new BorderLayout(10,10));
        f1.setBounds(500,500,500,500);

        f2.setLayout(new BorderLayout(10,10));
        f2.setBounds(500,500,500,500);

        JTabbedPane tabbedpane=new JTabbedPane();

        PortFolio portfolio=new PortFolio();
        Heading Header=new Heading();
        Listing List=new   Listing();
        PortFolio_table portFolioTable=new PortFolio_table();
        TransactionHistory transactionHistory = new TransactionHistory();
        Transaction transaction = new Transaction(transactionHistory);

        Analytics analytics=new Analytics();

        analytics.leaderboard();

        f1.add(portFolioTable.getPortfoliotable(),BorderLayout.SOUTH);
        f1.add(List.getpanel(), BorderLayout.WEST);
        f1.add(transaction.getpanel(),BorderLayout.EAST);
        f1.add(Header.getpanel(),BorderLayout.NORTH);

        f2.add(analytics.getPanel());

        tabbedpane.add("Trading",f1);
        tabbedpane.add("Analytics",f2);
        tabbedpane.add("Transaction History", transactionHistory.getPanel());

        Main.add(tabbedpane);
        Main.setVisible(true);
    }
}