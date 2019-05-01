package DataAccess;

import Exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws ConnectionException{
        if(uniqueConnection == null){
            try {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root","Rc648pPy");
            }
            catch(SQLException exception){
                System.out.println(exception.getMessage() + "\n" + exception.getSQLState() + "\n" + exception.getErrorCode());
                throw new ConnectionException(exception.getMessage(), exception.getSQLState(), exception.getErrorCode());
            }
        }
        return uniqueConnection;
    }

    public static boolean isClosed(){
        return uniqueConnection == null;
    }
}
