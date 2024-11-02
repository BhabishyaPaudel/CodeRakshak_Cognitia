import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class laf extends JFrame implements ActionListener {
    JButton fai, lai, ia, info;
    JTextField PswTextField;
    JPasswordField pinTextField;

    // Constructor
    laf() {
        setTitle("MyNITM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null); // Center the frame on screen
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setPreferredSize(new Dimension(1000, 200));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Laf.png"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        headerPanel.add(label);

        JLabel text = new JLabel("Welcome to Lost & Found NITM");
        text.setFont(new Font("Osward", Font.BOLD, 32));
        text.setForeground(Color.WHITE);
        headerPanel.add(text);

        add(headerPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // Button style
        fai = createStyledButton("Found an Item");
        lai = createStyledButton("Lost an Item");
        ia = createStyledButton("Items Available");
        info = createStyledButton("Information");

        buttonPanel.add(fai);
        buttonPanel.add(lai);
        buttonPanel.add(ia);
        buttonPanel.add(info);

        add(buttonPanel, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("<---");
        backButton.setBackground(new Color(0, 51, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new signin().setVisible(true);
            }
        });

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.add(backButton);

        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fai) {
            setVisible(false);
            new fai().setVisible(true);
        } else if (e.getSource() == lai) {
            setVisible(false);
            new lai().setVisible(true);
        } else if (e.getSource() == ia) {
            setVisible(false);
            new ia().setVisible(true);
        } else if (e.getSource() == info) {
            JOptionPane.showMessageDialog(null, "Under Development");
        }
    }

    public static void main(String[] args) {
        new laf();
    }
}
