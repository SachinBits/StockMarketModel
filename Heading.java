package App;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Heading extends JPanel{
    JPanel Header;
    JLabel Headerlabel1=new JLabel();;
    User user = User.user;

    Heading(){
        setLayout(new BorderLayout(10,10));
        JLabel Headerlabel=new JLabel("Stock Trading");
        Headerlabel.setFont(new Font("Arial",Font.PLAIN,40));
        Headerlabel.setHorizontalAlignment(JLabel.CENTER);
        Headerlabel.setVerticalAlignment(JLabel.CENTER);

        Header=new JPanel();
        Header.setLayout(new BorderLayout());

        //Header.add(Headerlabel, BorderLayout.NORTH);

        Header.add(Box.createVerticalStrut(20), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JLabel balanceLabel = new JLabel("Balance: $"+user.getBalance(), JLabel.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel profitLabel = new JLabel("Profit: $"+user.getProfit(), JLabel.CENTER);
        profitLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel unrealizedProfitLabel = new JLabel("Unrealized Profit: $"+user.getUnrealizedProfit(), JLabel.CENTER);
        unrealizedProfitLabel.setFont(new Font("Arial", Font.BOLD, 20));

        bottomPanel.add(balanceLabel);
        bottomPanel.add(profitLabel);
        bottomPanel.add(unrealizedProfitLabel);

        Header.add(bottomPanel, BorderLayout.SOUTH);


        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                balanceLabel.setText("Balance: $" + (int) user.getBalance());
                profitLabel.setText("Profit: $" + (int) user.getProfit());
                unrealizedProfitLabel.setText("Unrealized Profit: $" + (int) user.getUnrealizedProfit());
        }},0,100);
    }

    public JPanel getpanel(){
        return Header;
    }
    }
