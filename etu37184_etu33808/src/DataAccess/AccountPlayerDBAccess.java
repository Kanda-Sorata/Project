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
        Connection connection = null;
        try {
            connection = SingletonConnection.getInstance();
            String query = "select count(*) from playeraccount;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            Integer nbPlayerAccount = null;

            if(data.next()) {
                nbPlayerAccount = data.getInt(1);
            }
            return nbPlayerAccount;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        }
    }

    public ArrayList<AccountPlayer> getAllAccountPlayer() throws DataException, DataAccessException {
        Connection connection = null;
        try {
            connection = SingletonConnection.getInstance();
            String query = "select * from playeraccount";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet data = statement.executeQuery();
            ArrayList<AccountPlayer> accountPlayers = new ArrayList<>();
            AccountPlayer accountPlayer;

            while (data.next()) {
                accountPlayer = new AccountPlayer((Integer)data.getObject("id"), data.getString("pseudo"),
                        (Integer)data.getObject("number"), data.getString("sex"), null,
                        data.getString("city"), data.getString("country"));

                GregorianCalendar calendar = new GregorianCalendar();
                java.sql.Date creationDate = data.getDate("creationDate");
                calendar.setTime(creationDate);
                accountPlayer.setCreationDate(calendar);

                accountPlayers.add(accountPlayer);
            }
            return accountPlayers;
        } catch (ConnectionException connexionException){
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        } catch (SexException sexException) {
            throw new DataException(4);
        }
    }
}