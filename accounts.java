import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.Random;

public class accounts extends JFrame implements ActionListener {
    JButton payFeesButton, transactionsButton, documentsButton;

    public accounts() {
        setLayout(null);
        setSize(800, 600);
        setLocation(350, 10);
        setTitle("Student Account Management");

        Random ran = new Random();
        int rn = 100000 + ran.nextInt(900000);

        // Title
        JLabel mainTitle = new JLabel("Account Management Portal");
        mainTitle.setFont(new Font("Raleway", Font.BOLD, 32));
        mainTitle.setForeground(new Color(30, 80, 188));
        mainTitle.setBounds(200, 30, 500, 50);
        add(mainTitle);

        JButton backButton = new JButton("<---");
        backButton.setBounds(700, 20, 80, 30);
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

        // Student Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(50, 100, 700, 100);
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel studentInfo = new JLabel("Welcome, Student");
        studentInfo.setFont(new Font("Raleway", Font.BOLD, 18));
        studentInfo.setBounds(20, 20, 300, 30);
        infoPanel.add(studentInfo);

        JLabel rollNo = new JLabel("Session Id : " + rn);
        rollNo.setFont(new Font("Raleway", Font.PLAIN, 16));
        rollNo.setBounds(20, 50, 200, 30);
        infoPanel.add(rollNo);

        add(infoPanel);

        // Pay Fees Button
        payFeesButton = new JButton("Pay Fees");
        payFeesButton.setBounds(150, 250, 500, 60);
        payFeesButton.setFont(new Font("Raleway", Font.BOLD, 20));
        payFeesButton.setBackground(new Color(66, 139, 202));
        payFeesButton.setForeground(Color.WHITE);
        payFeesButton.setFocusPainted(false);
        payFeesButton.addActionListener(this);
        payFeesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(payFeesButton);

        // View Transactions Button
        transactionsButton = new JButton("View Transactions History");
        transactionsButton.setBounds(150, 330, 500, 60);
        transactionsButton.setFont(new Font("Raleway", Font.BOLD, 20));
        transactionsButton.setBackground(new Color(92, 184, 92));
        transactionsButton.setForeground(Color.WHITE);
        transactionsButton.setFocusPainted(false);
        transactionsButton.addActionListener(this);
        transactionsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(transactionsButton);

        // Upload Documents Button
        documentsButton = new JButton("Upload/View Documents");
        documentsButton.setBounds(150, 410, 500, 60);
        documentsButton.setFont(new Font("Raleway", Font.BOLD, 20));
        documentsButton.setBackground(new Color(240, 173, 78));
        documentsButton.setForeground(Color.WHITE);
        documentsButton.setFocusPainted(false);
        documentsButton.addActionListener(this);
        documentsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(documentsButton);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(null);
        footerPanel.setBounds(0, 500, 800, 70);
        footerPanel.setBackground(new Color(245, 245, 245));

        // Help Link
        JLabel helpLink = new JLabel("Need Help?");
        helpLink.setFont(new Font("Raleway", Font.PLAIN, 14));
        helpLink.setForeground(Color.BLUE);
        helpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        helpLink.setBounds(50, 20, 100, 30);
        helpLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.nitm.ac.in/"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error opening help page");
                }
            }
        });
        footerPanel.add(helpLink);

        // Contact Info
        JLabel contactInfo = new JLabel("Contact: nitm.ac.in");
        contactInfo.setFont(new Font("Raleway", Font.PLAIN, 14));
        contactInfo.setBounds(500, 20, 200, 30);
        footerPanel.add(contactInfo);

        add(footerPanel);

        // Add hover effects
        addHoverEffect(payFeesButton, new Color(66, 139, 202), new Color(51, 122, 183));
        addHoverEffect(transactionsButton, new Color(92, 184, 92), new Color(76, 174, 76));
        addHoverEffect(documentsButton, new Color(240, 173, 78), new Color(236, 151, 31));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addHoverEffect(JButton button, Color originalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == payFeesButton) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.onlinesbi.sbi/sbicollect/"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error opening payment gateway");
            }
        } else if (ae.getSource() == transactionsButton) {
            // Add code to show transactions
            // For now, showing a message
            JOptionPane.showMessageDialog(null, "Transaction History\n\n" +
                    "1. Fee Payment - Rs. 50000 - 01/01/2024\n" +
                    "2. Library Fine - Rs. 100 - 15/12/2023\n" +
                    "3. Hostel Fee - Rs. 25000 - 01/12/2023");
        } else if (ae.getSource() == documentsButton) {
            // Add code to handle document upload/view
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "File selected: " +
                        fileChooser.getSelectedFile().getName());
            }
        }
    }

    // Custom button class for better styling
    class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(getBackground());
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new accounts();
    }
}