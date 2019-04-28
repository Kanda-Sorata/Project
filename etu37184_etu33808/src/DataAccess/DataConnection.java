package DataAccess;

import Exception.*;
import java.sql.*;

public class DataConnection {  
    private static Connection dataConnection = null;

    public DataConnection() throws ConnectionException{
        try {
            dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root","Rc648pPy");
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage() + "\n" + exception.getSQLState() + "\n" + exception.getErrorCode());
            throw new ConnectionException(exception.getMessage(), exception.getSQLState(), exception.getErrorCode());
        }
    }
    public Connection getDataConnection(){
        return  dataConnection;
    }

}
