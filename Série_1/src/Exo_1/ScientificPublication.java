package Exo_1;

public class ScientificPublication extends Book {
    private int nbPagesAnnex;

    public ScientificPublication(String title, int editionYear, Editor editor, int nbPagesSummary, int nbPagesPrologue,
                                 int nbPagesContent, int nbPagesAnnex){
        super(title, editionYear, editor, nbPagesSummary, nbPagesPrologue, nbPagesContent);
        this.nbPagesAnnex = nbPagesAnnex;
    }

    @Override
    public int totalPages(){
        return super.totalPages() + nbPagesAnnex;
    }

    @Override
    public String categorie(){
        return "L'ouvrage scientifique";
    }

}
