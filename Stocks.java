package App;

import javax.swing.*;
import java.awt.*;

public class Stocks {
    public static void main(String... args) {
        JFrame Main = new JFrame();
        Main.setResizable(true);
        Main.setLayout(new BorderLayout(100, 100));
        Main.setBounds(500, 500, 1000, 500);
        Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        PortFolio portfolio = new PortFolio();
        Heading header = new Heading();
        Listing listing = new Listing();
        PortFolio_table portfolioTable = new PortFolio_table();
        Analytics analytics = new Analytics();
        TransactionHistory transactionHistory = new TransactionHistory();
        Transaction transaction = new Transaction(transactionHistory);

        analytics.leaderboard();

        JPanel tradingPanel = new JPanel(new BorderLayout());
        tradingPanel.add(portfolioTable.getPortfoliotable(), BorderLayout.SOUTH);
        tradingPanel.add(listing.getpanel(), BorderLayout.WEST);
        tradingPanel.add(transaction.getpanel(), BorderLayout.EAST);
        tradingPanel.add(header.getpanel(), BorderLayout.NORTH);

        JPanel analyticsPanel = new JPanel(new BorderLayout());
        analyticsPanel.add(analytics.getPanel(), BorderLayout.WEST);

        tabbedPane.add("Trading", tradingPanel);
        tabbedPane.add("Analytics", analyticsPanel);
        tabbedPane.add("Transaction History", transactionHistory.getPanel());

        Main.add(tabbedPane);
        Main.setVisible(true);
    }
}