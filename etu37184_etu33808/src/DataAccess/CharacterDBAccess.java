package DataAccess;

import BusinessLogic.CharacterDataAccess;
import Exception.*;
import Model.Character;
import Model.DisplayCharacter;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class CharacterDBAccess implements CharacterDataAccess {
    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        Connection connection;
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

            while (data.next()) {
                character = new Character(data.getString("name"), (Integer)data.getObject("healthPoint"),
                        (Boolean)data.getObject("isStuffed"), null, data.getString("petName"),
                        (Integer)data.getObject("damagePerSecond"), null, null);

                java.sql.Date creationDate = data.getDate("creationDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                character.setCreationDate(calendar);

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
            throw new DataException(5);
        }
    }

    public ArrayList<String> getAllCharactersInAGame(String pseudo, int number, String gameName) throws DataException, DataAccessException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select `character`.name as characName " +
                    "from playeraccount, acquisition, game, server, `character` " +
                    "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) " +
                    "and game.name = ? and acquisition.playeraccountid = playeraccount.id " +
                    "and acquisition.gamename = game.name and server.gamename = game.name " +
                    "and `character`.servertechnicalid = server.technicalid " +
                    "and `character`.playeraccountid = playeraccount.id;";

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
        if (!isDeleteParametersValid(pseudo, number, gameName, characterName)) {
            throw new DataException(7);
        } else {
            int state = 0;
            Connection connection;
            try {
                connection = SingletonConnection.getInstance();

                String queryTechnicalId = "select `character`.technicalid " +
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
                if(data.next()) {

                    int technicalId = data.getInt("technicalId");

                    String queryDelete = "delete from `character` where technicalid = ?";

                    PreparedStatement statementDelete = connection.prepareStatement(queryDelete);

                    statementDelete.setInt(1, technicalId);

                   state = statementDelete.executeUpdate(); //whether 0 no return or nb row modified
                }
                return state;
            } catch (ConnectionException connectionException) {
                throw new DataAccessException(1);
            } catch (SQLException sqlException) {
                throw new DataException(7);
            }
        }
    }

    public int insertACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException {
        if (!isInsertParametersValid(character, pseudo, number, game, server, characterClass)) {
            throw new DataException(6);
        }else {
            Connection connection;
            try {
                connection = SingletonConnection.getInstance();
                int state = 0;

                String query = "select playeraccount.id from playeraccount where pseudo = ? and number = ?;";
                PreparedStatement statementPlayer = connection.prepareStatement(query);

                statementPlayer.setString(1, pseudo);
                statementPlayer.setInt(2, number);

                ResultSet data = statementPlayer.executeQuery();
                int playerAccountId;

                if (data.next()) {
                    playerAccountId = data.getInt(1);

                    String queryCharacterClass = "select characterClass.technicalId from characterClass where characterClass.name = ? "
                            + "and characterClass.Gamename = ?;";
                    PreparedStatement statementCharacterClassId = connection.prepareStatement(queryCharacterClass);

                    statementCharacterClassId.setString(1, characterClass);
                    statementCharacterClassId.setString(2, game);

                    data = statementCharacterClassId.executeQuery();
                    int characterClassTechnicalId;

                    if (data.next()) {
                        characterClassTechnicalId = data.getInt(1);

                        String queryServer = "select server.technicalId from server, game, playeraccount, acquisition "
                                + "where playeraccount.id = ? and game.name = ? and acquisition.playeraccountid = playeraccount.id "
                                + "and acquisition.gamename = game.name and server.Gamename = game.name and server.name = ?";

                        PreparedStatement statementServer = connection.prepareStatement(queryServer);

                        statementServer.setInt(1, playerAccountId);
                        statementServer.setString(2, game);
                        statementServer.setString(3, server);

                        data = statementServer.executeQuery();
                        int serverTechnicalId;

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
                            if (character.getPetName() == null || character.getPetName().isEmpty()) {
                                statementInsert.setNull(5, Types.VARCHAR);
                            } else {
                                statementInsert.setString(5, character.getPetName());
                            }
                            if (character.getDamagePerSecond() == null) {
                                statementInsert.setNull(6, Types.INTEGER);
                            } else {
                                statementInsert.setInt(6, character.getDamagePerSecond());
                            }
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
                throw new DataException(6);
            }
        }
    }

    public int modifyACharacter(Character character, String pseudo, int number, String game, String server, String characterClass) throws DataException, DataAccessException {
        if (!isInsertParametersValid(character, pseudo, number, game, server, characterClass)) {
            throw new DataException(8);
        } else {
            Connection connection;
            try {
                connection = SingletonConnection.getInstance();
                int state = 0;

                String query = "select playeraccount.id from playeraccount where pseudo = ? and number = ?;";
                PreparedStatement statementPlayer = connection.prepareStatement(query);

                statementPlayer.setString(1, pseudo);
                statementPlayer.setInt(2, number);

                ResultSet data = statementPlayer.executeQuery();
                int playerAccountId;

                if (data.next()) {
                    playerAccountId = data.getInt(1);

                    String queryCharacterClass = "select characterClass.technicalId from characterClass where characterClass.name = ? "
                            + "and characterClass.Gamename = ?;";
                    PreparedStatement statementCharacterClassId = connection.prepareStatement(queryCharacterClass);

                    statementCharacterClassId.setString(1, characterClass);
                    statementCharacterClassId.setString(2, game);

                    data = statementCharacterClassId.executeQuery();
                    int characterClassTechnicalId;

                    if (data.next()) {
                        characterClassTechnicalId = data.getInt(1);

                        String queryServer = "select server.technicalId from server, game, playeraccount, acquisition "
                                + "where playeraccount.id = ? and game.name = ? and acquisition.playeraccountid = playeraccount.id "
                                + "and acquisition.gamename = game.name and server.Gamename = game.name and server.name = ?";

                        PreparedStatement statementServer = connection.prepareStatement(queryServer);

                        statementServer.setInt(1, playerAccountId);
                        statementServer.setString(2, game);
                        statementServer.setString(3, server);

                        data = statementServer.executeQuery();
                        int serverTechnicalId;

                        if (data.next()) {
                            serverTechnicalId = data.getInt(1);

                            String queryInsert = "update `character` set name = ?, healthPoint = ?, isStuffed = ?, creationDate = ?, petName = ?, "
                                    + "damagePerSecond = ?, playeraccountid = ?, characterClassTechnicalId = ?, serverTechnicalId = ? "
                                    + "where `character`.playeraccountId = ? and `character`.name = ?";

                            PreparedStatement statementUpdate = connection.prepareStatement(queryInsert);

                            statementUpdate.setString(1, character.getName());
                            statementUpdate.setInt(2, character.getHealthPoints());
                            statementUpdate.setBoolean(3, character.isStuffed());
                            java.sql.Date dateSql = new java.sql.Date(character.getCreationDate().getTimeInMillis());
                            statementUpdate.setDate(4, dateSql);
                            if (character.getPetName() == null || character.getPetName().isEmpty()) {
                                statementUpdate.setNull(5, Types.VARCHAR);
                            } else {
                                statementUpdate.setString(5, character.getPetName());
                            }
                            if (character.getDamagePerSecond() == null) {
                                statementUpdate.setNull(6, Types.INTEGER);
                            } else {
                                statementUpdate.setInt(6, character.getDamagePerSecond());
                            }
                            statementUpdate.setInt(7, playerAccountId);
                            statementUpdate.setInt(8, characterClassTechnicalId);
                            statementUpdate.setInt(9, serverTechnicalId);
                            statementUpdate.setInt(10, playerAccountId);
                            statementUpdate.setString(11, character.getName());


                            state = statementUpdate.executeUpdate();
                        }
                    }
                }
                return state;
            } catch (ConnectionException connectionException) {
                throw new DataAccessException(1);
            } catch (SQLException sqlException) {
                throw new DataException(8);
            }
        }
    }


    private boolean isInsertParametersValid(Character character, String pseudo, int number, String game, String server, String characterClass) {
        String noSelection = "No selection";
        return character != null && !character.getName().isEmpty() && Pattern.matches("^[a-zA-Z_-]{4,50}", character.getName())
                && character.getHealthPoints() >= Character.getMinHp()
                && character.getHealthPoints() <= Character.getMaxHp() && character.getCreationDate() != null
                && character.getStuffed() != null
                && (character.getDamagePerSecond() == null || (character.getDamagePerSecond() >= Character.getMinDmg()
                && character.getDamagePerSecond() <= Character.getMaxDmg())) && pseudo != null
                && !pseudo.equals(noSelection) && game != null
                && !game.equals(noSelection) && server != null && !server.equals(noSelection)
                && characterClass != null && !characterClass.equals(noSelection);
    }

    private boolean isDeleteParametersValid(String pseudo, int number, String gameName, String characterName) {
        String noSelection = "No selection";
        return pseudo != null && !pseudo.equals(noSelection) && gameName != null && !gameName.equals(noSelection)
                && characterName != null && !characterName.equals(noSelection) && String.valueOf(number).length() == 5;
    }

    public Character getOneCharacter(String pseudo, int number, String game, String server, String characterClass, String character) throws DataException, DataAccessException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select  `character`.name, `character`.healthPoint, `character`.isStuffed, "
                    + "`character`.creationDate, `character`.petName, `character`.damagePerSecond "
                    + "from playeraccount, acquisition, game, server, characterclass, `character` "
                    + "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) "
                    + "and acquisition.playeraccountid = playeraccount.id and game.name = ? "
                    + "and acquisition.gamename = game.name "
                    + "and server.technicalId = (select technicalId from server where name = ? and gamename = game.name) "
                    + "and characterclass.technicalId = (select technicalId from characterclass where name = ? and gameName = game.name) "
                    + "and `character`.ServertechnicalId = server.technicalId "
                    + "and `character`.characterclassTechnicalId = characterclass.technicalId "
                    + "and `character`.playeraccountId = playeraccount.id;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, game);
            statement.setString(4, server);
            statement.setString(5, characterClass);

            ResultSet data = statement.executeQuery();
            Character characterToFill = null;

            while (data.next()) {
                characterToFill = new Character(data.getString("name"), (Integer)data.getObject("healthPoint"),
                        (Boolean)data.getObject("isStuffed"), null, data.getString("petName"),
                        (Integer)data.getObject("damagePerSecond"), null, null);

                java.sql.Date creationDate = data.getDate("creationDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                characterToFill.setCreationDate(calendar);
            }

            return characterToFill;
        } catch (ConnectionException connectionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        } catch (HealthPointsException healthPointException){
            throw  new DataException(2);
        } catch (DamagePerSecondException damagePerSecondException) {
            throw new DataException(5);
        }
    }

    public ArrayList<String> getAllCharactersInAGameInServerWithCharacterClass(String pseudo, int number, String game,
                                                                               String server, String characterClass) throws DataException, DataAccessException{
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select `character`.name "
                    + "from `character`, playeraccount, game, server, characterclass, acquisition "
                    + "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) "
                    + "and game.name = ? "
                    + "and acquisition.playeraccountid = playeraccount.id "
                    + "and acquisition.gameName = game.name "
                    + "and server.technicalid = (select technicalid from server where name =  ? and gamename = game.name) "
                    + "and characterclass.technicalid = (select technicalid from characterclass where name = ? and gamename = game.name) "
                    + "and `character`.playeraccountid = playeraccount.id "
                    + "and `character`.servertechnicalid = server.technicalid "
                    + "and `character`.characterclasstechnicalid = characterclass.technicalid;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, game);
            statement.setString(4, server);
            statement.setString(5, characterClass);

            ResultSet data = statement.executeQuery();
            ArrayList<String> characters = new ArrayList<>();

            while (data.next()) {
                characters.add(data.getString("name"));
            }

            return characters;
        } catch (ConnectionException connectionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public ArrayList<DisplayCharacter> getAllInfosCharacters(String pseudoChoice, int numberChoice) throws DataException, DataAccessException {
        Connection connection;

        try {
            ArrayList<DisplayCharacter> characters = new ArrayList<>();
            connection = SingletonConnection.getInstance();

            String query = "select game.name as GameName, server.name as ServerName, `character`.name as CharacterName, " +
                    "characterclass.name as CharacterClassName, `character`.healthpoint, `character`.isStuffed, " +
                    "`character`.creationDate, `character`.petname, `character`.damagepersecond " +
                    "from `character`, playeraccount, server, characterclass, game " +
                    "where playeraccount.pseudo = ? and playeraccount.number = ? " +
                    "and characterclass.name in (select characterclass.name from characterclass, `character` " +
                    "where `character`.characterclasstechnicalid = characterclass.technicalid) " +
                    "and server.name in (select server.name from server, `character` where `character`.servertechnicalid = server.technicalid) " +
                    "and `character`.characterclasstechnicalid = characterclass.technicalid " +
                    "and `character`.playeraccountid = playeraccount.id " +
                    "and `character`.servertechnicalid = server.technicalid " +
                    "and game.name in (select game.name from game, server where server.gamename = game.name) " +
                    "and server.gamename = game.name";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudoChoice);
            statement.setInt(2, numberChoice);

            ResultSet data = statement.executeQuery();
            DisplayCharacter currentChar;

            while (data.next()) {
                currentChar = new DisplayCharacter(data.getString("GameName"), data.getString("ServerName"), data.getString("CharacterName"),
                        data.getString("CharacterClassName"), (Integer)data.getObject("healthPoint"),
                        (Boolean)data.getObject("isStuffed"), null, data.getString("petName"),
                        (Integer)data.getObject("damagePerSecond"));

                java.sql.Date creationDate = data.getDate("creationDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                currentChar.setCreationDate(calendar);
                characters.add(currentChar);
            }

            return characters;
        } catch (ConnectionException connectionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public int getNbCharacters() throws DataException, DataAccessException {
        Connection connection;
        int nbCharacters = 0;

        try {
            connection = SingletonConnection.getInstance();
            String query = "select count(*) from `character`;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();

            if(data.next()) {
                nbCharacters = data.getInt(1);
            }
            return nbCharacters;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    private String getOneCharacterToCompare(String pseudo, int number, String game, String server, String characterName) throws DataException, DataAccessException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();
            String query = "select `character`.name from `character`, playeraccount, server "
                    + "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) "
                    + "and server.technicalId = (select technicalId from server where server.name = ? and server.gamename = ?) "
                    + "and `character`.technicalId = (select `character`.technicalId from `character`, playeraccount, "
                    + " server where `character`.playeraccountId = playeraccount.id  "
                    + "and `character`.servertechnicalId = server.technicalId and `character`.name = ?);";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, server);
            statement.setString(4, game);
            statement.setString(5, characterName);

            ResultSet data = statement.executeQuery();
            String character = null;

            if (data.next()) {
                character = data.getString(1);
            }

            return character;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public boolean notTheSameName(String pseudo, int number, String game, String server, String characterName) throws DataException, DataAccessException {
        String character = getOneCharacterToCompare(pseudo, number, game, server, characterName);
        return character == null;
    }
}