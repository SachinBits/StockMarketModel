package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


import static App.Listing.*;

public class PortFolio_table extends JPanel{
    DefaultTableModel tableModel;
    JPanel table1;
    JTable table;
    User user = User.user;


    public PortFolio_table() {

        String[] columns = {"ID", "Stock", "Owned Stock", "Buying Price", "Current Price","Difference", "Unrealized Profit"};


        tableModel = new DefaultTableModel(getdata(), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table=new JTable(tableModel);
        JScrollPane scrollPane=new JScrollPane(table);

        table1=new JPanel();


        table1.setLayout(new BorderLayout());




        settablewidth(0,350);
        for(int i=1;i<table.getColumnCount();i++){
            settablewidth(i,2500);
        }
        setrowwidth(table,25);

        table1.setBackground(Color.GRAY);
        table1.setPreferredSize(new Dimension(1000, 200));
        table.setPreferredSize(new Dimension(1000,1000));

        table1.add(scrollPane, BorderLayout.CENTER);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                updateTableData();
                //updateTablefornull();

            }
        }, 0, 100);
    }

    // Get initial table data(can be automated later have to look into it)
    public Object[][] getdata() {
        return new Object[][]{
                {1, apple.getName(), user.getOwnedStockCount(apple), user.getBuyingPrice(apple),
                        apple.getCurrentPrice(), user.getDifference(apple)},
                {2, microsoft.getName(), user.getOwnedStockCount(microsoft), user.getBuyingPrice(microsoft),
                        microsoft.getCurrentPrice(), user.getDifference(microsoft)},
                {3, lenovo.getName(), user.getOwnedStockCount(lenovo), user.getBuyingPrice(lenovo),
                        lenovo.getCurrentPrice(), user.getDifference(lenovo)},
                {4, philips.getName(), user.getOwnedStockCount(philips), user.getBuyingPrice(philips),
                        philips.getCurrentPrice(), user.getDifference(philips)},
                {5, google.getName(), user.getOwnedStockCount(google), user.getBuyingPrice(google),
                        google.getCurrentPrice(), user.getDifference(google)},
                {6, amazon.getName(), user.getOwnedStockCount(amazon), user.getBuyingPrice(amazon),
                        amazon.getCurrentPrice(), user.getDifference(amazon)},
                {7, meta.getName(), user.getOwnedStockCount(meta), user.getBuyingPrice(meta),
                        meta.getCurrentPrice(), user.getDifference(meta)},
                {8, tesla.getName(), user.getOwnedStockCount(tesla), user.getBuyingPrice(tesla),
                        tesla.getCurrentPrice(), user.getDifference(tesla)},
                {9, Pfizer.getName(), user.getOwnedStockCount(Pfizer), user.getBuyingPrice(Pfizer),
                        Pfizer.getCurrentPrice(), user.getDifference(Pfizer)},
                {10, BerkshireHathaway.getName(), user.getOwnedStockCount(BerkshireHathaway), user.getBuyingPrice(BerkshireHathaway),
                        BerkshireHathaway.getCurrentPrice(), user.getDifference(BerkshireHathaway)},
                {11, JPMorganChase.getName(), user.getOwnedStockCount(JPMorganChase), user.getBuyingPrice(JPMorganChase),
                        JPMorganChase.getCurrentPrice(), user.getDifference(JPMorganChase)},
                {12, GoldmanSachs.getName(), user.getOwnedStockCount(GoldmanSachs), user.getBuyingPrice(GoldmanSachs),
                        GoldmanSachs.getCurrentPrice(), user.getDifference(GoldmanSachs)},
                {13, Nestlé.getName(), user.getOwnedStockCount(Nestlé), user.getBuyingPrice(Nestlé),
                        Nestlé.getCurrentPrice(), user.getDifference(Nestlé)},
                {14, ExxonMobil.getName(), user.getOwnedStockCount(ExxonMobil), user.getBuyingPrice(ExxonMobil),
                        ExxonMobil.getCurrentPrice(), user.getDifference(ExxonMobil)},
                {15, Toyota.getName(), user.getOwnedStockCount(Toyota), user.getBuyingPrice(Toyota),
                        Toyota.getCurrentPrice(), user.getDifference(Toyota)},
                {16, Volkswagen.getName(), user.getOwnedStockCount(Volkswagen), user.getBuyingPrice(Volkswagen),
                        Volkswagen.getCurrentPrice(), user.getDifference(Volkswagen)},
                {17, Walmart.getName(), user.getOwnedStockCount(Walmart), user.getBuyingPrice(Walmart),
                        Walmart.getCurrentPrice(), user.getDifference(Walmart)},
                {18, IBM.getName(), user.getOwnedStockCount(IBM), user.getBuyingPrice(IBM),
                        IBM.getCurrentPrice(), user.getDifference(IBM)},
                {19, Intel.getName(), user.getOwnedStockCount(Intel), user.getBuyingPrice(Intel),
                        Intel.getCurrentPrice(), user.getDifference(Intel)},
                {20, Samsung.getName(), user.getOwnedStockCount(Samsung), user.getBuyingPrice(Samsung),
                        Samsung.getCurrentPrice(), user.getDifference(Samsung)},

        };
    }


    private void updateTableData() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            // Update each row with the latest stock data
            String stockName = (String) tableModel.getValueAt(i, 1);
            updateRow(i,Stock.getInstance(stockName));
        }
    }



    private void updateRow(int row, Stock stock) {
        tableModel.setValueAt(user.getOwnedStockCount(stock), row, 2);
        tableModel.setValueAt(user.getBuyingPrice(stock), row, 3);
        tableModel.setValueAt(stock.getCurrentPrice(), row, 4);
        tableModel.setValueAt(user.getDifference(stock), row, 5);
        tableModel.setValueAt(user.getUnrealizedProfit(stock), row, 6);
    }

    public JPanel getPortfoliotable(){
        return table1;
    }
    public void settablewidth(int column,int width){
        table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }
    public void setrowwidth(JTable tabel,int height){
        tabel.setRowHeight(height);
    }



}
