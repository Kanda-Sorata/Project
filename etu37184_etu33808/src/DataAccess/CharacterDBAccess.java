package DataAccess;

import BusinessLogic.CharacterDataAccess;
import Exception.*;
import Model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class CharacterDBAccess implements CharacterDataAccess {

    public CharacterDBAccess(){}

    public ArrayList<Character> getAllCharacter(String pseudo, int number) throws DataException, DataAccessException {
        try {
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select name, healthPoint, isStuffed, creationDate, petName, damagePerSecond ";
            querry += "from `character` where `character`.`playeraccountId` = (select id from `playeraccount` ";
            querry += "where  pseudo = ? and number = ?);";

            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, pseudo);
            statement.setInt(2, number);

            ResultSet data = statement.executeQuery();
            Character character;
            ArrayList<Character> characters = new ArrayList<>();
            String petName;
            Integer damagePerSecond;

            while (data.next()) {
                character = new Character(data.getString("name"), data.getInt("healthPoint"),
                        data.getBoolean("isStuffed"), null, null, null,
                        null, null);

                java.sql.Date creationDate = data.getDate("creationDate");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                character.setCreationDate(calendar);

                petName = data.getString("petName");
                if (!data.wasNull()) {
                    character.setPetName(petName);
                }

                damagePerSecond = data.getInt("damagePerSecond");
                if (!data.wasNull()) {
                    character.setDamagePerSecond(damagePerSecond);
                }
                characters.add(character);
            }
            return characters;
        } catch (ConnectionException connexionException) {
            throw new DataAccessException();
        } catch (SQLException sqlException) {
            throw new DataException(0);
        } catch (HealthPointsException healthPointsException) {
            throw new DataException(2);
        } catch (DamagePerSecondException damagePerSecondException) {
            throw new DataException(6);
        }
    }
}
