package DataAccess;

import BusinessLogic.GameDataAccess;
import Exception.AllGamesException;
import Exception.ConnectionException;
import Model.SearchGameList;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GameDBAccess implements GameDataAccess {

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character,
                                                                    GregorianCalendar dateEnd) throws AllGamesException{
        try {
            Connection dataConnection = SingletonConnection.getInstance();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateDate = new java.sql.Date(dateEnd.getTimeInMillis());

            String querry = "select game.name, game.releasedate, server.name as serverName ";
            querry += "from playeraccount, `character`, server, game ";
            querry += "where playeraccount.id = (select id from playeraccount where pseudo = ? and `number` = ?) ";
            querry += "and `character`.name = ? and `character`.playeraccountid = playeraccount.id ";
            querry += "and server.technicalId = `character`.servertechnicalid and game.name = server.gamename";
            querry += "and game.releaseDate <= STR_TO_DATE(?, '%Y-%m-%d');";

            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, pseudo);
            statement.setString(2, number);
            statement.setString(3, character);
            statement.setString(4, dateFormat.format(dateDate));

            ResultSet data = statement.executeQuery();

            ArrayList<SearchGameList> searchGameLists = new ArrayList<>();
            SearchGameList searchGameList;

            while(data.next()){
                searchGameList = new SearchGameList(data.getString("name"), null,
                        data.getString("nameServer"));
                java.sql.Date releaseDate = data.getDate("releaseDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(releaseDate);
                searchGameList.setReleaseDate(calendar);

                searchGameLists.add(searchGameList);
            }
            return searchGameLists;

        }catch (ConnectionException connectionException){
            throw new AllGamesException(0);
        } catch (SQLException sqlException) {
            throw new AllGamesException(1);
        }
    }

    public ArrayList<String> getAllGames() throws AllGamesException{
        try{
            Connection dataConnection = SingletonConnection.getInstance();

            String querry = "select game.name from game";
            PreparedStatement statement = dataConnection .prepareStatement(querry);

            ResultSet data = statement.executeQuery();
            ArrayList<String> allGames = new ArrayList<>();

            String game;
            while(data.next()){
                    game = data.getString("name");
                    allGames.add(game);
            }
            return allGames;

        } catch (ConnectionException connectionException){
            throw new AllGamesException(0);
        } catch (SQLException sqlException){
            throw new AllGamesException(1);
        }
    }

}