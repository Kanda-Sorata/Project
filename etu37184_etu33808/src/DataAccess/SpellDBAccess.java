package DataAccess;

import BusinessLogic.SpellDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchSpellList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpellDBAccess implements SpellDataAccess {
    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, int numberChoice)throws DataException, DataAccessException{
        Connection connection;
        try {
           connection = SingletonConnection.getInstance();

            String query = "select `character`.name as characterName, spell.name, spell.cooldown "
                        + "from playeraccount, `character`, characterclass, bind, spell "
                        + "where playeraccount.id = (select id from playeraccount where pseudo = ? and `number` = ? ) "
                        + "and `character`.playeraccountid = playeraccount.id "
                        + "and `character`.characterclassTechnicalId = characterclass.technicalid "
                        + "and bind.characterclassTechnicalId = characterclass.technicalid "
                        + "and bind.spellTechnicalId = spell.technicalid "
                        + "order by (`character`.name);";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, pseudoChoice);
            statement.setInt(2, numberChoice);

            ResultSet data = statement.executeQuery();
            ArrayList<SearchSpellList> searchSpellLists = new ArrayList<>();
            SearchSpellList searchSpellList;

            while (data.next()){
                searchSpellList = new SearchSpellList(data.getString("name"), (Integer) data.getObject("cooldown"),
                        data.getString("characterName"));
                searchSpellLists.add(searchSpellList);
            }
            return searchSpellLists;
        } catch (ConnectionException connectionException) {
            throw new DataAccessException(1);
        }catch(SQLException sqlException){
            throw new DataException(1);
        }
    }
}
