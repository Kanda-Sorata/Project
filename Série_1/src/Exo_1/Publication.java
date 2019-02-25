package Exo_1;

public class Publication {
    private String title;
    private int editionYear;
    private Editor editor;

    public Publication(String title, int editionYear, Editor editor){
        this.title = title;
        this.editionYear = editionYear;
        this.editor = editor;
    }

    public String categorie(){
        return "L'ouvrage";
    }

    public String getTitle(){
        return title;
    }

    public Editor getEditor(){
        return editor;
    }

    @Override
    public String toString(){
        return categorie() + " édité en " + editionYear + " par " + editor;
    }
}
