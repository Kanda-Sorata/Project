public class Anime extends Série {
    private Manga manga;

    public Anime(String title, int nbEpisodes, int nbSeason, double rate, String format, Manga manga){
        super(title, nbEpisodes, nbSeason, rate, format);
    }

    @Override
    public String toString(){ return super.toString() + "nAdapatation du manga : " + manga.getNom() + "\n"; }
}
