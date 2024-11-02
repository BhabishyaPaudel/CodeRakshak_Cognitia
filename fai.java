import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class fai extends JFrame implements ActionListener {
    JTextField itemNameField, modelField, colorField, locationField, finderNameField, finderContactField, Urlfield;
    JTextArea descriptionArea, additionalInfoArea;
    // JDateChooser dateChooser;
    JButton submitButton, resetButton;
    JComboBox<String> itemStatusBox;

    public fai() {
        setLayout(null);
        setSize(800, 1000);
        setLocation(350, 10);
        setTitle("Found Item Registration Form");

        // Title
        JLabel formTitle = new JLabel("Found Item Registration");
        formTitle.setFont(new Font("Raleway", Font.BOLD, 28));
        formTitle.setBounds(270, 30, 400, 40);
        add(formTitle);

        // Item Name
        JLabel itemName = new JLabel("Item Name:");
        itemName.setFont(new Font("Raleway", Font.BOLD, 18));
        itemName.setBounds(150, 100, 200, 40);
        add(itemName);

        itemNameField = new JTextField();
        itemNameField.setBounds(310, 105, 350, 30);
        itemNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(itemNameField);

        // Model
        JLabel model = new JLabel("Model/Brand:");
        model.setFont(new Font("Raleway", Font.BOLD, 18));
        model.setBounds(150, 160, 200, 40);
        add(model);

        modelField = new JTextField();
        modelField.setBounds(310, 165, 350, 30);
        modelField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(modelField);

        // Description
        JLabel description = new JLabel("Description:");
        description.setFont(new Font("Raleway", Font.BOLD, 18));
        description.setBounds(150, 220, 200, 40);
        add(description);

        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane scrollDesc = new JScrollPane(descriptionArea);
        scrollDesc.setBounds(310, 225, 350, 100);
        add(scrollDesc);

        // Color
        JLabel color = new JLabel("Color:");
        color.setFont(new Font("Raleway", Font.BOLD, 18));
        color.setBounds(150, 340, 200, 40);
        add(color);

        colorField = new JTextField();
        colorField.setBounds(310, 345, 350, 30);
        colorField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(colorField);

        // Found Location
        JLabel location = new JLabel("Found Location:");
        location.setFont(new Font("Raleway", Font.BOLD, 18));
        location.setBounds(150, 400, 200, 40);
        add(location);

        locationField = new JTextField();
        locationField.setBounds(310, 405, 350, 30);
        locationField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(locationField);

        // Date Found
        JLabel date = new JLabel("Image Url:");
        date.setFont(new Font("Raleway", Font.BOLD, 18));
        date.setBounds(150, 460, 200, 40);
        add(date);

        // dateChooser = new JDateChooser();
        // dateChooser.setBounds(310, 465, 350, 30);
        // dateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        // add(dateChooser);

        Urlfield = new JTextField();
        Urlfield.setBounds(310, 465, 350, 30);
        Urlfield.setFont(new Font("Arial", Font.PLAIN, 14));
        add(Urlfield);

        // Finder's Name
        JLabel finderName = new JLabel("Finder's Name:");
        finderName.setFont(new Font("Raleway", Font.BOLD, 18));
        finderName.setBounds(150, 520, 200, 40);
        add(finderName);

        finderNameField = new JTextField();
        finderNameField.setBounds(310, 525, 350, 30);
        finderNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(finderNameField);

        // Finder's Contact
        JLabel finderContact = new JLabel("Finder's Contact:");
        finderContact.setFont(new Font("Raleway", Font.BOLD, 18));
        finderContact.setBounds(150, 580, 200, 40);
        add(finderContact);

        finderContactField = new JTextField();
        finderContactField.setBounds(310, 585, 350, 30);
        finderContactField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(finderContactField);

        JButton backButton = new JButton("<---");
        backButton.setBounds(700, 20, 80, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new laf().setVisible(true);
            }
        });
        add(backButton);

        // Item Status
        JLabel status = new JLabel("Item Status:");
        status.setFont(new Font("Raleway", Font.BOLD, 18));
        status.setBounds(150, 640, 200, 40);
        add(status);

        String[] statusOptions = { "In Storage", "Returned to Owner", "Under Verification" };
        itemStatusBox = new JComboBox<>(statusOptions);
        itemStatusBox.setBounds(310, 645, 350, 30);
        itemStatusBox.setFont(new Font("Arial", Font.PLAIN, 14));
        add(itemStatusBox);

        // // Additional Info
        // JLabel additionalInfo = new JLabel("Additional Info:");
        // additionalInfo.setFont(new Font("Raleway", Font.BOLD, 18));
        // additionalInfo.setBounds(150, 700, 200, 40);
        // add(additionalInfo);

        // additionalInfoArea = new JTextArea();
        // additionalInfoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        // additionalInfoArea.setLineWrap(true);
        // additionalInfoArea.setWrapStyleWord(true);

        // JScrollPane scrollAdditional = new JScrollPane(additionalInfoArea);
        // scrollAdditional.setBounds(310, 705, 350, 100);
        // add(scrollAdditional);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(66, 139, 202));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Raleway", Font.BOLD, 14));
        submitButton.setBounds(310, 700, 150, 30);
        submitButton.addActionListener(this);
        add(submitButton);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(217, 83, 79));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Raleway", Font.BOLD, 14));
        resetButton.setBounds(510, 700, 150, 30);
        resetButton.addActionListener(this);
        add(resetButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String itemName = itemNameField.getText();
            String model = modelField.getText();
            String description = descriptionArea.getText();
            String color = colorField.getText();
            String location = locationField.getText();
            String finderName = finderNameField.getText();
            String finderContact = finderContactField.getText();
            String itemStatus = (String) itemStatusBox.getSelectedItem();
            String itemid = "3";
            String imageurl = Urlfield.getText();
            conn newconnection = new conn();
            String query = "INSERT INTO founditem VALUES ('" + itemid + "','" + itemName + "', '" + model + "', '"
                    + description
                    + "', '" + color + "', '" + location + "','" + imageurl + "', '" + finderName + "', '"
                    + finderContact + "', '"
                    + itemStatus + "')";

            try {
                newconnection.s.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (ae.getSource() == resetButton) {
                resetForm();

            }
        }
    }

    private void resetForm() {
        itemNameField.setText("");
        modelField.setText("");
        descriptionArea.setText("");
        colorField.setText("");
        locationField.setText("");
        // dateChooser.setDate(null);
        finderNameField.setText("");
        finderContactField.setText("");
        itemStatusBox.setSelectedIndex(0);
        additionalInfoArea.setText("");
    }

    public static void main(String[] args) {
        new fai();
    }
}