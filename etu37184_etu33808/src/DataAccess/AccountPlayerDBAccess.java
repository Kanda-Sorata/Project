package DataAccess;

import BusinessLogic.AccountPlayerDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.SexException;
import Model.AccountPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AccountPlayerDBAccess implements AccountPlayerDataAccess {

    public AccountPlayerDBAccess(){}

    public Integer getNbAccountPlayers() throws DataException, DataAccessException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select count(*) from playeraccount;";
            PreparedStatement statement = dataConnection.prepareStatement(querry);
            ResultSet data = statement.executeQuery();
            Integer nbPlayerAccount = null;
            if(data.next()) {
                nbPlayerAccount = data.getInt(1);
            }
            return nbPlayerAccount;
        } catch (ConnectionException connexionException) {
            throw new DataException(0);
        } catch (SQLException sqlException) {
            throw new DataAccessException();
        }
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws DataException, DataAccessException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry;
            querry = "select * from playeraccount";

            ArrayList<AccountPlayer> accountPlayers = new ArrayList<>();
            PreparedStatement statement = dataConnection.prepareStatement(querry);

            ResultSet data = statement.executeQuery();
            AccountPlayer accountPlayer;
            String city;
            String sex;

            while (data.next()) {
                accountPlayer = new AccountPlayer(data.getInt("id"), data.getString("pseudo"),
                        data.getInt("number"), data.getString("sex"), null,
                        data.getString("country"));

                GregorianCalendar calendar = new GregorianCalendar();
                java.sql.Date creationDate = data.getDate("creationDate");
                calendar.setTime(creationDate);
                accountPlayer.setCreationDate(calendar);

                city = data.getString("city");
                if (!data.wasNull()) {
                    accountPlayer.setCity(city);
                }
                accountPlayers.add(accountPlayer);
            }
            return accountPlayers;
        } catch (ConnectionException connexionException){
            throw new DataException(0);
        } catch (SQLException sqlException) {
            throw new DataAccessException();
        } catch (SexException sexException) {
            throw new DataException(4);
        }
    }


}
