package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Listing extends JPanel  {

    JPanel List;
    GraphFrame graphFrame;
    private DefaultTableModel tablemodel;
    public static Stock apple = new Stock("Apple",100);
    public static Stock microsoft = new Stock("Microsoft",100);
    public static Stock lenovo = new Stock("Lenovo",100);
    public static Stock philips = new Stock("Philips",100);
    public static Stock google = new Stock("Google",100);
    public static Stock amazon = new Stock("Amazon",100);
    public static Stock meta = new Stock("Meta",100);
    public static Stock tesla = new Stock("Tesla",100);
    public static Stock Pfizer = new Stock("Pfizer",100);
    public static Stock BerkshireHathaway = new Stock("Berkshire Hathaway",100);
    public static Stock JPMorganChase = new Stock("JPMorgan Chase",100);
    public static Stock GoldmanSachs = new Stock("Goldman Sachs",100);
    public static Stock Nestlé = new Stock("Nestlé",100);
    public static Stock ExxonMobil = new Stock("ExxonMobil",100);
    public static Stock Toyota = new Stock("Toyota",100);
    public static Stock Volkswagen = new Stock("Volkswagen",100);
    public static Stock Walmart = new Stock("Walmart",100);
    public static Stock IBM = new Stock("IBM",100);
    public static Stock Intel = new Stock("Intel",100);
    public static Stock Samsung = new Stock("Samsung",100);



    static GraphsMain generate_apple=new GraphsMain(apple.stock_history);
    static GraphsMain generate_microsoft=new GraphsMain(microsoft.stock_history);
    static GraphsMain generate_lenovo=new GraphsMain(lenovo.stock_history);
    static GraphsMain generate_philips=new GraphsMain(philips.stock_history);
    static GraphsMain generate_google=new GraphsMain(google.stock_history);
    static GraphsMain generate_amazon=new GraphsMain(amazon.stock_history);
    static GraphsMain generate_meta=new GraphsMain(meta.stock_history);
    static GraphsMain generate_tesla=new GraphsMain(tesla.stock_history);
    static GraphsMain generate_Pfizer=new GraphsMain(Pfizer.stock_history);
    static GraphsMain generate_BerkshireHathaway=new GraphsMain(BerkshireHathaway.stock_history);
    static GraphsMain generate_JPMorganChase=new GraphsMain(JPMorganChase.stock_history);
    static GraphsMain generate_GoldmanSachs=new GraphsMain(GoldmanSachs.stock_history);
    static GraphsMain generate_Nestlé=new GraphsMain(Nestlé.stock_history);
    static GraphsMain generate_ExxonMobil=new GraphsMain(ExxonMobil.stock_history);
    static GraphsMain generate_Toyota=new GraphsMain(Toyota.stock_history);
    static GraphsMain generate_Volkswagen=new GraphsMain(Volkswagen.stock_history);
    static GraphsMain generate_Walmart=new GraphsMain(Walmart.stock_history);
    static GraphsMain generate_IBM=new GraphsMain(IBM.stock_history);
    static GraphsMain generate_Intel=new GraphsMain(Intel.stock_history);
    static GraphsMain generate_Samsung=new GraphsMain(Samsung.stock_history);




    public JTable Table;

    Listing() {

        String[] columns = {"ID", "Companies", "Current Stock Price"};

        Object[][] data = {{1, "Apple", 0.0},
                {2, "Microsoft", 0.0},
                {3, "Lenovo", 0.0},
                {4, "Philips", 0.0},
                {5, "Google", 0.0},
                {6, "Amazon", 0.0},
                {7, "Meta", 0.0},
                {8, "Tesla", 0.0},
                {9, "Pfizer", 0.0},
                {10, "Berkshire Hathaway", 0.0},
                {11, "JPMorgan Chase", 0.0},
                {12, "Goldman Sachs", 0.0},
                {13, "Nestlé", 0.0},
                {14, "ExxonMobil", 0.0},
                {15, "Toyota", 0.0},
                {16, "Volkswagen", 0.0},
                {17, "Walmart", 0.0},
                {18, "IBM", 0.0},
                {19, "Intel", 0.0},
                {20, "Samsung", 0.0},



        };

        tablemodel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List = new JPanel();
        List.setLayout(new BorderLayout());

        Table = new JTable(tablemodel);
        PortFolio_table tb=new PortFolio_table();
        tb.setrowwidth(Table,25);

        JScrollPane scrollPane = new JScrollPane(Table);
        settablewidth(0,200);
        for(int i=1;i<Table.getColumnCount();i++){
            settablewidth(i,400);
        }

        List.setBackground(Color.blue);
        List.setPreferredSize(new Dimension(850, 30));
        List.add(scrollPane, BorderLayout.CENTER);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                updatetable();



            }
        }, 0, 100);

        Table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row=Table.rowAtPoint(e.getPoint());

                opengraph(row);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void opengraph(int i){
        try{
            switch (i) {
                case 0:
                    graphFrame = new GraphFrame(generate_apple);
                    break;
                case 1:
                    graphFrame = new GraphFrame(generate_microsoft);
                    break;
                case 2:
                    graphFrame = new GraphFrame(generate_lenovo);
                    break;
                case 3:
                    graphFrame = new GraphFrame(generate_philips);
                    break;
                case 4:
                    graphFrame = new GraphFrame(generate_google);
                    break;
                case 5:
                    graphFrame = new GraphFrame(generate_amazon);
                    break;
                case 6:
                    graphFrame = new GraphFrame(generate_meta);
                    break;
                case 7:
                    graphFrame = new GraphFrame(generate_tesla);
                    break;
                case 8:
                    graphFrame = new GraphFrame(generate_Pfizer);
                    break;
                case 9:
                    graphFrame = new GraphFrame(generate_BerkshireHathaway);
                    break;
                case 10:
                    graphFrame = new GraphFrame(generate_JPMorganChase);
                    break;
                case 11:
                    graphFrame = new GraphFrame(generate_GoldmanSachs);
                    break;
                case 12:
                    graphFrame = new GraphFrame(generate_Nestlé);
                    break;
                case 13:
                    graphFrame = new GraphFrame(generate_ExxonMobil);
                    break;
                case 14:
                    graphFrame = new GraphFrame(generate_Toyota);
                    break;
                case 15:
                    graphFrame = new GraphFrame(generate_Volkswagen);
                    break;
                case 16:
                    graphFrame = new GraphFrame(generate_Walmart);
                    break;
                case 17:
                    graphFrame = new GraphFrame(generate_IBM);
                    break;
                case 18:
                    graphFrame = new GraphFrame(generate_Intel);
                    break;
                case 19:
                    graphFrame = new GraphFrame(generate_Samsung);
                    break;

                default:

            }
        }
        catch(Exception e){
            System.out.println("Yep that's the limit");
        }
    }

    public void updatetable(){
        tablemodel.setValueAt(generate_apple.getlatestvalue(generate_apple.b),0,2);
        tablemodel.setValueAt(generate_microsoft.getlatestvalue(generate_microsoft.b),1,2);
        tablemodel.setValueAt(generate_lenovo.getlatestvalue(generate_lenovo.b),2,2);
        tablemodel.setValueAt(generate_philips.getlatestvalue(generate_philips.b),3,2);
        tablemodel.setValueAt(generate_google.getlatestvalue(generate_google.b),4,2);
        tablemodel.setValueAt(generate_amazon.getlatestvalue(generate_amazon.b),5,2);
        tablemodel.setValueAt(generate_meta.getlatestvalue(generate_meta.b),6,2);
        tablemodel.setValueAt(generate_tesla.getlatestvalue(generate_tesla.b),7,2);
        tablemodel.setValueAt(generate_Pfizer.getlatestvalue(generate_Pfizer.b),8,2);
        tablemodel.setValueAt(generate_BerkshireHathaway.getlatestvalue(generate_BerkshireHathaway.b),9,2);
        tablemodel.setValueAt(generate_JPMorganChase.getlatestvalue(generate_JPMorganChase.b),10,2);
        tablemodel.setValueAt(generate_GoldmanSachs.getlatestvalue(generate_GoldmanSachs.b),11,2);
        tablemodel.setValueAt(generate_Nestlé.getlatestvalue(generate_Nestlé.b),12,2);
        tablemodel.setValueAt(generate_ExxonMobil.getlatestvalue(generate_ExxonMobil.b),13,2);
        tablemodel.setValueAt(generate_Toyota.getlatestvalue(generate_Toyota.b),14,2);
        tablemodel.setValueAt(generate_Volkswagen.getlatestvalue(generate_Volkswagen.b),15,2);
        tablemodel.setValueAt(generate_Walmart.getlatestvalue(generate_Walmart.b),16,2);
        tablemodel.setValueAt(generate_IBM.getlatestvalue(generate_IBM.b),17,2);
        tablemodel.setValueAt(generate_Intel.getlatestvalue(generate_Intel.b),18,2);
        tablemodel.setValueAt(generate_Samsung.getlatestvalue(generate_Samsung.b),19,2);



    }
    public JPanel getpanel()
    {
        return List;
    }
    public void settablewidth(int column,int width){
        Table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }
    public TableModel gettable(){
        return tablemodel;
    }

}
