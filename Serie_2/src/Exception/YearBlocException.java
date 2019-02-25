package Exception;

public class YearBlocException extends Exception{
    private int yearBlocError;

    public YearBlocException(int yearBlocError){
        this.yearBlocError = yearBlocError;
    }

    public String getMessage(){
        return "Les valeurs possible sont 1, 2, 3 !\nValeur utilisateur: " + yearBlocError;
    }
}
