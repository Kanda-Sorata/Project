package BusinessLogic;

import DataAccess.ServerDBAccess;
import Exception.ConflictDataException;
import Exception.DataAccessException;

import java.util.ArrayList;

public class ServerBusinessLogic {
    private ServerDataAccess dao;

    public ServerBusinessLogic() {
        setDao(new ServerDBAccess());
    }

    public void setDao(ServerDataAccess dao){
        this.dao = dao;
    }
    public ArrayList<String> getAllServersName(String pseudoChoice, String numberChoice, String game) throws ConflictDataException, DataAccessException {
        return dao.getAllServersName(pseudoChoice, numberChoice, game);
    }
}
