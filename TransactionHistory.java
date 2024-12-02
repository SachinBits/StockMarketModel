package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TransactionHistory {
    private JPanel historyPanel;
    private DefaultTableModel tableModel;
    private JTable historyTable;

    public TransactionHistory() {
        historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());
        historyPanel.setPreferredSize(new Dimension(600, 400));

        String[] columns = {"ID", "Company", "Transaction Type", "Quantity", "Price", "Date"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        historyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return historyPanel;
    }

    public void addTransaction(String company, String type, int quantity, double price, String date) {
        int id = tableModel.getRowCount() + 1;
        tableModel.addRow(new Object[]{id, company, type, quantity, price, date});
    }
}
