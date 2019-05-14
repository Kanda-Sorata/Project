package DataAccess;

import BusinessLogic.PlayerAccountDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;
import Exception.SexException;
import Model.PlayerAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PlayerAccountDBAccess implements PlayerAccountDataAccess {
    public int getNbAccountPlayers() throws DataException, DataAccessException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();

            String query = "select count(*) from playeraccount;";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();

            int nbPlayerAccount = 0;

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

    public ArrayList<PlayerAccount> getAllAccountPlayer() throws DataException, DataAccessException {
        Connection connection;
        try {
            connection = SingletonConnection.getInstance();
            String query = "select * from playeraccount";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet data = statement.executeQuery();
            ArrayList<PlayerAccount> playerAccounts = new ArrayList<>();
            PlayerAccount playerAccount;

            while (data.next()) {
                playerAccount = new PlayerAccount((Integer) data.getObject("id"), data.getString("pseudo"),
                        (Integer)data.getObject("number"), data.getString("sex"), null,
                        data.getString("city"), data.getString("country"));

                GregorianCalendar calendar = new GregorianCalendar();
                java.sql.Date creationDate = data.getDate("creationDate");
                calendar.setTime(creationDate);
                playerAccount.setCreationDate(calendar);

                playerAccounts.add(playerAccount);
            }

            return playerAccounts;
        } catch (ConnectionException connexionException){
            throw new DataAccessException(1);
        } catch (SQLException sqlException) {
            throw new DataException(1);
        } catch (SexException sexException) {
            throw new DataException(4);
        }
    }
}