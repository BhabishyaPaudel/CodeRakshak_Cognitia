import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

public class ia extends JFrame {
    private JTable itemsTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public ia() {
        // Frame setup with increased size
        setTitle("Available Items - MyNITM Lost & Found System");
        setSize(1200, 800);
        setLocation(250, 50);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout(10, 10));

        JButton backButton = new JButton("<---");
        backButton.setBounds(1100, 10, 80, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new signin().setVisible(true);
            }
        });
        add(backButton);

        // Enhanced header panel with better contrast
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Darker blue for better contrast
        headerPanel.setPreferredSize(new Dimension(1200, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel titleLabel = new JLabel("MyNITM - Lost & Found Items Database");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Table setup with all columns
        String[] columns = {
                "Item ID",
                "Item Name",
                "Model/Brand",
                "Description",
                "Color",
                "Found Location",
                "Image URL",
                "Finder's Name",
                "Finder's Contact",
                "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            // Override getColumnClass to ensure proper rendering of integers
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };

        itemsTable = new JTable(tableModel);
        setupTableAppearance();

        // Add scroll pane with custom styling
        scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Enhanced bottom panel with better visibility
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 245, 245));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton refreshButton = createStyledButton("Refresh Items");
        refreshButton.addActionListener(e -> loadItemsData());

        JButton exportButton = createStyledButton("Export to Excel");
        exportButton.addActionListener(e -> exportToExcel());

        bottomPanel.add(refreshButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        bottomPanel.add(exportButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Initial data load
        loadItemsData();
    }

    private void setupTableAppearance() {
        // Enhanced table styling with better visibility
        itemsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        itemsTable.setRowHeight(35);
        itemsTable.setIntercellSpacing(new Dimension(10, 5));
        itemsTable.setFillsViewportHeight(true);
        itemsTable.setGridColor(new Color(200, 200, 200));
        itemsTable.setShowGrid(true);

        // Custom header styling with better contrast
        JTableHeader header = itemsTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Column widths
        TableColumnModel columnModel = itemsTable.getColumnModel();
        int[] columnWidths = { 60, 150, 150, 200, 100, 150, 150, 150, 150, 100 };
        for (int i = 0; i < columnWidths.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(columnWidths[i]);
            // Center align all columns
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

        // Custom cell renderer with better visibility
        itemsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(new Color(51, 153, 255));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? new Color(240, 240, 250) : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }

                // Add padding to cell contents
                setBorder(BorderFactory.createCompoundBorder(
                        getBorder(),
                        BorderFactory.createEmptyBorder(2, 5, 2, 5)));

                return c;
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(00, 00, 00));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 82, 164));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 102, 204));
            }
        });

        return button;
    }

    private void loadItemsData() {
        tableModel.setRowCount(0);

        try {
            conn c = new conn();
            String query = "SELECT item_id, item_name, model_brand, description, color, " +
                    "found_location, image_url, finder_name, finder_contact, item_status " +
                    "FROM founditem WHERE item_status = 'In Storage' " +
                    "ORDER BY item_id DESC";

            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("model_brand"),
                        rs.getString("description"),
                        rs.getString("color"),
                        rs.getString("found_location"),
                        rs.getString("image_url"),
                        rs.getString("finder_name"),
                        rs.getString("finder_contact"),
                        rs.getString("item_status")
                };
                tableModel.addRow(row);
            }

            if (tableModel.getRowCount() == 0) {
                showMessage("No items currently in storage", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            showMessage("Error loading items: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void exportToExcel() {
        // Placeholder for export functionality
        showMessage("Export feature will be implemented soon!", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new ia().setVisible(true);
        });
    }
}