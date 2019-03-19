package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton back;
    private JButton validation;
    private JButton reset;
    private Frame frameReference;

    public ButtonPanel(Frame frameReference){
        setLayout(new FlowLayout());

        this.frameReference = frameReference;

        back = new JButton("Retour");
        validation = new JButton("Validation");
        reset = new JButton("Réinitialiser");

        back.addActionListener(new ButtonListener());
        validation.addActionListener(new ButtonListener());
        reset.addActionListener(new ButtonListener());

        add(back);
        add(validation);
        add(reset);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == back){
                frameReference.getContainer().removeAll();
                frameReference.getContainer().repaint();
                frameReference.setVisible(true);
                JOptionPane.showMessageDialog(null, "Bienvenu(e) Agent!\nNous devons reprendre Washington D.C.!\n", "I.S.A.C", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                if(event.getSource() == reset){
                    frameReference.getRegisterForm().getFormPanel().clearField();
                }
                else{
                    Student student = new Student();
                    student.setMatricule(frameReference.getRegisterForm().getFormPanel().getMatricule().getText());
                    student.setLastName(frameReference.getRegisterForm().getFormPanel().getLastName().getText());
                    student.setFirstName(frameReference.getRegisterForm().getFormPanel().getFirstName().getText());
                    student.setBirthDay(frameReference.getRegisterForm().getFormPanel().getBirthDay().getText());
                    student.setSection(frameReference.getRegisterForm().getFormPanel().getSections()[frameReference.getRegisterForm().getFormPanel().getSectionCombox().getSelectedIndex()]);
                    student.setScholarship(frameReference.getRegisterForm().getFormPanel().getScholarship().isSelected());
                    student.setStranger(frameReference.getRegisterForm().getFormPanel().getStranger().isSelected());
                    student.setOrigins(frameReference.getRegisterForm().getFormPanel().getOrigins()[frameReference.getRegisterForm().getFormPanel().getOriginCombox().getSelectedIndex()]);
                    student.setNewStudent(frameReference.getRegisterForm().getFormPanel().getNewStudent().isSelected());
                    student.setReRegistration(frameReference.getRegisterForm().getFormPanel().getReRegistration().isSelected());

                    JOptionPane.showMessageDialog(null, student, "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
