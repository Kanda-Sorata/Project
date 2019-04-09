package DataAccess;

import javax.swing.*;
import java.sql.*;

public class Connection {
    private static Connection connection = null;

    public Connection(){
        /*try {
            connection = DriverManager.getConnection("", "","");
        }
        catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Error SQL Connection", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
        }*/
    }

}
