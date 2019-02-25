package Exo_1;

public class BookException extends Exception {

    private Book book;

    public BookException(Book book){
        this.book = book;
    }

    public String getMessage(){
        return "Le livre " + book + " n'a pas été ajouté à votre liste!";
    }


}
