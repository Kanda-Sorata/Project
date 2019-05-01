package DataAccess;

import BusinessLogic.ServerDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerDBAccess implements ServerDataAccess {
    @Override
    public ArrayList<String> getAllServersName(String pseudoChoice, String numberChoice, String game) throws DataException, DataAccessException {
        try{
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select  server.name from playeraccount, game, server, acquisition ";
            querry += "and playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) ";
            querry += "where server.gamename = game.name and acquisition.playerzccountid = playeraccount.id ";
            querry += "an acquisition.Gamename = ?";

            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, pseudoChoice);
            statement.setString(2, numberChoice);
            statement.setString(3, game);

            ResultSet data = statement.executeQuery();
            ArrayList<String> servers = new ArrayList<>();

            while(data.next()){
                servers.add(data.getString("name"));
            }
            return servers;

        }catch(ConnectionException connectionEwception){
            throw new DataException(0);
        }catch(SQLException sqlException){
            throw new DataAccessException();
        }
    }
}
