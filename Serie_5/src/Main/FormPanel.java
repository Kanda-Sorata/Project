package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FormPanel extends JPanel {

    private JLabel[] labels = new JLabel[6];
    private JTextField matricule;
    private JComboBox sectionCombox;
    private String [] sections = {"Technologie", "Informatique de Gestion", "Comptabilité", "Marketing", "Automatique",
            "Droit", "Sécurité des systèmes"};
    private  JComboBox originCombox;
    private String [] origins = {"Europe", "Afrique", "Asie", "Amérique", "Océanie"};

    private  JCheckBox stranger;
    private  JCheckBox scholarship;

    private ButtonGroup buttonGroup;
    private JRadioButton newStudent;

    public FormPanel() {
        setLayout(new GridLayout(8, 2, 5, 15));

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
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField birthDay = new JTextField();


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
        JRadioButton reRegistration = new JRadioButton("Réinscription");
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
                        sectionCombox.setSelectedItem(sections[0]);
                        break;
                    case '2':
                        sectionCombox.setSelectedItem(sections[1]);
                        break;
                    case '3':
                        sectionCombox.setSelectedItem(sections[2]);
                        break;
                    case '4':
                        sectionCombox.setSelectedItem(sections[3]);
                        break;
                    case '5':
                        sectionCombox.setSelectedItem(sections[4]);
                        break;
                    case '6':
                        sectionCombox.setSelectedItem(sections[5]);
                        break;
                    case '7':
                        sectionCombox.setSelectedItem(sections[6]);
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
}
