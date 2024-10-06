package JFC2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankRegistrationForm extends JFrame {
    // Komponen GUI
    private JTextField nameField;
    private JList<String> accountTypeList;
    private JSpinner transactionSpinner, dobSpinner;
    private JPasswordField passwordField, confirmPasswordField;
    private JTextArea outputArea;

    public BankRegistrationForm() {
        // Set up frame
        setTitle("Bank Registration Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // Input Nama
        inputPanel.add(new JLabel("Nama:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // JList untuk memilih jenis tabungan
        inputPanel.add(new JLabel("Jenis Tabungan:"));
        String[] accountTypes = {"Tabungan Umum", "Tabungan Investasi", "Tabungan Pendidikan", "Tabungan Berjangka"};
        accountTypeList = new JList<>(accountTypes);
        accountTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inputPanel.add(new JScrollPane(accountTypeList));

        // JSpinner untuk memilih frekuensi transaksi
        inputPanel.add(new JLabel("Frekuensi Transaksi per Bulan:"));
        SpinnerNumberModel transactionModel = new SpinnerNumberModel(1, 1, 100, 1);
        transactionSpinner = new JSpinner(transactionModel);
        inputPanel.add(transactionSpinner);

        // Input password
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // Input confirm password
        inputPanel.add(new JLabel("Konfirmasi Password:"));
        confirmPasswordField = new JPasswordField();
        inputPanel.add(confirmPasswordField);

        // JSpinner untuk tanggal lahir
        inputPanel.add(new JLabel("Tanggal Lahir:"));
        SpinnerDateModel dobModel = new SpinnerDateModel();
        dobSpinner = new JSpinner(dobModel);
        inputPanel.add(dobSpinner);

        add(inputPanel, BorderLayout.CENTER);

        // Area output
        outputArea = new JTextArea(5, 20);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Action untuk menu reset
        resetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Action untuk menu exit
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(resetMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Tombol Submit
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
        add(submitButton, BorderLayout.NORTH);

        setVisible(true);
    }

    // Method untuk mereset form
    private void resetForm() {
        nameField.setText("");
        accountTypeList.clearSelection();
        transactionSpinner.setValue(1);
        passwordField.setText("");
        confirmPasswordField.setText("");
        dobSpinner.setValue(new java.util.Date());
        outputArea.setText("");
    }

    // Method untuk submit form
    private void submitForm() {
        String name = nameField.getText();
        String accountType = accountTypeList.getSelectedValue();
        int transactions = (int) transactionSpinner.getValue();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        java.util.Date dob = (java.util.Date) dobSpinner.getValue();

        if (!password.equals(confirmPassword)) {
            outputArea.setText("Password dan Konfirmasi Password tidak cocok!");
        } else {
            outputArea.setText("Nama: " + name + "\n"
                    + "Jenis Tabungan: " + accountType + "\n"
                    + "Frekuensi Transaksi: " + transactions + " kali per bulan\n"
                    + "Tanggal Lahir: " + dob);
        }
    }

    public static void main(String[] args) {
        new BankRegistrationForm();
    }
}
