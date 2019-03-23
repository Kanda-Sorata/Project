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
    private ButtonListener buttonListener;

    public ButtonPanel(Frame frameReference){
        setLayout(new FlowLayout());

        this.frameReference = frameReference;

        buttonListener = new ButtonListener();
        back = new JButton("Retour");
        validation = new JButton("Validation");
        reset = new JButton("Réinitialiser");

        back.addActionListener(buttonListener);
        validation.addActionListener(buttonListener);
        reset.addActionListener(buttonListener);

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

                    setStudent(student);

                    JOptionPane.showMessageDialog(null, student, "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public void setStudent(Student student){
        student.setMatricule(getFormPanel().getMatricule().getText());
        student.setLastName(getFormPanel().getLastName().getText());
        student.setFirstName(getFormPanel().getFirstName().getText());
        student.setBirthDay(getFormPanel().getBirthDay().getText());
        student.setSection(getFormPanel().getSections()[getFormPanel().getSectionCombox().getSelectedIndex()]);
        student.setScholarship(getFormPanel().getScholarship().isSelected());
        student.setStranger(getFormPanel().getStranger().isSelected());
        student.setOrigins(getFormPanel().getOrigins()[getFormPanel().getOriginCombox().getSelectedIndex()]);
        student.setNewStudent(getFormPanel().getNewStudent().isSelected());
        student.setReRegistration(getFormPanel().getReRegistration().isSelected());
    }

    public FormPanel getFormPanel(){
        return frameReference.getRegisterForm().getFormPanel();
    }
}
