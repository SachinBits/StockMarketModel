package App;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Random_News {


    public void positive_news(String Company){


        String[] positive_headlines = {
                "[Company] reports record-breaking revenue for the quarter.",
                "[Company] announces dividend increase, delighting investors.",
                "Global market trends push [Company] stocks higher.",
                "[Company] gains from rising demand in emerging markets.",
                "[Company] unveils new flagship product, stock jumps.",
                "[Company]'s breakthrough technology disrupts the industry.",
                "Analysts praise [Company]'s latest innovation as a game-changer.",
                "[Company] expands product line to cater to new markets.",
                "[Company] announces strategic partnership with [Partner Company].",
                "[Company] acquires [Target Company] to strengthen market position.",
                "Merger talks between [Company] and [Other Company] gain momentum.",
                "[Company] ranked among the best places to work in 2024.",
                "[Company] introduces new diversity and inclusion initiatives.",
                "[Company] commits to achieving carbon neutrality by 2030.",
                "[Company] launches community initiative to support education.",
                "[Company] wins award for corporate social responsibility efforts.",
                "[Company]'s stock hits an all-time high on strong demand.",
                "[Company] included in major stock index, boosting investor interest."
        };

        Random rand=new Random();
        int news_no=rand.nextInt(positive_headlines.length);
        String news=positive_headlines[news_no].replace("[Company]",Company);
        JLabel pos_label=new JLabel(news);
        pos_label.setFont(new Font("Arial", Font.ITALIC,20));

        JOptionPane optionPane=new JOptionPane(pos_label);
        JDialog dialog=optionPane.createDialog("Stock Increase");
        dialog.setSize(1000,200);
        dialog.setLocation(1000,800);

        dialog.setVisible(true);

    }

    public void negative_news(String Company){

        String[] negative_headlines = {
                "[Company]'s earnings fall short of expectations, stock dips.",
                "[Company] posts unexpected loss, citing supply chain issues.",
                "Economic slowdown weighs on [Company]'s growth prospects.",
                "Interest rate changes impact [Company]'s financial outlook.",
                "[Company] faces investigation over alleged misconduct.",
                "[Company]'s CEO steps down amid controversy.",
                "Court ruling impacts [Company]'s operations in key markets.",
                "[Company] settles lawsuit for $[X] million.",
                "[Company] announces major layoffs to cut costs.",
                "[Company] faces backlash over workplace conditions.",
                "[Company] criticized for environmental practices.",
                "[Company]'s stock volatility raises investor concerns.",
                "[Company] shares plummet after analysts downgrade."
        };
        Random rand=new Random();
        int news_no=rand.nextInt(negative_headlines.length);
        String news=negative_headlines[news_no].replace("[Company]",Company);
        JLabel neg_label=new JLabel(news);
        JOptionPane optionPane=new JOptionPane(neg_label);
        JDialog dialog=optionPane.createDialog("Stock Decrease");
        dialog.setLocation(1000,500);
        dialog.setVisible(true);

    }


}
