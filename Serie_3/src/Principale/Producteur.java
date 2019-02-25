package Principale;

import java.io.*;
import Principale.Student;

public class Producteur extends Thread  {

    private ZoneCommune z;

    public Producteur(ZoneCommune z) {
        this.z = z;
    }

    public void run(){
        ObjectInputStream entree = null;
        Student student;

        try {
            FileInputStream file = new FileInputStream("student.txt");
            entree = new ObjectInputStream(file);
            for(int i = 0; i < 10; i++) {
                try {
                    Thread.sleep((int) (Math.random() * 3000));
                } catch (InterruptedException iException) {
                    iException.printStackTrace();
                }

                try {
                    student = (Student) entree.readObject();
                    z.setStudent(student);
                } catch (EOFException eofException) {
                    System.out.println("Plus d'enregistrement");
                } catch (Exception exception) {
                    System.out.println("Erreur autre que lecture");
                }
            }
        }
        catch(IOException ioException){
            System.out.println("Erreur lecture fichier");
        }
        finally{
            if (entree != null){
                try {
                    entree.close();
                }
                catch (IOException iException){
                    System.out.println(iException.getMessage());
                }
            }
        }

    }
}
