package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PortFolio_table extends JPanel{
    DefaultTableModel tableModel;
    JPanel table1;

    public PortFolio_table() {

        String[] columns = {"ID", "Stock", "Owned Stock", "Buying Price", "Current Price", "Difference"};


        tableModel = new DefaultTableModel(getdata(), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1=new JPanel();
        JTable table=new JTable(tableModel);
        JScrollPane scrollPane=new JScrollPane(table);
        table1.setLayout(new BorderLayout());
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(4500);
        table.getColumnModel().getColumn(2).setPreferredWidth(4500);
        table.getColumnModel().getColumn(3).setPreferredWidth(4500);
        table.getColumnModel().getColumn(4).setPreferredWidth(4500);
        table.getColumnModel().getColumn(5).setPreferredWidth(4500);
        table1.setBackground(Color.GRAY);
        table1.setPreferredSize(new Dimension(10000, 150));

        table1.add(scrollPane, BorderLayout.CENTER);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                updateTableData();
                updateTablefornull();

            }
        }, 0, 200);
    }

    // Get initial table data(can be automated later have to look into it)
    public Object[][] getdata() {
        return new Object[][]{
                {1, Transaction.apple.getCompany(), Transaction.apple.getnumber(), Transaction.apple.Buying_price,
                        Transaction.apple.getCurrent_Price(), Transaction.apple.getDifference()},
                {2, Transaction.microsoft.getCompany(), Transaction.microsoft.getnumber(), Transaction.microsoft.Buying_price,
                        Transaction.microsoft.getCurrent_Price(), Transaction.microsoft.getDifference()},
                {3, Transaction.lenovo.getCompany(), Transaction.lenovo.getnumber(), Transaction.lenovo.Buying_price,
                        Transaction.lenovo.getCurrent_Price(), Transaction.lenovo.getDifference()},
                {4, Transaction.philips.getCompany(), Transaction.philips.getnumber(), Transaction.philips.Buying_price,
                        Transaction.philips.getCurrent_Price(), Transaction.philips.getDifference()},
                {5, Transaction.google.getCompany(), Transaction.google.getnumber(), Transaction.google.Buying_price,
                        Transaction.google.getCurrent_Price(), Transaction.google.getDifference()}
        };
    }

    public void updateTablefornull(){
        for(int i=0;i<tableModel.getRowCount();i++){
            int Number_of_Stocks=(int)tableModel.getValueAt(i,2);
            if(Number_of_Stocks==0){
                tableModel.setValueAt(0, i, 3);
                tableModel.setValueAt(0, i, 4);
                tableModel.setValueAt(0, i, 5);

            }
        }
    }


    private void updateTableData() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            // Update each row with the latest stock data
            String stockName = (String) tableModel.getValueAt(i, 1);
            switch (stockName) {
                case "Apple":
                    updateRow(i, Transaction.apple);
                    break;
                case "Microsoft":
                    updateRow(i, Transaction.microsoft);
                    break;
                case "Lenovo":
                    updateRow(i, Transaction.lenovo);
                    break;
                case "Philips":
                    updateRow(i, Transaction.philips);
                    break;
                case "Google":
                    updateRow(i, Transaction.google);
                    break;
            }
        }
    }

    private void updateRow(int row, Buying stock) {
        tableModel.setValueAt(stock.getnumber(), row, 2);
        tableModel.setValueAt(stock.getBuying_price(), row, 3);
        tableModel.setValueAt(stock.getCurrent_Price(), row, 4);
        tableModel.setValueAt(stock.getDifference(), row, 5);
    }
    public JPanel getPortfoliotable(){
        return table1;
    }



}
