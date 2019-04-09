package DataAccess;
import Exception.*;
import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AccountPlayerDBAccess {

    public static int getNbAccountPlayers() throws SelectException {
        Connection connection = SingletonConnection.getInstance();
        String request = "select count(*) from AccountPlayer;";
        PreparedStatement statement = connection.prepareStatement(request);

        try {
            ResultSet data = statement.executeQuery();
        } catch (SQLException sqlException) {
            throw new SelectException(request);
        }

        return data.getInt(0);
    }

    public static ArrayList<AccountPlayer> getAllAccountPlayer() throws SelectException, NameException, SexException{
        Connection connection = SingletonConnection.getInstance();
        String request = "select * from AccountPlayer where colone1 = ? and colonne2 = ? and colone3 = ? and colone4 =";
                request += " ? and colone5 = ? and colone6 = ? and colone7 = ?;";
        PreparedStatement statement = connection.prepareStatement(request);

        try {
            statement.setInt(1, 100);
            statement.setString(2, "");
            statement.setInt(3, 100);
            statement.setString(4, "");
            GregorianCalendar calendar = new GregorianCalendar();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            statement.setDate(5, sqlDate);
            statement.setString(6, "");
            statement.setString(7, "");
        }catch(SQLException sqlException){
            throw new SelectException(request);
        }

        ArrayList<AccountPlayer> accountPlayers = new ArrayList<>();

        try {
            ResultSet data = statement.executeQuery();
            AccountPlayer accountPlayer;
            String city;
            while(data.next()){
                accountPlayer = new AccountPlayer(data.getInt("id"), data.getString("pseudo"),
                        data.getInt("number"), data.getString("sex"), null,
                        data.getString("country"));

                java.sql.Date creationDate = data.getDate("creationDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                accountPlayer.setCreationDate(calendar);

                city = data.getString("city");
                if(!data.wasNull()){
                    accountPlayer.setCity(city);
                }
                accountPlayers.add(accountPlayer);
            }
        }
        catch (SQLException sqlException) {
            throw new SelectException(request);
        }
        return accountPlayers;
    }
}
