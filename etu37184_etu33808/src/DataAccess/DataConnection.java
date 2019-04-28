package DataAccess;

import Exception.*;
import java.sql.*;

public class DataConnection {  
    private static Connection dataConnection = null;

    public DataConnection() throws ConnectionException{
        try {
            dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","Rc648pPy");
        }
        catch(SQLException exception){
            throw new ConnectionException();
        }
    }
    public Connection getDataConnection(){
        return  dataConnection;
    }

}
