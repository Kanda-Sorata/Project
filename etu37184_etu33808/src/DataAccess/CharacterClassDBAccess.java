package DataAccess;

import BusinessLogic.CharacterClassDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CharacterClassDBAccess implements CharacterClassDataAccess {
    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        Connection connection = null;
        try {
            connection = SingletonConnection.getInstance();
            String query = "select characterClass.name from characterClass, game "
                         + "where characterClass.gamename = ? and characterClass.gamename = game.name;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, game);
            ResultSet data = statement.executeQuery();
            ArrayList<String> allClassesInAGame = new ArrayList<>();

            String characterClass;

            while(data.next()){
                characterClass = data.getString("name");
                allClassesInAGame.add(characterClass);
            }
            return allClassesInAGame;

        } catch (ConnectionException connexionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public ArrayList<String> getAllClassesName(String pseudo, int number, String game)
                                                                            throws DataException, DataAccessException {
        Connection connection = null;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select characterClass.name from characterClass, game, playeraccount, acquisition "
                        + "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) "
                        + "and acquisition.playeraccountid = playeraccount.id and game.name = ? "
                        + "and acquisition.gamename = game.name and characterClass.gamename = game.name ";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, game);

            ResultSet data = statement.executeQuery();

            ArrayList<String> allClasses = new ArrayList<>();

            String characterClass;

            while(data.next()){
                characterClass = data.getString("name");
                allClasses.add(characterClass);
            }
            return allClasses;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }
}
