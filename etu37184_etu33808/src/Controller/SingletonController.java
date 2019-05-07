package Controller;

import BusinessLogic.SingletonBusinessLogic;
import Exception.DataAccessException;

public class SingletonController {
    private SingletonBusinessLogic singletonBusinessLogic;

    public SingletonController() {
        this.singletonBusinessLogic = new SingletonBusinessLogic();
    }

    public void close() throws DataAccessException {
        singletonBusinessLogic.close();
    }
}
