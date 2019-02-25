package Principale;

import Exception.YearException;
import java.io.*;

public class Principale {
    public static void main(String[] args) {
/*
        Student[] etus = new Student[10];

        try {
            StudyingYear year1 = new StudyingYear("IG", 1, "Bac");
            StudyingYear year2 = new StudyingYear("SECU", 1, "Bac");
            StudyingYear year3 = new StudyingYear("TI", 1, "Master");


            etus[0] = new Student("Heintz", "Kelly", 'f', year1);
            etus[1] = new Student("Heintz", "Cathy", 'f', year2);
            etus[2] = new Student("Rousseaux", "Corentin", 'm', year1);
            etus[3] = new Student("Janssens", "Florian", 'm', year1);
            etus[4] = new Student("Zonowatnik", "Joris", 'm', year2);
            etus[5] = new Student("Brard", "Noémie", 'f', year1);
            etus[6] = new Student("Pinto", "Ryan", 'm', year3);
            etus[7] = new Student("Tcho", "Nicholas", 'm', year3);
            etus[8] = new Student("Rousseaux", "Jean-Marie", 'm', year3);
            etus[9] = new Student("Maria", "Lisa", 'f', year2);
        } catch (YearException year) {
            System.out.println(year.getMessage());
        }

        //writing file
        ObjectOutputStream sortie = null;
        try {
            sortie = new ObjectOutputStream(new FileOutputStream(("student.txt")));
            for (int i = 0; i < 10; i++) {
                sortie.writeObject(etus[i]);
            }

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            if (sortie != null) {
                try {
                    sortie.close();
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            }
        }


        //ViewFile

        ObjectInputStream entree = null;
        try {
            entree = new ObjectInputStream(new FileInputStream("student.txt"));
            Student student;
            student = (Student) entree.readObject();
            while (student != null) {
                System.out.println(student);
                student = (Student) entree.readObject();
            }
        } catch (EOFException eofException) {
            System.out.println("Plus d'enregistrement");
        } catch (IOException ioException) {
            System.out.println("Erreur lecture fichier");
        } catch (Exception exception) {
            System.out.println("Erreur autre que lecture");
        } finally {
            if (entree != null) {
                try {
                    entree.close();
                    System.out.println("Fichier fermé");
                } catch (IOException iException) {
                    System.out.println(iException.getMessage());
                }
            }
        }
*/
        //Threads

        ZoneCommune students = new ZoneCommune();
        Producteur p = new Producteur(students);
        Consommateur c = new Consommateur(students);

        p.start();
        c.start();

    }
}