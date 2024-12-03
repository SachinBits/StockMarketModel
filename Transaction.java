package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JPanel implements ActionListener {
    private static final JButton Buy = new JButton("Buy");
    private static final JButton Sell = new JButton("Sell");
    private JComboBox<String> CompanyList;
    private JTextField Stocks_Num = new JTextField();
    private User user = User.user;
    private TransactionHistory transactionHistory;

    public Transaction(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;

        // Panel Setup
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Vertical layout for components
        setBackground(new Color(240, 240, 240));           // Light background for better contrast
        setPreferredSize(new Dimension(850, 500));

        // Title Header
        JLabel titleLabel = new JLabel("Stock Transaction", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Space between components
        add(Box.createVerticalStrut(20));

        // Company List (Dropdown)
        String[] Company_List = {"Apple", "Microsoft", "Philips", "Google", "Lenovo","Amazon","Meta","Tesla","Pfizer","Berkshire Hathaway","JPMorgan Chase","Goldman Sachs","Goldman Sachs","ExxonMobil","Toyota","Volkswagen","Walmart","IBM","Intel","Samsung"};
        CompanyList = new JComboBox<>(Company_List);
        CompanyList.setMaximumSize(new Dimension(300, 30)); // Limiting size for consistency
        CompanyList.setFont(new Font("Arial", Font.PLAIN, 14));
        CompanyList.setForeground(new Color(50, 50, 50));
        CompanyList.setBackground(Color.WHITE);
        CompanyList.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(CompanyList);

        // Space between components
        add(Box.createVerticalStrut(20));

        // Stock Number Input
        JPanel stockPanel = new JPanel();
        stockPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        stockPanel.setBackground(new Color(240, 240, 240));
        stockPanel.add(new JLabel("Enter Number of Stocks:"));
        Stocks_Num.setPreferredSize(new Dimension(100, 30));
        Stocks_Num.setFont(new Font("Arial", Font.PLAIN, 14));
        Stocks_Num.setForeground(new Color(50, 50, 50));
        Stocks_Num.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        stockPanel.add(Stocks_Num);
        add(stockPanel);

        // Space between components
        add(Box.createVerticalStrut(20));

        // Action Buttons (Buy and Sell)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        Buy.setFont(new Font("Arial", Font.BOLD, 16));
        Buy.setBackground(new Color(0, 153, 76)); // Green color for Buy
        Buy.setForeground(Color.WHITE);
        Buy.setPreferredSize(new Dimension(150, 50));
        Buy.setFocusPainted(false);
        Buy.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 51), 2));
        Buy.addActionListener(this);
        buttonPanel.add(Buy);

        Sell.setFont(new Font("Arial", Font.BOLD, 16));
        Sell.setBackground(new Color(255, 51, 51)); // Red color for Sell
        Sell.setForeground(Color.WHITE);
        Sell.setPreferredSize(new Dimension(150, 50));
        Sell.setFocusPainted(false);
        Sell.setBorder(BorderFactory.createLineBorder(new Color(204, 0, 0), 2));
        Sell.addActionListener(this);
        buttonPanel.add(Sell);

        add(buttonPanel);

        // Space at the bottom
        add(Box.createVerticalStrut(20));
    }

    public JPanel getpanel() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int quantity = Integer.parseInt(Stocks_Num.getText()); // Get the quantity from text field
            String company = CompanyList.getSelectedItem().toString(); // Get the selected company
            String date = java.time.LocalDate.now().toString(); // Current date for transaction history

            // Handle Buy action
            if (e.getSource() == Buy) {
                if (user.buyStock(Stock.getInstance(company), quantity)) {
                    transactionHistory.addTransaction(company, "Buy", quantity, Stock.getInstance(company).getCurrentPrice(), date);
                    JOptionPane.showMessageDialog(this, "Successfully bought " + quantity + " shares of " + company + ".", "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Handle Sell action
            else if (e.getSource() == Sell) {
                if (user.sellStock(Stock.getInstance(company), quantity)) {
                    transactionHistory.addTransaction(company, "Sell", quantity, Stock.getInstance(company).getCurrentPrice(), date);
                    JOptionPane.showMessageDialog(this, "Successfully sold " + quantity + " shares of " + company + ".", "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Stocks!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of stocks.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}