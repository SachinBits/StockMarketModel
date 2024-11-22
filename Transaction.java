package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JPanel implements ActionListener {
    JPanel transaction;
    static JButton Buy=new JButton("Buy");
    static JButton Sell=new JButton("Sell");
    JComboBox CompanyList;
    JTextField Stocks_Num=new JTextField();
//    Buying apple;
//    Buying microsoft;
//    Buying lenovo;
//    Buying philips;
//    Buying google;



    Transaction(){

        String[] Company_List={"Apple","MicroSoft","Philips","Google","Lenovo"  };
        CompanyList =new JComboBox(Company_List);

        Buy.setBounds(50,50,50,50);

        Sell.setBounds(50,50,50,50);

        Buy.addActionListener(this);
        Sell.addActionListener(this);
        CompanyList.addActionListener(this);


        transaction=new JPanel();


        transaction.setBackground(Color.GRAY);
        transaction.setPreferredSize(new Dimension(850  ,30));
        transaction.add(Buy);
        transaction.add(Sell);
        transaction.add(CompanyList);
        transaction.add(Stocks_Num);

    }
    public JPanel getpanel(){
        return transaction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int number=Integer.parseInt(Stocks_Num.getText());
        String company=CompanyList.getSelectedItem().toString();




        if(e.getSource()==Buy){
            Buying buying=new Buying(number,company,e);
        }




    }
}
