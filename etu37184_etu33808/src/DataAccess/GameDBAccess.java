package DataAccess;

import BusinessLogic.GameDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchGameList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GameDBAccess implements GameDataAccess {

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, int number, String character, GregorianCalendar dateEnd) throws DataException, DataAccessException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();

            java.sql.Date sqlDate = new java.sql.Date(dateEnd.getTimeInMillis());

            String query = "select game.name, game.releasedate, server.name as serverName "
                         + "from playeraccount, `character`, server, game "
                         + "where playeraccount.id = (select id from playeraccount where pseudo = ? and `number` = ?) "
                         + "and `character`.name = ? and `character`.playeraccountid = playeraccount.id "
                         + "and server.technicalId = `character`.servertechnicalid and game.name = server.gamename "
                    + "and game.releaseDate <= ? and `character`.creationDate >= game.releaseDate;";


            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setInt(2, number);
            statement.setString(3, character);
            statement.setDate(4, sqlDate);

            ResultSet data = statement.executeQuery();

            ArrayList<SearchGameList> searchGameLists = new ArrayList<>();
            SearchGameList searchGameList;

            while(data.next()){
                searchGameList = new SearchGameList(data.getString("name"), null,
                        data.getString("serverName"));
                java.sql.Date releaseDate = data.getDate("releaseDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(releaseDate);
                searchGameList.setReleaseDate(calendar);

                searchGameLists.add(searchGameList);
            }

            return searchGameLists;
        }catch (ConnectionException connectionException){
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public ArrayList<String> getAllGames() throws DataException, DataAccessException{
        Connection connection;
        try{
            connection = SingletonConnection.getInstance();

            String query = "select game.name from game";
            PreparedStatement statement = connection .prepareStatement(query);

            ResultSet data = statement.executeQuery();
            ArrayList<String> allGames = new ArrayList<>();

            String game;
            while(data.next()){
                    game = data.getString("name");
                    allGames.add(game);
            }

            return allGames;
        } catch (ConnectionException connectionException){
            throw new DataAccessException(1);
        } catch (SQLException sqlException){
            throw new DataException(1);
        }
    }

    public ArrayList<String> getAllGamesName(String pseudoChoice, int numberChoice) throws DataAccessException, DataException {
        Connection connection;
        try{
            connection = SingletonConnection.getInstance();

            String query = "select game.name from game, playeraccount, acquisition " +
                            "where playeraccount.id = (select id from playeraccount where pseudo = ? and number = ?) " +
                            "and playeraccount.id = acquisition.playeraccountid and acquisition.gamename = game.name;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudoChoice);
            statement.setInt(2, numberChoice);

            ResultSet data = statement.executeQuery();
            ArrayList<String> gamesName = new ArrayList<>();

            while(data.next()){
                gamesName.add(data.getString("name"));
            }

            return gamesName;
        }catch (ConnectionException connectionException){
            throw new DataAccessException(1);
        }catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

}