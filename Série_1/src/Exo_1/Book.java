package Exo_1;

public class Book extends Publication implements IPublication  {
    private int nbPagesSummary;
    private int nbPagesPrologue;
    private int nbPagesContent;

    public Book(String title, int editionYear, Editor editor, int nbPagesSummary, int nbPagesPrologue,
                int nbPagesContent){
        super(title, editionYear, editor);
        this.nbPagesSummary = nbPagesSummary;
        this.nbPagesPrologue = nbPagesPrologue;
        this.nbPagesContent = nbPagesContent;
    }

    public void setNbPagesPrologue(int nbPages){
        nbPagesPrologue = nbPages;
    }

    @Override
    public int totalPages() {
        return nbPagesSummary + nbPagesPrologue + nbPagesContent;
    }

    @Override
    public String categorie() {
        return "Le livre";
    }

    @Override
    public String toString(){
        return super.toString() + " compte " + totalPages() + "pages et est intitulé " + getTitle();
    }
}
