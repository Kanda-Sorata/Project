package DataAccess;

import BusinessLogic.CharacterClassDataAccess;
import Exception.ConflictDataException;
import Exception.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CharacterClassDBAccess implements CharacterClassDataAccess {
    public ArrayList<String> getClassesInAGame(String game) throws ConflictDataException {
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
            throw new ConflictDataException(0);
        } catch (SQLException sqlException) {
            throw new ConflictDataException(1);
        }
    }
}
