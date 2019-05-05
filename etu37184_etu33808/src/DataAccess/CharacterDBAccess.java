package DataAccess;

import BusinessLogic.CharacterDataAccess;
import Exception.*;
import Model.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class CharacterDBAccess implements CharacterDataAccess {

    public CharacterDBAccess(){}

    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        Connection connection = null;
        try {
           connection = SingletonConnection.getInstance();
            String query = "select name, healthPoint, isStuffed, creationDate, petName, damagePerSecond " +
                            "from `character` where `character`.`playeraccountId` = (select id from `playeraccount` " +
                            "where  pseudo = ? and number = ?);";

            PreparedStatement statement = connection.prepareStatement(query);

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
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        } catch (HealthPointsException healthPointsException) {
            throw new DataException(2);
        } catch (DamagePerSecondException damagePerSecondException) {
            throw new DataException(6);
        }
    }

    public ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException {
        Connection connection = null;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select `character`.name as characName " +
                    "from playeraccount, acquisition, game, server, `character` " +
                    "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) " +
                    "and game.name = ? and acquisition.playeraccountid = playeraccount.id " +
                    "and acquisition.gamename = game.name and server.gamename = game.name " +
                    "and `character`.servertechnicalid = server.technicalid;";

            PreparedStatement statement = connection.prepareStatement(query);

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
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public int deleteACharacter(String pseudo, int number, String gameName, String characterName) throws DataAccessException, DataException {
        if (isDeleteParametersValide(pseudo, number, gameName, characterName)) {
            throw new DataException(8);
        } else {
            Connection connection = null;
            try {
                connection = SingletonConnection.getInstance();

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

                PreparedStatement statementTechnicalId = connection.prepareStatement(queryTechnicalId);

                statementTechnicalId.setString(1, pseudo);
                statementTechnicalId.setInt(2, number);
                statementTechnicalId.setString(3, gameName);
                statementTechnicalId.setString(4, characterName);

                ResultSet data = statementTechnicalId.executeQuery();

                int technicalId = data.getInt("characterId");

                String queryDelete = "delete from `character` where technicalid = ?";

                PreparedStatement statementDelete = connection.prepareStatement(queryDelete);

                statementTechnicalId.setInt(1, technicalId);

                int state = statementDelete.executeUpdate(); //whether 0 no return or nb row modified
                return state;
            } catch (ConnectionException connectionException) {
                throw new DataAccessException(1);
            } catch (SQLException sqlException) {
                throw new DataException(8);
            }
        }
    }

    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException {
        if(isInsertParametersValide(character, pseudo, number, game, server, characterClass)){
            throw new DataException(7);
        }else {
            Connection connection = null;
            try {
               connection = SingletonConnection.getInstance();
                int state = 0;

                String query = "select playeraccount.id from playeraccount where pseudo = ? and number = ?;";
                PreparedStatement statementPlayer = connection.prepareStatement(query);

                statementPlayer.setString(1, pseudo);
                statementPlayer.setInt(2, number);

                ResultSet data = statementPlayer.executeQuery();
                Integer playerAccountId;

                if (data.next()) {
                    playerAccountId = data.getInt(1);

                    String queryCharacterClass = "select characterClass.technicalId from characterClass where characterClass.name = ? "
                            + "and characterClass.Gamename = ?;";
                    PreparedStatement statementCharacterClassId = connection.prepareStatement(queryCharacterClass);

                    statementCharacterClassId.setString(1, characterClass);
                    statementCharacterClassId.setString(2, game);

                    data = statementPlayer.executeQuery();
                    Integer characterClassTechnicalId;

                    if (data.next()) {
                        characterClassTechnicalId = data.getInt(1);

                        String queryServer = "select server.technicalId from server, game, playeraccount, acquisition "
                                + "where playeraccount.id = ? and game.name = ? and acquisition.playeraccountid = playeraccount.id "
                                + "and acquisition.gamename = game.name and server.Gamename = game.name";

                        PreparedStatement statementServer = connection.prepareStatement(queryServer);

                        statementServer.setInt(1, playerAccountId);
                        statementServer.setString(2, game);

                        data = statementServer.executeQuery();
                        Integer serverTechnicalId;

                        if (data.next()) {
                            serverTechnicalId = data.getInt(1);

                            String queryInsert = "insert into `character`(name, healthPoint, isStuffed, creationDate, petName, "
                                    + "damagePerSecond, playeraccountid, characterClassTechnicalId, serverTechnicalId) "
                                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

                            PreparedStatement statementInsert = connection.prepareStatement(queryInsert);

                            statementInsert.setString(1, character.getName());
                            statementInsert.setInt(2, character.getHealthPoints());
                            statementInsert.setBoolean(3, character.isStuffed());
                            java.sql.Date dateSql = new java.sql.Date(character.getCreationDate().getTimeInMillis());
                            statementInsert.setDate(4, dateSql);
                            statementInsert.setNull(5, Types.VARCHAR);
                            statementInsert.setNull(6, Types.INTEGER);
                            statementInsert.setInt(7, playerAccountId);
                            statementInsert.setInt(8, characterClassTechnicalId);
                            statementInsert.setInt(9, serverTechnicalId);

                            state = statementInsert.executeUpdate();
                        }
                    }
                }
                return state;
            } catch (ConnectionException connectionException) {
                throw new DataAccessException(1);
            } catch (SQLException sqlException) {
                throw new DataException(7);
            }
        }
    }

    private boolean isInsertParametersValide(Character character, String pseudo, int number, String game, String server, String characterClass){
        String noSelection = "No selection";
        return character != null && Pattern.matches("^[a-zA-Z_-]{4,50}", character.getName())
                && character.getHealthPoints() >= Character.getMinHp()
                && character.getHealthPoints() <= Character.getMaxHp() && character.getCreationDate() != null
                && character.getStuffed() != null && pseudo != null && !pseudo.equals(noSelection) && game != null
                && !game.equals(noSelection) && server != null && !server.equals(noSelection)
                && characterClass != null && !characterClass.equals(noSelection);
    }

    private boolean isDeleteParametersValide(String pseudo, int number, String gameName, String characterName){
        String noSelection = "No selection";
        return pseudo != null && !pseudo.equals(noSelection) && gameName != null && !gameName.equals(noSelection)
                && characterName != null && !characterName.equals(noSelection);
    }

    public Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException {
        Connection connection = null;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select `character`.name, `character`.healthPoint, `character`.isStuffed, "
                        + "`character`.creationDate, `character`.petName, `character`.damagePerSecond "
                        + "from playeraccount, `character`, server, game, acquisition "
                        + "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) "
                        + "and acquisition.playeraccountid = playeraccount.id and game.name = ? "
                        + "and acquisition.gamename = game.name "
                        + "and server.technicalId = (select technicalId from server where server.name = ? "
                        + " and server.gamename = game.name) "
                        + "and characterClass.technicalId = (select technicalId from characterClass where name = ? and gamename = ?) "
                        + "and `character`.playeraccountId = playeraccount.id "
                        + "and `character`.CharacterClassTechnicalId = characterClass.technicalId "
                        + "and `character`.ServertechnicalId = server.technicalId";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, game);
            statement.setString(4, server);
            statement.setString(5, characterClass);
            statement.setString(6, game);

            ResultSet data = statement.executeQuery();
            Character character = null;
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
            }

            return character;
        } catch (ConnectionException connectionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        } catch (HealthPointsException healthPointException){
            throw  new DataException(2);
        }catch (DamagePerSecondException damagePerSecondException){
            throw  new DataException(6);
        }
    }
}