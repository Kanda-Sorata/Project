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
    public ArrayList<SearchSpellList> getSearchSpellList(String pseudoChoice, String numberChoice)throws DataException, DataAccessException{
        try {
            Connection connection = SingletonConnection.getInstance();

            String querry = "select `character`.name as characterName, spell.name, spell.cooldown ";
            querry += "from playeraccount, `character`, characterclass, bind, spell ";
            querry += "where playeraccount.id = (select id from playeraccount where pseudo = ? and `number` = ? ) ";
            querry += "and `character`.playeraccountid = playeraccount.id ";
            querry += "and `character`.characterclassTechnicalId = characterclass.technicalid ";
            querry += "and bind.characterclassTechnicalId = characterclass.technicalid ";
            querry += "and bind.spellTechnicalId = spell.technicalid ";
            querry += "order by (`character`.name);";

            PreparedStatement statement = connection.prepareStatement(querry);

            statement.setString(1, pseudoChoice);
            statement.setString(2, numberChoice);

            ResultSet data = statement.executeQuery();
            ArrayList<SearchSpellList> searchSpellLists = new ArrayList<>();
            SearchSpellList searchSpellList;
            Integer cooldown = null;

            while (data.next()){
                searchSpellList = new SearchSpellList(data.getString("name"), null,
                        data.getString("characterName"));

                cooldown = data.getInt("cooldown");
                if (!data.wasNull()) {
                    searchSpellList.setSpellCooldown(cooldown);
                }

                searchSpellLists.add(searchSpellList);
            }
            return searchSpellLists;
        }catch(ConnectionException connectionEwception){
            throw new DataException(0);
        }catch(SQLException sqlException){
            throw new DataAccessException();
        }
    }
}
