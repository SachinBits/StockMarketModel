package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JPanel implements ActionListener {
    private JPanel transaction;
    private static JButton Buy = new JButton("Buy");
    private static JButton Sell = new JButton("Sell");
    private JComboBox<String> CompanyList;
    private JTextField Stocks_Num = new JTextField();
    private User user = User.user;
    private TransactionHistory transactionHistory; // Reference to the TransactionHistory

    public Transaction(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;

        String[] Company_List = {"Apple", "Microsoft", "Philips", "Google", "Lenovo"};
        CompanyList = new JComboBox<>(Company_List);
        CompanyList.setBounds(250, 300, 500, 50);

        Buy.setBounds(200, 100, 400, 100);
        Sell.setBounds(400, 100, 400, 100);

        Buy.addActionListener(this);
        Sell.addActionListener(this);
        CompanyList.addActionListener(this);

        transaction = new JPanel();
        Stocks_Num.setPreferredSize(new Dimension(100, 30));

        transaction.setBackground(Color.GRAY);
        transaction.setPreferredSize(new Dimension(850, 30));
        transaction.add(Buy);
        transaction.add(Sell);
        transaction.add(CompanyList);
        transaction.add(Stocks_Num);
    }

    public JPanel getpanel() {
        return transaction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int quantity = Integer.parseInt(Stocks_Num.getText());
            String company = CompanyList.getSelectedItem().toString();
            String date = java.time.LocalDate.now().toString(); // Current date

            if (e.getSource() == Buy) {
                if (user.buyStock(Stock.getInstance(company), quantity)) {
                    transactionHistory.addTransaction(company, "Buy", quantity, Stock.getInstance(company).getCurrentPrice(), date);
                    System.out.println("Bought Stock: " + company);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                }
            } else if (e.getSource() == Sell) {
                if (user.sellStock(Stock.getInstance(company), quantity)) {
                    transactionHistory.addTransaction(company, "Sell", quantity, Stock.getInstance(company).getCurrentPrice(), date);
                    System.out.println("Sold Stock: " + company);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Stocks");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of stocks.");
        }
    }
}