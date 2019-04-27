package Exception;

import View.AllGameFromCharacterModel;

public class AllGamesException extends Exception {
    private int code;

    public AllGamesException(int code){
        this.code = code;
    }

    public String getMessage(){
        return "";
    }

}
