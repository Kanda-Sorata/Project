package DataAccess;
import Exception.*;

import java.sql.Connection;

public class SingletonConnection {
    private static DataConnection uniqueDataConnection;

    public static Connection getInstance() throws ConnectionException{
        if(uniqueDataConnection == null){
            uniqueDataConnection = new DataConnection();
        }
        return uniqueDataConnection.getDataConnection();
    }
}
