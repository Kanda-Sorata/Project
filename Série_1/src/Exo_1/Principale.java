package Exo_1;

public class Principale {
    public static void main(String[] args) {
        Location loc = new Location("Namur", 5000);
        Editor editor = new Editor("Heintz Kelly", loc);
        Book p = new ScientificPublication("Nature", 1997, editor, 2,
                3, 250, 5);
        Author author = new Author("Rousseaux", "Corentin");

        p.setNbPagesPrologue(32);
        author.addBook(p);

        System.out.println(p.getEditor().getName());
        System.out.println(p.getEditor().getLocation().getLabel());

    }
}
