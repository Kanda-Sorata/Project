package Controller;

import BusinessLogic.ServerBusinessLogic;
import Exception.ConflictDataException;

import java.util.ArrayList;

public class ServerController {
    private  ServerBusinessLogic serverBusinessLogic;

    public ServerController() {
        this.serverBusinessLogic = new ServerBusinessLogic();
    }

    public ArrayList<String> getAllServersName(String pseudoChoice, String numberChoice, String game) throws ConflictDataException {
        return serverBusinessLogic.getAllServersName(pseudoChoice, numberChoice, game);
    }


}
