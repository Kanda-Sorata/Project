package DataAccess;

import BusinessLogic.EffectDataAccess;
import Exception.ConnectionException;
import Exception.DataAccessException;
import Exception.DataException;
import Model.SearchEffectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EffectDBAccess implements EffectDataAccess{
    public ArrayList<SearchEffectList> getSearchEffectList(String gameChoice, String classChoice) throws DataException, DataAccessException {
        Connection connection = null;
        try {
           connection = SingletonConnection.getInstance();

            String query = "select effect.label as labelEffect, spell.name as spellName, spell.cooldown, game.name as game "
                          + "from effect, spell, game, characterClass, bind, debuff "
                          + "where game.name = characterClass.Gamename and bind.characterClassTechnicalId = "
                          + "characterClass.technicalId and spell.technicalId = bind.spellTechnicalId "
                          + "and debuff.spellTechnicalId = spell.technicalid and debuff.effectLabel = effect.label "
                          + "and game.name = ? and characterClass.name = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameChoice);
            statement.setString(2, classChoice);

            ResultSet data = statement.executeQuery();
            ArrayList<SearchEffectList> searchEffectLists = new ArrayList<>();
            SearchEffectList searchEffectList;

            while(data.next()){
                searchEffectList = new SearchEffectList(data.getString("labelEffect"), data.getString("spellName"), (Integer)data.getObject("cooldown"));
                searchEffectLists.add(searchEffectList);
            }

            return searchEffectLists;

        } catch(ConnectionException connectionException){
            throw new DataAccessException(1);
        }catch(SQLException sqlException){
            throw new DataException(1);
        }
    }
}
