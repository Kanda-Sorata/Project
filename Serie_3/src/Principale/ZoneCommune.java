package Principale;

import Principale.Student;

public class ZoneCommune {
    private boolean aEcraser = true;
    private Student student = null;

    public synchronized  Student getStudent(){ //Synchronised permet d'éviter qu'on utilise la même méthode en même temps sur le même objet
        if(aEcraser)
        try{
            wait();
        }
        catch(InterruptedException iException){
            iException.printStackTrace();
        }

        System.out.println("Consummer " + Thread.currentThread().getName() + " lit l'étudiant " + student);
        aEcraser = true;

        notifyAll(); //Reveille 1 thread en attente
        return student;
    }

    public synchronized void setStudent(Student student){
        if(!aEcraser)
            try{
                wait();
            }
        catch(InterruptedException iException){
                iException.printStackTrace();
        }

        this.student = student;
        System.out.println("Producer " + Thread.currentThread().getName() + " ecrit l'étudiant " + student);
        aEcraser = false;
        notifyAll();
    }
}
