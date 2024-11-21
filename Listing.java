package App;

import javax.swing.*;
import java.awt.*;

public class Listing extends JPanel {
    JPanel List;
    JPanel Companies;
    Listing(){
        JLabel Listinglabel=new JLabel("Listing of Companies");
        Listinglabel.setFont(new Font("Arial",Font.PLAIN,40));
        Listinglabel.setHorizontalTextPosition(JLabel.LEFT);
        Listinglabel.setVerticalTextPosition(JLabel.TOP);
        Listinglabel.setHorizontalAlignment(JLabel.LEFT);
        Listinglabel.setVerticalAlignment(JLabel.TOP);

        Companies=new JPanel();



        List=new JPanel();

        List.setBackground(Color.blue);
        List.setPreferredSize(new Dimension(850  ,30));
        List.add(Listinglabel);




    }

    public JPanel getpanel(){
        return List;
    }
}
