package DataAccess;

import Exception.ConnectionException;

import java.sql.Connection;

public class SingletonConnection {
    private static Connection uniqueDataConnection;

    public static Connection getInstance() throws ConnectionException{
        if(uniqueDataConnection == null){
            uniqueDataConnection = new DataConnection().getDataConnection();
        }
        return uniqueDataConnection;
    }

    public static boolean isClosed(){
        return uniqueDataConnection == null;
    }
}
