package DataAccess;

import BusinessLogic.GameDataAccess;
import Exception.AllGamesException;
import Exception.ConnectionException;
import Exception.UniqueNameException;
import Model.SearchGameList;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class GameDBAccess implements GameDataAccess {

    public ArrayList<SearchGameList> getSearchAllGamesListCharacter(String pseudo, String number, String character,
                                                                    GregorianCalendar dateEnd) throws AllGamesException{
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select g.name, g.releaseDate, s.name from AccountPlayer a, Game g, Server s, Character c ";
            querry += "where a.pseudo = " + pseudo + " and a.number = " + number  + " and c.name = ";
            querry += character + " and c.PlayerAccountId = a.id ";
            querry += "and c.ServertechnicalId = s.technicalId and s.Gamename = g.name and g.releaseDate <=" + dateEnd;
            querry += " and colonne1 = ? and colonne2 = ? and colonne3 = ?;";

            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, "");
            GregorianCalendar calendar = new GregorianCalendar();
            java.sql.Date date = new Date(calendar.getTimeInMillis());
            statement.setDate(2, date);
            statement.setString(3, "");

            ResultSet data = statement.executeQuery();

            ArrayList<SearchGameList> searchGameLists = new ArrayList<>();
            SearchGameList searchGameList;

            while(data.next()){
                searchGameList = new SearchGameList(data.getString("name"), null,
                        data.getString("server"));
                java.sql.Date releaseDate = data.getDate("releaseDate");
                calendar = new GregorianCalendar();
                calendar.setTime(releaseDate);
                searchGameList.setReleaseDate(calendar);

                searchGameLists.add(searchGameList);
            }
            return searchGameLists;

        }catch (ConnectionException connectionException){
            throw new AllGamesException(0);
        } catch (SQLException sqlException) {
            throw new AllGamesException(0);
        }catch (UniqueNameException uniqueNameException){
            throw new AllGamesException(1);
        }
    }

}
