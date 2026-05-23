package RegistrationApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 

public class TaxPayer extends JFrame {
    // Form components
    private JComboBox<String> titleComboBox, countryBirthComboBox, genderComboBox, languageComboBox;
    private JComboBox<String> communicationComboBox, professionComboBox, regionalOfficeComboBox, dualCitizenshipComboBox;
    private JComboBox<String> registrationTypeComboBox, referenceTypeComboBox;
    private JTextField nameEnglishField, nameSinhalaField, nameTamilField;
    private JTextField initialsEnglishField, initialsSinhalaField, initialsTamilField;
    private JTextField dobField, websiteField, othersIncomeField, referenceNumberField;
    private JTextField premisesNoEnglishField, unitNoEnglishField, addressEnglishField;
    private JTextField premisesNoSinhalaField, unitNoSinhalaField, addressSinhalaField;
    private JRadioButton yesDualCitizenRadio, noDualCitizenRadio;
    private JCheckBox businessCheck, employmentCheck, investmentCheck, othersCheck;

    // 2. Database Connection Details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tax_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public TaxPayer() {
        // Set up the JFrame
        setTitle("Taxpayer Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        
        // Set beige background for the main content
        getContentPane().setBackground(new Color(245, 245, 220)); // Beige color
        
        // Create the main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(245, 245, 220)); // Beige color
        
        // Create title panel with dark red background and white text
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(139, 0, 0)); // Dark red
        JLabel titleLabel = new JLabel("TAX PAYER REGISTRATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Fixed height
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // Add registration type and reference number with dropdowns
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(245, 245, 220)); // Beige color
        headerPanel.add(new JLabel("Registration type:"));
        registrationTypeComboBox = new JComboBox<>(new String[]{"INDIVIDUAL LOCAL", "INDIVIDUAL FOREIGN", "BUSINESS", "ORGANIZATION"});
        headerPanel.add(registrationTypeComboBox);
        
        headerPanel.add(Box.createHorizontalStrut(20));
        
        headerPanel.add(new JLabel("Applicant reference No.:"));
        referenceTypeComboBox = new JComboBox<>(new String[]{"NIC", "PASSPORT", "BUSINESS REGISTRATION", "OTHER"});
        headerPanel.add(referenceTypeComboBox);
        
