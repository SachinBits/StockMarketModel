package App;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Analytics {
    private JPanel analyticsPanel;
    private JLabel leaderboardLabel;
    private DefaultTableModel tableModel;
    private JTable leaderboardTable;
    private ArrayList<Object[]> companyRows = new ArrayList<>();

    public Analytics() {

        analyticsPanel = new JPanel();
        analyticsPanel.setLayout(new BorderLayout(10, 10));
        analyticsPanel.setBackground(Color.WHITE);

        leaderboardLabel = new JLabel("📈 Stock Leaderboard", SwingConstants.CENTER);
        leaderboardLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        leaderboardLabel.setForeground(new Color(0, 102, 204));
        leaderboardLabel.setOpaque(true);
        leaderboardLabel.setBackground(new Color(240, 240, 240));
        leaderboardLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        analyticsPanel.add(leaderboardLabel, BorderLayout.NORTH);


        String[] columnNames = {"Rank", "Company", "Stock Price"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setRowHeight(40);
        leaderboardTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
        leaderboardTable.setGridColor(new Color(230, 230, 230));

        JTableHeader header = leaderboardTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(new Color(200, 200, 200));
        header.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        analyticsPanel.add(scrollPane, BorderLayout.CENTER);

        Listing companyData = new Listing();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateLeaderboard(companyData.gettable());
            }
        }, 0, 2000);
    }
    private void updateLeaderboard(javax.swing.table.TableModel tableModelFromListing) {
        companyRows.clear();


        for (int i = 0; i < 10; i++) {
            Object[] row = new Object[tableModelFromListing.getColumnCount()];
            for (int j = 0; j < tableModelFromListing.getColumnCount(); j++) {
                row[j] = tableModelFromListing.getValueAt(i, j);
            }
            companyRows.add(row);
        }


        companyRows.sort((row1, row2) -> {
            Double price1 = Double.parseDouble(row1[2].toString());
            Double price2 = Double.parseDouble(row2[2].toString());
            return price2.compareTo(price1);
        });


        SwingUtilities.invokeLater(() -> refreshLeaderboardTable(companyRows));
    }

    private void refreshLeaderboardTable(ArrayList<Object[]> sortedRows) {
        tableModel.setRowCount(0);

        for (Object[] row : sortedRows) {
            tableModel.addRow(row);
        }
    }

    public JPanel getPanel() {
        return analyticsPanel;
    }
}