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

    public ArrayList<Character> getAllCharacter(String pseudo, String number) throws AllCharacterException{
        try{
            Connection dataConnection = SingletonConnection.getInstance();
            String querry = "select * from Character where colonne1 = ? and colonne 2 = ? and colonne3 = ? and colonne4 = ?";
            querry += " and colonne5 = ? and colonne6 = ? and  pseudo = " + pseudo + " and number = " + number + ";";

            PreparedStatement statement = dataConnection.prepareStatement(querry);

            statement.setString(1, "");
            statement.setInt(2, 100);
            statement.setBoolean(3, false);
            GregorianCalendar calendar = new GregorianCalendar();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            statement.setDate(4, sqlDate);
            statement.setString(5, "");
            statement.setInt(6, 100);

            ResultSet data = statement.executeQuery();
            Character character;
            ArrayList<Character> characters = new ArrayList<>();
            String petName;
            Integer damagePerSecond;

            while (data.next()) {
                character = new Character(data.getString("name"), data.getInt("healthPoint"),
                        data.getBoolean("isStuffed"),null, null, null,
                        null, null);

                java.sql.Date creationDate = data.getDate("creationDate");
                calendar = new GregorianCalendar();
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
        }catch (SQLException sqlException){
            throw new AllCharacterException(0);
        }catch(NameException nameException){
            throw new AllCharacterException(1);
        }catch(HealthPointsException healthPointsException){
            throw new AllCharacterException(2);
        }catch(DamagePerSecondException damagePerSecondException){
            throw new AllCharacterException(3);
        }catch (ConnectionException connexionException){
            throw new AllCharacterException(0);
        }
    }
}
