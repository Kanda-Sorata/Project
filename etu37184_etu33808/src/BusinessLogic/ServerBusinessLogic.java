package BusinessLogic;

import DataAccess.ServerDBAccess;
import Exception.DataAccessException;
import Exception.DataException;

import java.util.ArrayList;

public class ServerBusinessLogic {
    private ServerDataAccess dao;

    public ServerBusinessLogic() {
        setDao(new ServerDBAccess());
    }

    public void setDao(ServerDataAccess dao){
        this.dao = dao;
    }
    public ArrayList<String> getAllServersName(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException {
        return dao.getAllServersName(pseudoChoice, numberChoice, game);
    }
}
