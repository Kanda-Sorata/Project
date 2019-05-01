package DataAccess;

import BusinessLogic.CharacterClassDataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.ConnectionException;
import Exception.AllCharacterClassException;
import java.util.ArrayList;
import java.sql.Connection;


public class CharacterClassDBAccess implements CharacterClassDataAccess {
    public ArrayList<String> getClassesInAGame(String game) throws AllCharacterClassException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select characterClass.name from characterClass, game ";
            querry += "where characterClass.gamename = ? and characterClass.gamename = game.name;";

            PreparedStatement statement = dataConnection.prepareStatement(querry);
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
            throw new AllCharacterClassException(0);
        } catch (SQLException sqlException) {
            throw new AllCharacterClassException(1);
        }
    }
}
