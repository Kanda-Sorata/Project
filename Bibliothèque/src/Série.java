public class Série extends Media{
    private int nbEpisodes;
    private int nbSeason;
    //Tableau ou collection de lien vers autre saison
        public Série(String title, int nbEpisodes, int nbSeason, double rate, String format){
        super(title, format, rate);
        this.nbEpisodes = nbEpisodes;
        this.nbSeason = nbSeason;
    }

    @Override
    public String toString(){
        return super.toString() + "Nb_Ep: " + nbEpisodes + "\n" + "Nb_Season: " + nbSeason + "\n";
    }
}
