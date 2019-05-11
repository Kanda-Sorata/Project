package View;

import Controller.AccountPlayerController;
import Controller.CharacterController;
import Exception.DataAccessException;
import Exception.DataException;

import javax.swing.*;
import java.text.DecimalFormat;

public class ThreadCounts extends Thread {

    private AccountPlayerController accountPlayerController;
    private CharacterController characterController;
    private JLabel resultPlayers;
    private JLabel resultCharacters;
    private JLabel resultAverage;
    private PanelThread panelThread;
    private DecimalFormat decimalFormat;
    int nbPlayers;
    int nbCharacters;
    double average;

    public ThreadCounts(PanelThread panelThread){
        this.panelThread = panelThread;
        accountPlayerController = new AccountPlayerController();
        characterController = new CharacterController();
        decimalFormat = new DecimalFormat("#.##");
    }

    public void run(){
        //compte le nombre de personnages dans la db et le nombre de joueurs dans la db toutes les 10 secondes
        while(true){
            try {
                nbPlayers = accountPlayerController.getNbAccountPlayers();
                nbCharacters = characterController.getNbCharacters();
                average = (double)nbCharacters/nbPlayers;
                resultPlayers = new JLabel("Total of players : " + nbPlayers);
                resultCharacters = new JLabel("Total of characters : " + nbCharacters);
                resultAverage = new JLabel("Average number of characters for a player account : " + decimalFormat.format(average));
                panelThread.add(resultPlayers);
                panelThread.add(resultCharacters);
                panelThread.add(resultAverage);
                panelThread.revalidate();
                panelThread.repaint();
                Thread.sleep(10000);
                panelThread.removeAll();

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