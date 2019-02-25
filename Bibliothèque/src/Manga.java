public class Manga extends Media {
    private String mangaka;
    private Anime [] animes = new Anime [];
    private int nbTomes;

    public Manga(String title, String mangaka, double rate, int nbTomes){
        super(title, null, rate);
        this.mangaka = mangaka;
        this.nbTomes = nbTomes;
    }

    @Override
    public String toString(){
        return super.toString() + "\n" + "Mangaka: " + mangaka + "\n" + "NbTomes: " + nbTomes + "\n";
    }
}
