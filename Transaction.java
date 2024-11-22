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



    Transaction() {

        String[] Company_List = {"Apple", "Microsoft", "Philips", "Google", "Lenovo"};
        CompanyList = new JComboBox(Company_List);

        Buy.setBounds(50, 50, 50, 50);

        Sell.setBounds(50, 50, 50, 50);

        Buy.addActionListener(this);
        Sell.addActionListener(this);
        CompanyList.addActionListener(this);


        transaction = new JPanel();


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


        if (e.getSource() == Buy) {
            switch (company) {
                case ("Apple"):
                    apple.gostuff(number);
                    break;

                case ("Microsoft"):
                    microsoft.gostuff(number);
                    break;
                case ("Lenovo"):
                    lenovo.gostuff(number);
                    break;
                case ("Philips"):
                    philips.gostuff(number);
                    break;
                case ("Google"):
                    google.gostuff(number);
                    break;
            }

        }
        else if (e.getSource() == Sell) {
            switch (company) {
                case ("Apple"):
                    if(apple.getnumber()>0){

                        apple.gostuff(-number);

                    }

                    break;

                case ("Microsoft"):
                    if(microsoft.getnumber()>0){
                        microsoft.gostuff(-number);

                    }

                    break;
                case ("Lenovo"):
                    if(lenovo.getnumber()>0){
                        lenovo.gostuff(-number);
                    }

                    break;
                case ("Philips"):
                    if(philips.getnumber()>0){
                    philips.gostuff(-number);

                    }

                    break;
                case ("Google"):
                    if(google.getnumber()>0){
                        google.gostuff(-number);

                    }

                    break;
            }


        }




    }
}

