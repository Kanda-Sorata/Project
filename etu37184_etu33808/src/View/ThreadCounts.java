package View;

import Controller.CharacterController;
import Controller.PlayerAccountController;
import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;

public class ThreadCounts extends Thread {

    private PlayerAccountController playerAccountController;
    private CharacterController characterController;
    private PanelThread panelThread;
    int nbPlayers;
    int nbCharacters;
    double average;

    public ThreadCounts(PanelThread panelThread){
        this.panelThread = panelThread;
        playerAccountController = new PlayerAccountController();
        characterController = new CharacterController();
    }

    public void run(){
        while(true){
            try {
                //Obtaining values
                nbPlayers = playerAccountController.getNbAccountPlayers();
                nbCharacters = characterController.getNbCharacters();
                average = nbPlayers > 0 ? (double) nbCharacters / nbPlayers : 0.0;

                //SetValues
                panelThread.setValueOfAllLabels(nbPlayers, nbCharacters, average);

                //Repaint panel
                panelThread.repaint();
                Thread.sleep(10000);
            } catch(DataAccessException dataAccessException){
                JOptionPane.showMessageDialog(null, dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch(DataException dataException){
                JOptionPane.showMessageDialog(null, dataException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}