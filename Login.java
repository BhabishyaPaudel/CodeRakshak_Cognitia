import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Login extends JFrame implements ActionListener {
    JButton sign, signup, clear;
    JTextField PswTextField;
    JPasswordField pinTextField;

    // Constructor
    Login() {
        setLayout(null);
        // Create a custom blue color
        Color customBlue = new Color(51, 153, 255);
        Color customDarkBlue = new Color(0, 102, 204);

        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1000;
        int height = 600;
        setSize(width, height);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setTitle("MyNITM");

        // Create a panel for the logo and title
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, width, 150);
        headerPanel.setBackground(customBlue);
        add(headerPanel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("digitlogo.png"));
        Image i2 = i1.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        headerPanel.add(label);
        label.setBounds(width / 2 - 250, 15, 120, 120);

        JLabel text = new JLabel("MyNITM , Our Digital Solution");
        text.setFont(new Font("Montserrat", Font.BOLD, 35));
        text.setForeground(Color.WHITE);
        text.setBounds(width / 2 - 100, 45, 520, 50);
        headerPanel.add(text);

        // Create main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(width / 2 - 250, 180, 500, 350);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Segoe UI", Font.BOLD, 24));
        username.setForeground(customDarkBlue);
        username.setBounds(50, 30, 200, 40);
        mainPanel.add(username);

        PswTextField = new JTextField();
        PswTextField.setBounds(50, 70, 400, 40);
        PswTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        PswTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(customBlue),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(PswTextField);

        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Segoe UI", Font.BOLD, 24));
        password.setForeground(customDarkBlue);
        password.setBounds(50, 120, 200, 40);
        mainPanel.add(password);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(50, 160, 400, 40);
        pinTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pinTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(customBlue),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(pinTextField);

        sign = new JButton("Sign In");
        sign.setBounds(50, 220, 190, 40);
        sign.setBackground(customBlue);
        sign.setForeground(Color.WHITE);
        sign.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sign.setFocusPainted(false);
        sign.addActionListener(this);
        mainPanel.add(sign);

        clear = new JButton("Clear");
        clear.setBounds(260, 220, 190, 40);
        clear.setBackground(Color.GRAY);
        clear.setForeground(Color.WHITE);
        clear.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clear.setFocusPainted(false);
        clear.addActionListener(this);
        mainPanel.add(clear);

        signup = new JButton("Create New Account");
        signup.setBounds(50, 280, 400, 40);
        signup.setBackground(customDarkBlue);
        signup.setForeground(Color.WHITE);
        signup.setFont(new Font("Segoe UI", Font.BOLD, 16));
        signup.setFocusPainted(false);
        signup.addActionListener(this);
        mainPanel.add(signup);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sign) {
            conn connection = new conn();
            String username = PswTextField.getText();
            String pass = pinTextField.getText();
            String query = "select * from signup where username='" + username + "' and password='" + pass + "'";
            try {
                ResultSet rs = connection.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new signin().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }
            } catch (Exception ee) {
                System.out.println(ee);
            }
        } else if (e.getSource() == clear) {
            PswTextField.setText("");
            pinTextField.setText("");
        } else if (e.getSource() == signup) {
            setVisible(false);
            new Signup1().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}