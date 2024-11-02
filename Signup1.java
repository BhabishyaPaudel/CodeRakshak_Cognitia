import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Signup1 extends JFrame implements ActionListener {
    JTextField FNfield, RNfield, Efield, Addfield, Ufield, PNfield, Depfield;
    JPasswordField Pfield, CPfield;
    JButton signup;
    JRadioButton male, female;

    Signup1() {
        setLayout(null);
        setSize(800, 850);
        setVisible(true);
        setLocation(350, 10);
        setTitle("MyNITM Signup 1");

        JLabel PerDet = new JLabel(" Personal Details ");
        PerDet.setFont(new Font("Raleway", Font.BOLD, 28));
        PerDet.setBounds(270, 80, 600, 40);
        add(PerDet);

        JLabel FirstName = new JLabel(" Name :");
        FirstName.setFont(new Font("Raleway", Font.BOLD, 18));
        FirstName.setBounds(150, 140, 600, 40);
        add(FirstName);

        JButton backButton = new JButton("<---");
        backButton.setBounds(700, 20, 80, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login().setVisible(true);
            }
        });
        add(backButton);

        FNfield = new JTextField();
        FNfield.setBounds(310, 150, 350, 30);
        FNfield.setFont(new Font("arial", Font.BOLD, 14));
        add(FNfield);

        JLabel RollNo = new JLabel("Roll NO :");
        RollNo.setFont(new Font("Raleway", Font.BOLD, 18));
        RollNo.setBounds(150, 180, 600, 40);
        add(RollNo);

        RNfield = new JTextField();
        RNfield.setBounds(310, 185, 350, 30);
        RNfield.setFont(new Font("arial", Font.BOLD, 14));
        add(RNfield);

        JLabel Dep = new JLabel("Department:");
        Dep.setFont(new Font("Raleway", Font.BOLD, 18));
        Dep.setBounds(150, 220, 600, 40);
        add(Dep);

        Depfield = new JTextField();
        Depfield.setBounds(310, 220, 350, 30);
        Depfield.setFont(new Font("arial", Font.BOLD, 14));
        add(Depfield);

        JLabel Gender = new JLabel("Gender :");
        Gender.setFont(new Font("Raleway", Font.BOLD, 18));
        Gender.setBounds(150, 260, 600, 40);
        add(Gender);

        male = new JRadioButton("Male");
        male.setBounds(310, 260, 60, 30);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(380, 260, 120, 30);
        add(female);

        ButtonGroup bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);

        JLabel Email = new JLabel("Email Address:");
        Email.setFont(new Font("Raleway", Font.BOLD, 18));
        Email.setBounds(150, 300, 600, 40);
        add(Email);

        Efield = new JTextField();
        Efield.setBounds(310, 300, 350, 30);
        Efield.setFont(new Font("arial", Font.BOLD, 14));
        add(Efield);

        JLabel PhoneN = new JLabel("Phone No :");
        PhoneN.setFont(new Font("Raleway", Font.BOLD, 18));
        PhoneN.setBounds(150, 340, 600, 40);
        add(PhoneN);

        PNfield = new JTextField();
        PNfield.setBounds(310, 340, 350, 30);
        PNfield.setFont(new Font("arial", Font.BOLD, 14));
        add(PNfield);

        JLabel Address = new JLabel("Address :");
        Address.setFont(new Font("Raleway", Font.BOLD, 18));
        Address.setBounds(150, 380, 600, 40);
        add(Address);

        Addfield = new JTextField();
        Addfield.setBounds(310, 380, 350, 30);
        Addfield.setFont(new Font("arial", Font.BOLD, 14));
        add(Addfield);

        JLabel Username = new JLabel("Username :");
        Username.setFont(new Font("Raleway", Font.BOLD, 18));
        Username.setBounds(150, 420, 600, 40);
        add(Username);

        Ufield = new JTextField();
        Ufield.setBounds(310, 420, 350, 30);
        Ufield.setFont(new Font("arial", Font.BOLD, 14));
        add(Ufield);

        JLabel Password = new JLabel("Password :");
        Password.setFont(new Font("Raleway", Font.BOLD, 18));
        Password.setBounds(150, 460, 600, 40);
        add(Password);

        Pfield = new JPasswordField();
        Pfield.setBounds(310, 460, 350, 30);
        Pfield.setFont(new Font("arial", Font.BOLD, 14));
        add(Pfield);

        JLabel ConfirmPassword = new JLabel("Confirm Password :");
        ConfirmPassword.setFont(new Font("Raleway", Font.BOLD, 18));
        ConfirmPassword.setBounds(150, 500, 600, 40);
        add(ConfirmPassword);

        CPfield = new JPasswordField();
        CPfield.setBounds(310, 500, 350, 30);
        CPfield.setFont(new Font("arial", Font.BOLD, 14));
        add(CPfield);

        signup = new JButton("Signup");
        signup.setBounds(560, 550, 100, 30);
        signup.setBackground(Color.BLACK);
        signup.addActionListener(this);
        signup.setForeground(Color.white);
        add(signup);
    }

    public void actionPerformed(ActionEvent ae) {
        String email = Efield.getText();
        String password = new String(Pfield.getPassword());
        String confirmPassword = new String(CPfield.getPassword());

        // Check if any fields are empty
        if (FNfield.getText().isEmpty() || RNfield.getText().isEmpty() || Efield.getText().isEmpty() ||
                Addfield.getText().isEmpty() || Ufield.getText().isEmpty() || PNfield.getText().isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are mandatory.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate email format
        if (!email.endsWith("@nitm.ac.in")) {
            JOptionPane.showMessageDialog(this, "Email should end with @nitm.ac.in", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate password complexity
        if (password.length() < 8 || !Pattern.compile("[^a-zA-Z0-9]").matcher(password).find() ||
                !Pattern.compile("[0-9]").matcher(password).find()) {
            JOptionPane.showMessageDialog(this,
                    "Password must be at least 8 characters long, contain a number and a special character.", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check if password and confirm password match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Signup Successful!\nUsername: " + Ufield.getText() + "\nPassword: " + password, "SignUp Done ",
                JOptionPane.INFORMATION_MESSAGE);
        // Proceed with signup logic here
        String name = FNfield.getText();
        String rollno = RNfield.getText();
        String dep = Depfield.getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";

        } else if (female.isSelected()) {
            gender = "Female";
        }
        String emai = Efield.getText();
        String pn = PNfield.getText();
        String add = Addfield.getText();
        String un = Ufield.getText();
        String pw = Pfield.getText();
        String cnf = CPfield.getText();
        String id = "3";

        if (ae.getSource() == signup) {
            conn newconnection = new conn();
            String query = "insert into signup values('" + id + "','" + name + "','" + rollno + "','" + dep
                    + "','" + gender + "','" + emai + "','" + pn + "','" + add + "','" + un + "','" + pw
                    + "','" + cnf + "')";
            try {
                newconnection.s.executeUpdate(query);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            setVisible(false);

            setVisible(false);
            new Login().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Signup1();
    }
}