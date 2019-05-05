package Controller;
import Exception.DataAccessException;
import BusinessLogic.SingletonBusinessLogic;

public class SingletonController {
    private SingletonBusinessLogic singletonBusinessLogic;

    public SingletonController() {
        this.singletonBusinessLogic = new SingletonBusinessLogic();
    }

    public void close() throws DataAccessException {
        singletonBusinessLogic.close();
    }
}
