package DataAccess;

import BusinessLogic.CharacterDataAccess;
import Exception.*;
import Model.Character;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class CharacterDBAccess implements CharacterDataAccess {

    public CharacterDBAccess(){}

    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String query = "select name, healthPoint, isStuffed, creationDate, petName, damagePerSecond " +
                            "from `character` where `character`.`playeraccountId` = (select id from `playeraccount` " +
                            "where  pseudo = ? and number = ?);";

            PreparedStatement statement = dataConnection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);

            ResultSet data = statement.executeQuery();
            Character character;
            ArrayList<Character> characters = new ArrayList<>();
            String petName;
            Integer damagePerSecond;

            while (data.next()) {
                character = new Character(data.getString("name"), data.getInt("healthPoint"),
                        data.getBoolean("isStuffed"), null, null, null,
                        null, null);

                java.sql.Date creationDate = data.getDate("creationDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                character.setCreationDate(calendar);

                petName = data.getString("petName");
                if (!data.wasNull()) {
                    character.setPetName(petName);
                }

                damagePerSecond = data.getInt("damagePerSecond");
                if (!data.wasNull()) {
                    character.setDamagePerSecond(damagePerSecond);
                }
                characters.add(character);
            }
            return characters;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException();
        } catch (SQLException sqlException) {
            throw new DataException(0);
        } catch (HealthPointsException healthPointsException) {
            throw new DataException(2);
        } catch (DamagePerSecondException damagePerSecondException) {
            throw new DataException(6);
        }
    }

    public ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();

            String query = "select `character`.name as characName " +
                    "from playeraccount, acquisition, game, server, `character` " +
                    "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) " +
                    "and game.name = ? and acquisition.playeraccountid = playeraccount.id " +
                    "and acquisition.gamename = game.name and server.gamename = game.name " +
                    "and `character`.servertechnicalid = server.technicalid;";

            PreparedStatement statement = dataConnection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, gameName);

            ResultSet data = statement.executeQuery();
            ArrayList<String> characters = new ArrayList<>();

            while(data.next()){
                characters.add(data.getString("characName"));
            }

            return characters;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException();
        } catch (SQLException sqlException) {
            throw new DataException(0); //todo vérifier
        }
    }

    public int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException{
        try{
            Connection dataConnection = SingletonConnection.getInstance();

            String queryTechnicalId = "select `character`.technicalid as characterId " +
                                        "from playeraccount, acquisition, game, server, `character` " +
                                        "where playeraccount.id = (select id from playeraccount " +
                                        "where pseudo = ? and number = ?) and game.name = ? " +
                                        "and `character`.name = ? " +
                                        "and acquisition.playeraccountid = playeraccount.id " +
                                        "and acquisition.gamename = game.name " +
                                        "and server.gamename = game.name " +
                                        "and `character`.servertechnicalid = server.technicalid " +
                                        "and `character`.playeraccountid = playeraccount.id;";

            PreparedStatement statementTechnicalId = dataConnection.prepareStatement(queryTechnicalId);

            statementTechnicalId.setString(1, pseudo);
            statementTechnicalId.setInt(2, number);
            statementTechnicalId.setString(3, gameName);
            statementTechnicalId.setString(4, characterName);

            ResultSet data = statementTechnicalId.executeQuery();

            int technicalId = data.getInt("characterId");

            String queryDelete = "delete from `character` where technicalid = ?";

            PreparedStatement statementDelete = dataConnection.prepareStatement(queryDelete);

            statementTechnicalId.setInt(1, technicalId);

            int state = statementDelete.executeUpdate(); //soit 0 si pas de retour soit le nb lignes
            return state; //todo
        } catch (ConnectionException connectionException){
            throw new DataAccessException();
        } catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //SUPPRIMER
            throw new DataException(0); //todo vérifier
        }
    }
}