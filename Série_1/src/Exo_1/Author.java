package Exo_1;

public class Author {
    private String lastName;
    private String firstName;
    private static final int NB_BOOKS = 5;
    private Book [] books = new Book [NB_BOOKS];

    public Author(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public void addBook(Book book){
        int spot = spotSearch();
        /*if(spot >= NB_BOOKS)
            throw new BookException(book);
        else*/
        if(spot < NB_BOOKS)
            books[spot] = book;
    }

    private int spotSearch(){
        int iBooks = 0;
        while(iBooks < NB_BOOKS && books[iBooks] != null){  iBooks++; }
        return iBooks;
    }

    public int totalPagesWritten(){
        int maxBooks = spotSearch();
        int nbPagesAllBooks = 0;

        for(int iBooks = 0; iBooks < maxBooks; iBooks++){
            nbPagesAllBooks += books[iBooks].totalPages();
        }

        return nbPagesAllBooks;
    }

    public String listingBook(){
        int maxBooks = spotSearch();
        String output ="";

        for(int i = 0; i < maxBooks; i++){
            output += books[i] + "\n";
        }

        return output;
    }
}
