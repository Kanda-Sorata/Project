package DataAccess;

import BusinessLogic.GameDataAccess;
import Model.Game;
import Exception.*;
import Model.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class GameDBAccess implements GameDataAccess {

    public HashMap<Game, ArrayList<Server>> getAllGames() throws AllGamesException{
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select * from Game g, Server s where g.Servername = s.name and colonne1 = ? ";
            querry += "and colonne2 = ? and colonne3 = ? and colonne4 = ? and colonne5 = ? and colonne6 = ? ";
            querry += "and colonne7 = ? and colonne8 = ? and colonne9 = ? and colonne10 = ? and colonne11 = ? ";
            querry += "and colonne12 = ?";

            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, "");
            GregorianCalendar calendar = new GregorianCalendar();
            java.sql.Date date = new Date(calendar.getTimeInMillis());
            statement.setDate(2, date);
            statement.setBoolean(3, false);
            statement.setDouble(4, 1.0);
            statement.setString(5, "");
            statement.setString(6, "");
            statement.setString(7, "");
            statement.setDate(8, date);
            statement.setBoolean(9, false);
            statement.setInt(10, 100);
            statement.setInt(11, 100);
            statement.setString(12, "");

            ResultSet data = statement.executeQuery();

            HashMap<Game, ArrayList<Server>> games = new HashMap<>();
            Game game;
            Server server;
            Double price;

            while(data.next()){
                game = new Game(data.getString("name"), null, data.getBoolean("haveMultiLanguages"));

                price = data.getDouble("price");
                if(!data.wasNull()){
                    game.setPrice(price);
                }

                java.sql.Date releaseDate = data.getDate("releaseDate");
                calendar = new GregorianCalendar();
                calendar.setTime(releaseDate);
                game.setReleaseDate(calendar);

              /*server = new Server();
                game.addServer();*/
            }

            return games;

        }catch (ConnectionException connectionException){
            throw new AllGamesException(0);
        } catch (SQLException sqlException) {
            throw new AllGamesException(0);
        }catch (UniqueNameException uniqueNameException){
            throw new AllGamesException(1);
        }
    }

}
