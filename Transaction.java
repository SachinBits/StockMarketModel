package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JPanel implements ActionListener {
    JPanel transaction;
    static JButton Buy = new JButton("Buy");
    static JButton Sell = new JButton("Sell");
    JComboBox CompanyList;
    JTextField Stocks_Num = new JTextField();
//    static Buying apple = new Buying(0, "Apple");
    static Buying microsoft = new Buying(0, "Microsoft");
    static Buying lenovo = new Buying(0, "Lenovo");
    static Buying philips = new Buying(0, "Philips");
    static Buying google = new Buying(0, "Google");
    double cost_of_stocks;
    User user = User.user;

    Transaction() {

        String[] Company_List = {"Apple", "Microsoft", "Philips", "Google", "Lenovo"};
        CompanyList = new JComboBox(Company_List);
        CompanyList.setBounds(250,300,500,50);

        Buy.setBounds(200, 50, 200, 50);
        Sell.setBounds(400, 50, 200, 50);

        Buy.addActionListener(this);
        Sell.addActionListener(this);
        CompanyList.addActionListener(this);

        transaction = new JPanel();


        Stocks_Num.setPreferredSize(new Dimension(100,30));

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
        int quantity = Integer.parseInt(Stocks_Num.getText());
        String company = CompanyList.getSelectedItem().toString();
        if (e.getSource() == Buy) {
            if(user.buyStock(Stock.getInstance(company),quantity)) {
                System.out.println("Bought Stock " + company);
            }
            else {
                JOptionPane.showMessageDialog(this,"Insufficient Balance");
            }
        }
        else if (e.getSource() == Sell) {
            if(user.sellStock(Stock.getInstance(company),quantity)) {

                System.out.println("Bought Stock " + company);
            }
            else {
                JOptionPane.showMessageDialog(this,"Insufficient Stocks");
            }
        }
        }

}