        referenceNumberField = new JTextField(15);
        headerPanel.add(referenceNumberField);
        
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // Create form sections
        mainPanel.add(createNameSection());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createPersonalDetailsSection());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createCitizenshipSection());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createIncomeSection());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createProfessionSection());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createAddressSection());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createButtonPanel());
        
        // Add main panel to frame
        add(new JScrollPane(mainPanel));
        
        // Make frame visible
        setVisible(true);
    }
    
    private JPanel createNameSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Applicant Name"));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        // Full name fields
        JPanel fullNamePanel = new JPanel(new GridLayout(3, 2, 5, 5));
        fullNamePanel.setBackground(new Color(245, 245, 220)); // Beige color
        fullNamePanel.add(new JLabel("Full name (English):"));
        nameEnglishField = new JTextField(20);
        fullNamePanel.add(nameEnglishField);
        
        fullNamePanel.add(new JLabel("Full name (Sinhala):"));
        nameSinhalaField = new JTextField(20);
        fullNamePanel.add(nameSinhalaField);
        
        fullNamePanel.add(new JLabel("Full name (Tamil):"));
        nameTamilField = new JTextField(20);
        fullNamePanel.add(nameTamilField);
        
        panel.add(fullNamePanel);
        panel.add(Box.createVerticalStrut(10));
        
        // Name with initials fields
        JPanel initialsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        initialsPanel.setBackground(new Color(245, 245, 220)); // Beige color
        initialsPanel.add(new JLabel("Name with initials (English):"));
        initialsEnglishField = new JTextField(20);
        initialsPanel.add(initialsEnglishField);
        
        initialsPanel.add(new JLabel("Name with initials (Sinhala):"));
        initialsSinhalaField = new JTextField(20);
        initialsPanel.add(initialsSinhalaField);
        
        initialsPanel.add(new JLabel("Name with initials (Tamil):"));
        initialsTamilField = new JTextField(20);
        initialsPanel.add(initialsTamilField);
        
        panel.add(initialsPanel);
        
        return panel;
    }
    
    private JPanel createPersonalDetailsSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        panel.add(new JLabel("Title:"));
        titleComboBox = new JComboBox<>(new String[]{"Mr.", "Mrs.", "Ms.", "Dr.", "Prof."});
        panel.add(titleComboBox);
        
        panel.add(new JLabel("Date of birth:"));
        dobField = new JTextField(10);
        panel.add(dobField);
        
        panel.add(new JLabel("Country of birth:"));
        countryBirthComboBox = new JComboBox<>(new String[]{"Sri Lanka", "India", "USA", "UK", "Other"});
        panel.add(countryBirthComboBox);
        
        panel.add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        panel.add(genderComboBox);
        
        panel.add(new JLabel("Preferred language:"));
        languageComboBox = new JComboBox<>(new String[]{"English", "Sinhala", "Tamil"});
        panel.add(languageComboBox);
        
        panel.add(new JLabel("Preferred mode of communication:"));
        communicationComboBox = new JComboBox<>(new String[]{"Email", "SMS", "Post"});
        panel.add(communicationComboBox);
        
        panel.add(new JLabel("Website URL:"));
        websiteField = new JTextField(20);
        panel.add(websiteField);
        
        return panel;
    }
    
    private JPanel createCitizenshipSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Citizenship Details"));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        panel.add(new JLabel("Resident status:"));
        panel.add(new JLabel("RESIDENT"));
        
        panel.add(new JLabel("Citizenship:"));
        panel.add(new JLabel("CITIZEN"));
        
        panel.add(new JLabel("Dual citizenship:"));
        JPanel dualPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dualPanel.setBackground(new Color(245, 245, 220)); // Beige color
        yesDualCitizenRadio = new JRadioButton("Yes");
        noDualCitizenRadio = new JRadioButton("No", true);
        ButtonGroup dualGroup = new ButtonGroup();
        dualGroup.add(yesDualCitizenRadio);
        dualGroup.add(noDualCitizenRadio);
        dualPanel.add(yesDualCitizenRadio);
        dualPanel.add(noDualCitizenRadio);
        panel.add(dualPanel);
        
        panel.add(new JLabel("Dual citizenship country:"));
        dualCitizenshipComboBox = new JComboBox<>(new String[]{"N/A", "USA", "UK", "Australia", "Canada", "Other"});
        panel.add(dualCitizenshipComboBox);
        
        return panel;
    }
    
    private JPanel createIncomeSection() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Source of Income"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        JPanel checkPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        checkPanel.setBackground(new Color(245, 245, 220)); // Beige color
        businessCheck = new JCheckBox("Business");
        employmentCheck = new JCheckBox("Employment");
        investmentCheck = new JCheckBox("Investment");
        othersCheck = new JCheckBox("Others");
        
        checkPanel.add(businessCheck);
        checkPanel.add(employmentCheck);
        checkPanel.add(investmentCheck);
        checkPanel.add(othersCheck);
        
        panel.add(checkPanel);
        
        JPanel othersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        othersPanel.setBackground(new Color(245, 245, 220)); // Beige color
        othersPanel.add(new JLabel("If others, please specify:"));
        othersIncomeField = new JTextField(15);
        othersPanel.add(othersIncomeField);
        
        panel.add(othersPanel);
        
        return panel;
    }
    
    private JPanel createProfessionSection() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Profession and Regional Office"));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        panel.add(new JLabel("Profession:"));
        professionComboBox = new JComboBox<>(new String[]{"Accountant", "Doctor", "Engineer", "Teacher", "Business", "Other"});
        panel.add(professionComboBox);
        
        panel.add(Box.createHorizontalStrut(20));
        
        panel.add(new JLabel("Nearest regional office:"));
        regionalOfficeComboBox = new JComboBox<>(new String[]{"Colombo", "Kandy", "Galle", "Jaffna", "Kurunegala", "Anuradhapura"});
        panel.add(regionalOfficeComboBox);
        
        return panel;
    }
    
    private JPanel createAddressSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("PERMANENT ADDRESS"));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        // English address
        JPanel englishAddressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        englishAddressPanel.setBackground(new Color(245, 245, 220)); // Beige color
        englishAddressPanel.add(new JLabel("Premises No. (English):"));
        premisesNoEnglishField = new JTextField(8);
        englishAddressPanel.add(premisesNoEnglishField);
        
        englishAddressPanel.add(new JLabel("Unit No. (English):"));
        unitNoEnglishField = new JTextField(8);
        englishAddressPanel.add(unitNoEnglishField);
        
        panel.add(englishAddressPanel);
        
        JPanel englishAddressLine2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        englishAddressLine2.setBackground(new Color(245, 245, 220)); // Beige color
        englishAddressLine2.add(new JLabel("Address (English):"));
        addressEnglishField = new JTextField(25);
        englishAddressLine2.add(addressEnglishField);
        panel.add(englishAddressLine2);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Sinhala address
        JPanel sinhalaAddressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sinhalaAddressPanel.setBackground(new Color(245, 245, 220)); // Beige color
        sinhalaAddressPanel.add(new JLabel("Premises No. (Sinhala):"));
        premisesNoSinhalaField = new JTextField(8);
        sinhalaAddressPanel.add(premisesNoSinhalaField);
        
        sinhalaAddressPanel.add(new JLabel("Unit No. (Sinhala):"));
        unitNoSinhalaField = new JTextField(8);
        sinhalaAddressPanel.add(unitNoSinhalaField);
        
        panel.add(sinhalaAddressPanel);
        
        JPanel sinhalaAddressLine2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sinhalaAddressLine2.setBackground(new Color(245, 245, 220)); // Beige color
        sinhalaAddressLine2.add(new JLabel("Address (Sinhala):"));
        addressSinhalaField = new JTextField(25);
        sinhalaAddressLine2.add(addressSinhalaField);
        panel.add(sinhalaAddressLine2);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(new Color(245, 245, 220)); // Beige color
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerAction();
            }
        });
        
        JButton resetButton = new JButton("Re-set");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetAction();
            }
        });
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });
        
        panel.add(registerButton);
        panel.add(resetButton);
        panel.add(exitButton);
        
        return panel;
    }
    
    // 3. මෙතඩ් එක වෙනස් කරා දත්ත ටික MySQL වලට සේව් වෙන්න
    private void registerAction() {
        // Validation
        if (nameEnglishField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter full name in English", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (referenceNumberField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter reference number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 4. Checkbox වලින් තෝරන Income Sources ටික එක දිග String එකක් කරගැනීම
        StringBuilder incomeSources = new StringBuilder();
        if (businessCheck.isSelected()) incomeSources.append("Business, ");
        if (employmentCheck.isSelected()) incomeSources.append("Employment, ");
        if (investmentCheck.isSelected()) incomeSources.append("Investment, ");
        if (othersCheck.isSelected()) incomeSources.append("Others (").append(othersIncomeField.getText()).append("), ");
        
        // අන්තිමට තියෙන කොමාව අයින් කිරීම
        String incomeStr = incomeSources.toString();
        if (incomeStr.endsWith(", ")) {
            incomeStr = incomeStr.substring(0, incomeStr.length() - 2);
        }

        // 5. ලිපිනයන් එකතු කරලා Full Address String එකක් හැදීම
        String fullAddressEnglish = premisesNoEnglishField.getText() + ", " + unitNoEnglishField.getText() + ", " + addressEnglishField.getText();
        String fullAddressSinhala = premisesNoSinhalaField.getText() + ", " + unitNoSinhalaField.getText() + ", " + addressSinhalaField.getText();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // SQL Insert Query
            String sql = "INSERT INTO taxpayers (reference_number, registration_type, reference_type, title, name_english, "
                    + "name_sinhala, name_tamil, initials_english, initials_sinhala, initials_tamil, dob, country_birth, "
                    + "gender, language, communication, website, dual_citizenship, dual_country, income_sources, profession, "
                    + "regional_office, address_english, address_sinhala) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, referenceNumberField.getText().trim());
            pstmt.setString(2, registrationTypeComboBox.getSelectedItem().toString());
            pstmt.setString(3, referenceTypeComboBox.getSelectedItem().toString());
            pstmt.setString(4, titleComboBox.getSelectedItem().toString());
            pstmt.setString(5, nameEnglishField.getText().trim());
            pstmt.setString(6, nameSinhalaField.getText().trim());
            pstmt.setString(7, nameTamilField.getText().trim());
            pstmt.setString(8, initialsEnglishField.getText().trim());
            pstmt.setString(9, initialsSinhalaField.getText().trim());
            pstmt.setString(10, initialsTamilField.getText().trim());
            pstmt.setString(11, dobField.getText().trim());
            pstmt.setString(12, countryBirthComboBox.getSelectedItem().toString());
            pstmt.setString(13, genderComboBox.getSelectedItem().toString());
            pstmt.setString(14, languageComboBox.getSelectedItem().toString());
            pstmt.setString(15, communicationComboBox.getSelectedItem().toString());
            pstmt.setString(16, websiteField.getText().trim());
            pstmt.setString(17, yesDualCitizenRadio.isSelected() ? "Yes" : "No");
            pstmt.setString(18, dualCitizenshipComboBox.getSelectedItem().toString());
            pstmt.setString(19, incomeStr);
            pstmt.setString(20, professionComboBox.getSelectedItem().toString());
            pstmt.setString(21, regionalOfficeComboBox.getSelectedItem().toString());
            pstmt.setString(22, fullAddressEnglish);
            pstmt.setString(23, fullAddressSinhala);
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Taxpayer Registered Successfully in MySQL Database!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetAction(); // Form එක Reset කරනවා
            }
         // ඉංග්‍රීසි ලිපිනය කොටස් 3 එකතු කිරීම
            String fullAddressEnglish1 = premisesNoEnglishField.getText().trim() + ", " 
                                      + unitNoEnglishField.getText().trim() + ", " 
                                      + addressEnglishField.getText().trim();

            // සිංහල ලිපිනය කොටස් 3 එකතු කිරීම
            String fullAddressSinhala1 = premisesNoSinhalaField.getText().trim() + ", " 
                                      + unitNoSinhalaField.getText().trim() + ", " 
                                      + addressSinhalaField.getText().trim();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
    
    private void resetAction() {
        // Clear all fields
        nameEnglishField.setText("");
        nameSinhalaField.setText("");
        nameTamilField.setText("");
        initialsEnglishField.setText("");
        initialsSinhalaField.setText("");
        initialsTamilField.setText("");
        dobField.setText("");
        websiteField.setText("");
        othersIncomeField.setText("");
        premisesNoEnglishField.setText("");
        unitNoEnglishField.setText("");
        addressEnglishField.setText("");
        premisesNoSinhalaField.setText("");
        unitNoSinhalaField.setText("");
        addressSinhalaField.setText("");
        referenceNumberField.setText("");
        
        // Reset combo boxes
        registrationTypeComboBox.setSelectedIndex(0);
        referenceTypeComboBox.setSelectedIndex(0);
        titleComboBox.setSelectedIndex(0);
        countryBirthComboBox.setSelectedIndex(0);
        genderComboBox.setSelectedIndex(0);
        languageComboBox.setSelectedIndex(0);
        communicationComboBox.setSelectedIndex(0);
        professionComboBox.setSelectedIndex(0);
        regionalOfficeComboBox.setSelectedIndex(0);
        dualCitizenshipComboBox.setSelectedIndex(0);
        
        // Reset radio buttons and checkboxes
        noDualCitizenRadio.setSelected(true);
        businessCheck.setSelected(false);
        employmentCheck.setSelected(false);
        investmentCheck.setSelected(false);
        othersCheck.setSelected(false);
    }
    
    private void exitAction() {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to exit?", "Exit Confirmation", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TaxPayer();
            }
        });
    }
}