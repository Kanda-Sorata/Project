package BusinessLogic;

import DataAccess.SingletonConnection;
import Exception.DataAccessException;

public class SingletonBusinessLogic {
    private SingletonConnection singletonConnection;

    public SingletonBusinessLogic(){
        singletonConnection = new SingletonConnection();
    }
    public void close() throws DataAccessException {
        singletonConnection.close();
    }
}
