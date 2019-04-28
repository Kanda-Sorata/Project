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
            java.sql.Date date = new java.sql.Date(dateEnd.getTimeInMillis());

            String querry = "select `game`.`name`, `game`.`releasedate`, `server`.`name` as 'nameServer' from `game`, `server`, ";
                   querry += "`character`, `playeraccount` where `game`.`name` = `server`.`gamename` and ";
                   querry += "`character`.`serverTechnicalId` = `server`.`technicalid` ";
                   querry += "and `character`.`playeraccountid` = (select id from `playeraccount` where `pseudo` = ? ";
                   querry += " and `number` = ? ) and `character`.`name` = ? and  `game`.`releaseDate` <= ? ;";
            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, pseudo);
            statement.setString(2, number);
            statement.setString(3, character);
            statement.setDate(4, date);

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
            throw new AllGamesException(0);
        }
    }

}
