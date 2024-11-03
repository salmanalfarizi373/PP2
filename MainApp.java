package JFC2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class MainApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public MainApp() {
        setTitle("Aplikasi Studi Kasus");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menubar dan Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem formItem = new JMenuItem("Form Input");
        menu.add(formItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel utama
        JPanel mainPanel = new JPanel();
        add(mainPanel, BorderLayout.CENTER);

        // Event klik menu
        formItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFormInput(mainPanel);
            }
        });

        // Tampilkan frame
        setVisible(true);
    }

    private void showFormInput(JPanel mainPanel) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());

        // Panel Form Input
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nama
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nama:"), gbc);

        gbc.gridx = 1;
        JTextField textFieldName = new JTextField(20);
        formPanel.add(textFieldName, gbc);

        // Deskripsi
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Deskripsi:"), gbc);

        gbc.gridx = 1;
        JTextArea textAreaDescription = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(textAreaDescription), gbc);

        // Jenis Kelamin
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Jenis Kelamin:"), gbc);

        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JRadioButton radioMale = new JRadioButton("Pria");
        JRadioButton radioFemale = new JRadioButton("Wanita");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioMale);
        genderGroup.add(radioFemale);
        genderPanel.add(radioMale);
        genderPanel.add(radioFemale);
        formPanel.add(genderPanel, gbc);

        // Hobi
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Hobi:"), gbc);

        gbc.gridx = 1;
        JPanel hobbyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JCheckBox hobbyReading = new JCheckBox("Membaca");
        JCheckBox hobbyTraveling = new JCheckBox("Traveling");
        hobbyPanel.add(hobbyReading);
        hobbyPanel.add(hobbyTraveling);
        formPanel.add(hobbyPanel, gbc);

        // Negara
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Negara:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> comboBoxCountry = new JComboBox<>(new String[]{"Indonesia", "Malaysia", "Singapura"});
        formPanel.add(comboBoxCountry, gbc);

        // Kemampuan
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Kemampuan:"), gbc);

        gbc.gridx = 1;
        JList<String> listSkills = new JList<>(new String[]{"Java", "Python", "C++"});
        listSkills.setVisibleRowCount(3);
        formPanel.add(new JScrollPane(listSkills), gbc);

        // Pengalaman (Slider)
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Pengalaman (0-10):"), gbc);

        gbc.gridx = 1;
        JSlider sliderExperience = new JSlider(0, 10);
        sliderExperience.setMajorTickSpacing(1);
        sliderExperience.setPaintTicks(true);
        sliderExperience.setPaintLabels(true);
        formPanel.add(sliderExperience, gbc);

        // Umur (Spinner)
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Umur:"), gbc);

        gbc.gridx = 1;
        JSpinner spinnerAge = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));
        formPanel.add(spinnerAge, gbc);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // Tombol submit dan tabel
        JButton submitButton = new JButton("Submit");
        mainPanel.add(submitButton, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new String[]{"Nama", "Deskripsi", "Jenis Kelamin", "Hobi", "Negara", "Kemampuan", "Pengalaman", "Umur"}, 0);
        table = new JTable(tableModel);
        mainPanel.add(new JScrollPane(table), BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = radioMale.isSelected() ? "Pria" : (radioFemale.isSelected() ? "Wanita" : "Tidak dipilih");
                String hobbies = (hobbyReading.isSelected() ? "Membaca " : "") + (hobbyTraveling.isSelected() ? "Traveling" : "");
                String skills = String.join(", ", listSkills.getSelectedValuesList());

                tableModel.addRow(new Object[]{
                        textFieldName.getText(),
                        textAreaDescription.getText(),
                        gender,
                        hobbies,
                        comboBoxCountry.getSelectedItem(),
                        skills,
                        sliderExperience.getValue(),
                        spinnerAge.getValue()
                });
            }
        });

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp();
            }
        });
    }
}

