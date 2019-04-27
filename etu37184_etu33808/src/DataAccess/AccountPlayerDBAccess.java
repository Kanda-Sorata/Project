package DataAccess;
import BusinessLogic.AccountPlayerDataAccess;
import Exception.*;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AccountPlayerDBAccess implements AccountPlayerDataAccess {

    public AccountPlayerDBAccess(){}

    public int getNbAccountPlayers() throws NbAccountException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select count(*) from AccountPlayer;";
            ResultSet data;
            PreparedStatement statement = dataConnection.prepareStatement(querry);
            data = statement.executeQuery();
            return  data.getInt(1);
        }
        catch(SQLException sqlException){
            throw new NbAccountException();
        }catch (ConnectionException connexionException){
            throw new NbAccountException();
        }
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws AllAccountException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry;
            querry = "select * from AccountPlayer where colonne1 = ? and colonne2 = ? and colonne3 = ? and colonne4 =";
            querry += " ? and colonne5 = ? and colonne6 = ? and colonne7 = ?;";

            ArrayList<AccountPlayer> accountPlayers = new ArrayList<>();
            PreparedStatement statement = dataConnection.prepareStatement(querry);
            statement.setInt(1, 100);
            statement.setString(2, "");
            statement.setInt(3, 100);
            statement.setString(4, "");
            GregorianCalendar calendar = new GregorianCalendar();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            statement.setDate(5, sqlDate);
            statement.setString(6, "");
            statement.setString(7, "");

            ResultSet data = statement.executeQuery();
            AccountPlayer accountPlayer;
            String city;
            while (data.next()) {
                accountPlayer = new AccountPlayer(data.getInt("id"), data.getString("pseudo"),
                        data.getInt("number"), data.getString("sex"), null,
                        data.getString("country"));

                java.sql.Date creationDate = data.getDate("creationDate");
                calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                accountPlayer.setCreationDate(calendar);

                city = data.getString("city");
                if (!data.wasNull()) {
                    accountPlayer.setCity(city);
                }
                accountPlayers.add(accountPlayer);
            }
            return accountPlayers;
        } catch (SQLException sqlException) {
            throw new AllAccountException(0);
        } catch (NameException nameException) {
            throw new AllAccountException(1);
        } catch (SexException sexException) {
            throw new AllAccountException(2);
        }catch (ConnectionException connexionException){
            throw new AllAccountException(0);
        }
    }


}
