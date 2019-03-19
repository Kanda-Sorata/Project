package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FormPanel extends JPanel {

    private JLabel[] labels = new JLabel[6];

    private JComboBox sectionCombox;
    private String [] sections = {"Technologie", "Informatique de Gestion", "Comptabilité", "Marketing", "Automatique",
            "Droit", "Sécurité des systèmes"};
    private  JComboBox originCombox;
    private String [] origins = {"Europe", "Afrique", "Asie", "Amérique", "Océanie"};

    private  JCheckBox stranger;
    private  JCheckBox scholarship;

    private ButtonGroup buttonGroup;
    private JRadioButton newStudent;
    private JRadioButton reRegistration;

    private JTextField matricule;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField birthDay;

    public FormPanel() {
        setLayout(new GridLayout(8, 2, 5, 15));

        setBorder(new EmptyBorder(75, 50, 75, 250));

        labels[0] = new JLabel("Matricule :");
        labels[1] = new JLabel("Prénom :");
        labels[2] = new JLabel("Nom :");
        labels[3] = new JLabel("Date de naissance :");
        labels[4] = new JLabel("Section");
        labels[5] = new JLabel("Origine");
        for (JLabel label : labels) label.setHorizontalAlignment(SwingConstants.RIGHT);

        //JTextFields
        matricule = new JFormattedTextField();
        matricule.addActionListener(new TextListener());
        firstName = new JTextField();
        lastName = new JTextField();
        birthDay = new JTextField();


        //Combox
        sectionCombox = new JComboBox(sections);
        sectionCombox.setMaximumRowCount(7);
        sectionCombox.setEnabled(false);

        originCombox = new JComboBox(origins);
        originCombox.setMaximumRowCount(5);
        originCombox.setEnabled(false);

        //CheckBox
        scholarship = new JCheckBox("Boursier");
        stranger = new JCheckBox("Etranger");
        stranger.addItemListener(new StrangerListener());
        scholarship.setHorizontalAlignment(SwingConstants.RIGHT);
        stranger.setHorizontalAlignment(SwingConstants.RIGHT);


        //JRadioButton

        newStudent = new JRadioButton("Nouveau étudiant");
        newStudent.addActionListener(new StudentListener());
        reRegistration = new JRadioButton("Réinscription");
        newStudent.setHorizontalAlignment(SwingConstants.RIGHT);
        reRegistration.setHorizontalAlignment(SwingConstants.RIGHT);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(newStudent);
        buttonGroup.add(reRegistration);


        //ajout element au panel
        add(labels[0]);
        add(matricule);
        add(labels[1]);
        add(firstName);
        add(labels[2]);
        add(lastName);
        add(labels[3]);
        add(birthDay);
        add(labels[4]);
        add(sectionCombox);
        add(scholarship);
        add(stranger);
        add(labels[5]);
        add(originCombox);
        add(newStudent);
        add(reRegistration);
    }


    private class StudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == newStudent){
                if(scholarship.isSelected()){
                    JOptionPane.showMessageDialog(null, "Please, go to the secretariat", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please go to the secretariant with your courses exemption", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class StrangerListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event){
            if(event.getStateChange() == ItemEvent.SELECTED) {
                originCombox.setEnabled(true);
            }
            else{
                originCombox.setEnabled(false);
            }
        }
    }

    private class TextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(estEntier(matricule.getText())) {
                switch (matricule.getText().charAt(0)) {
                    case '1':
                        sectionCombox.setSelectedIndex(0);
                        break;
                    case '2':
                        sectionCombox.setSelectedIndex(1);
                        break;
                    case '3':
                        sectionCombox.setSelectedIndex(2);
                        break;
                    case '4':
                        sectionCombox.setSelectedIndex(3);
                        break;
                    case '5':
                        sectionCombox.setSelectedIndex(4);
                        break;
                    case '6':
                        sectionCombox.setSelectedIndex(5);
                        break;
                    case '7':
                        sectionCombox.setSelectedIndex(6);
                        break;
                }
            }
        }
    }

    public Boolean estEntier(String matricule){
        int i = 0;
        while(i < matricule.length() && Character.isDigit((matricule.charAt(i)))){
            i++;
        }
        return  matricule.length() == 4 && i == matricule.length();
    }

    public void clearField(){
        matricule.setText(null);
        lastName.setText(null);
        firstName.setText(null);
        birthDay.setText(null);
        originCombox.setSelectedIndex(0);
        sectionCombox.setSelectedIndex(0);
        stranger.setSelected(false);
        scholarship.setSelected(false);
        buttonGroup.clearSelection();

    }

    public FormPanel getFormPanel(){
        return this;
    }

    public JComboBox getSectionCombox() {
        return sectionCombox;
    }

    public JComboBox getOriginCombox() {
        return originCombox;
    }

    public JCheckBox getStranger() {
        return stranger;
    }

    public JCheckBox getScholarship() {
        return scholarship;
    }

    public JRadioButton getNewStudent() {
        return newStudent;
    }

    public JRadioButton getReRegistration() {
        return reRegistration;
    }

    public JTextField getMatricule() {
        return matricule;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getBirthDay() {
        return birthDay;
    }

    public String[] getSections() {
        return sections;
    }

    public String[] getOrigins() {
        return origins;
    }
}
