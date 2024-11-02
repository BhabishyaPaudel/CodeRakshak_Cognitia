import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.border.EmptyBorder;

public class signin extends JFrame implements ActionListener {
    JButton Accounts, laf, assignments, notice;
    JTextField PswTextField;
    JPasswordField pinTextField;

    signin() {
        setLayout(null);
        // Create custom colors
        Color customBlue = new Color(51, 153, 255);
        Color customDarkBlue = new Color(0, 102, 204);
        Color customLightBlue = new Color(240, 248, 255);

        getContentPane().setBackground(customLightBlue);

        // Get screen dimensions for responsive design
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1000;
        int height = 600;
        setSize(width, height);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setTitle("MyNITM");

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, width, 150);
        headerPanel.setBackground(customBlue);
        add(headerPanel);

        // Back button with modern design
        JButton backButton = new JButton("←");
        backButton.setBounds(20, 20, 50, 50);
        backButton.setBackground(new Color(255, 255, 255, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login().setVisible(true);
            }
        });
        headerPanel.add(backButton);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("digitlogo.png"));
        Image i2 = i1.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        headerPanel.add(label);
        label.setBounds(width / 2 - 250, 15, 120, 120);

        // Welcome text
        JLabel text = new JLabel("Welcome to NIT M Digital Solution");
        text.setFont(new Font("Montserrat", Font.BOLD, 32));
        text.setForeground(Color.WHITE);
        text.setBounds(width / 2 - 100, 45, 520, 50);
        headerPanel.add(text);

        // Services Panel
        JPanel servicesPanel = new JPanel();
        servicesPanel.setLayout(null);
        servicesPanel.setBounds(width / 2 - 300, 180, 600, 350);
        servicesPanel.setBackground(Color.WHITE);
        servicesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder()));
        add(servicesPanel);

        // Services heading
        JLabel services = new JLabel("Our Services");
        services.setFont(new Font("Segoe UI", Font.BOLD, 28));
        services.setForeground(customDarkBlue);
        services.setBounds(220, 20, 200, 40);
        servicesPanel.add(services);

        // Custom method to create styled buttons
        Accounts = createStyledButton("Accounts", 50, 80);
        laf = createStyledButton("Lost & Found", 310, 80);
        assignments = createStyledButton("Assignments", 50, 180);
        notice = createStyledButton("Notices", 310, 180);

        servicesPanel.add(Accounts);
        servicesPanel.add(laf);
        servicesPanel.add(assignments);
        servicesPanel.add(notice);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 240, 60);
        button.setBackground(new Color(51, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.addActionListener(this);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Accounts) {
            setVisible(false);
            new accounts().setVisible(true);
        } else if (e.getSource() == assignments) {
            JOptionPane.showMessageDialog(null, "Under Development", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == laf) {
            setVisible(false);
            new laf().setVisible(true);
        } else if (e.getSource() == notice) {
            JOptionPane.showMessageDialog(null, "Under Development", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}