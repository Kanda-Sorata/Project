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
        try {
            Connection dataConnection = SingletonConnection.getInstance();

            String querry = "select effect.label as labelEffect, spell.name as spellName, spell.cooldown, game.name as game ";
            querry += "from effect, spell, game, characterClass, bind, debuff ";
            querry += "where game.name = characterClass.Gamename and bind.characterClassTechnicalId = ";
            querry += "characterClass.technicalId and spell.technicalId = bind.spellTechnicalId ";
            querry += "and debuff.spellTechnicalId = spell.technicalid and debuff.effectLabel = effect.label ";
            querry += "and game.name = ? and characterClass.name = ?;";

            PreparedStatement statement = dataConnection.prepareStatement(querry);
            statement.setString(1, gameChoice);
            statement.setString(2, classChoice);

            ResultSet data = statement.executeQuery();
            ArrayList<SearchEffectList> searchEffectLists = new ArrayList<>();
            SearchEffectList searchEffectList;

            while(data.next()){
                searchEffectList = new SearchEffectList(data.getString("labelEffect"), data.getString("spellName"), data.getInt("cooldown"));
                searchEffectLists.add(searchEffectList);
            }

            return searchEffectLists;

        } catch(ConnectionException connectionException){
            throw new DataException(0);
        }catch(SQLException sqlException){
            throw new DataAccessException();
        }
    }
}
