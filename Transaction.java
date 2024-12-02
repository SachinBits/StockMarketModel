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
    static Buying apple = new Buying(0, "Apple");
    static Buying microsoft = new Buying(0, "Microsoft");
    static Buying lenovo = new Buying(0, "Lenovo");
    static Buying philips = new Buying(0, "Philips");
    static Buying google = new Buying(0, "Google");
    double cost_of_stocks;

    Transaction() {

        String[] Company_List = {"Apple", "Microsoft", "Philips", "Google", "Lenovo"};
        CompanyList = new JComboBox(Company_List);
        CompanyList.setBounds(250,300,200,50);

        Buy.setBounds(50, 50, 200, 50);
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
        int number = Integer.parseInt(Stocks_Num.getText());
        String company = CompanyList.getSelectedItem().toString();

        if (e.getSource() == Buy ) {
            switch (company) {
                case ("Apple"):
                    if(check_Valid_transaction(number,Listing.generate_apple,Listing.generate_apple.b)) {
                        apple.trade(number, "Buy");
                    }
                    break;
                case ("Microsoft"):
                    if(check_Valid_transaction(number,Listing.generate_microsoft,Listing.generate_microsoft.b)) {
                        microsoft.trade(number, "Buy");
                    }
                    break;
                case ("Lenovo"):
                    if(check_Valid_transaction(number,Listing.generate_lenovo,Listing.generate_lenovo.b)) {
                        lenovo.trade(number, "Buy");
                    }
                    break;
                case ("Philips"):
                    if(check_Valid_transaction(number,Listing.generate_philips,Listing.generate_philips.b)) {
                        philips.trade(number, "Buy");
                    }
                    break;
                case ("Google"):
                    if(check_Valid_transaction(number,Listing.generate_google,Listing.generate_google.b)) {
                        google.trade(number, "Buy");
                    }
                    break;
            }
        }
        else if (e.getSource() == Sell) {
            switch (company) {
                case ("Apple"):
                    if(apple.getnumber()>=number){
                        apple.trade(-number, "Sell");
                    }
                    break;
                case ("Microsoft"):
                    if(microsoft.getnumber()>=number){
                        microsoft.trade(-number, "Sell");
                    }
                    break;
                case ("Lenovo"):
                    if(lenovo.getnumber()>=number){
                        lenovo.trade(-number, "Sell");
                    }
                    break;
                case ("Philips"):
                    if(philips.getnumber()>=number){
                    philips.trade(-number,"Sell");
                    }
                    break;
                case ("Google"):
                    if(google.getnumber()>=number){
                        google.trade(-number, "Sell");
                    }
                    break;
            }
        }
    }
    public boolean check_Valid_transaction(int number,GraphsMain func,double[] og_array){
        cost_of_stocks = number * func.getlatestvalue(og_array);
        if(Buying.Balance>=cost_of_stocks){

            return true;
        }
        else {
            JOptionPane.showMessageDialog(this,"Insufficient Balance");
            return false;
        }
    }


}

