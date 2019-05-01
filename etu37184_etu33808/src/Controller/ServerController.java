package Controller;
import BusinessLogic.*;
import Exception.*;

import java.util.ArrayList;

public class ServerController {
    private  ServerBusinessLogic serverBusinessLogic;

    public ServerController() {
        this.serverBusinessLogic = new ServerBusinessLogic();
    }

    public ArrayList<String> getAllServersName(String pseudoChoice, String numberChoice, String Game) throws AllException{
        return serverBusinessLogic.getAllServersName(pseudoChoice, numberChoice, game);
    }


}
