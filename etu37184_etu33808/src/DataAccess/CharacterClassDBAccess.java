package DataAccess;

import BusinessLogic.CharacterClassDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;
import Model.TopOfClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CharacterClassDBAccess implements CharacterClassDataAccess {
    public ArrayList<String> getClassesInAGame(String game) throws DataException, DataAccessException {
        Connection connection;
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

    public ArrayList<String> getAllCharactersClassName(String pseudo, int number, String game) throws DataException, DataAccessException {
        Connection connection;
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

    public ArrayList<TopOfClass> getAllCharacterClassOrderClass() throws DataAccessException, DataException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select count(`character`.name) as 'Characters number', characterclass.name, "
                    + "characterclass.description from `character`, characterClass "
                    + "where characterClass.technicalId = characterClass.technicalId "
                    + "and `character`.characterclassTechnicalId = characterclass.technicalId "
                    + "group by characterClass.name "
                    + "order by count(`Character`.name) desc,  characterclass.name asc";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet data = statement.executeQuery();

            ArrayList<TopOfClass> allClasses = new ArrayList<>();
            TopOfClass topOfClass;

            while(data.next()){
                topOfClass = new TopOfClass(data.getInt("Characters number"), data.getString("name"),
                        data.getString("description"));
                allClasses.add(topOfClass);
            }
            return allClasses;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }
}
