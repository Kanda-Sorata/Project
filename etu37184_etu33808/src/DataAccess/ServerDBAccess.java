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
    public ArrayList<String> getAllServersName(String pseudoChoice, int numberChoice, String game) throws DataException, DataAccessException {
        Connection connection;
        try{
            connection = SingletonConnection.getInstance();

            String query = "select  server.name from playeraccount, game, server, acquisition "
                         + "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) "
                         + "and game.name = ? and server.Gamename = game.name "
                         + "and acquisition.Gamename = ? and acquisition.playeraccountid = playeraccount.id ";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudoChoice);
            statement.setInt(2, numberChoice);
            statement.setString(3, game);
            statement.setString(4, game);

            ResultSet data = statement.executeQuery();
            ArrayList<String> servers = new ArrayList<>();

            while(data.next()){
                servers.add(data.getString("name"));
            }

            return servers;
        }catch(ConnectionException connectionException){
            throw new DataAccessException(1);
        }catch(SQLException sqlException){
            throw new DataException(1);
        }
    }
}
