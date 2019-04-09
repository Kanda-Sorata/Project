package DataAccess;

import Exception.*;
import java.sql.*;

public class DataConnection {
    private static Connection dataConnection = null;

    public DataConnection() throws ConnectionException{
        try {
            dataConnection = DriverManager.getConnection("JDBC", "root","1234");
        }
        catch(SQLException exception){
            throw new ConnectionException();
        }
    }
    public Connection getDataConnection(){
        return  dataConnection;
    }

}
