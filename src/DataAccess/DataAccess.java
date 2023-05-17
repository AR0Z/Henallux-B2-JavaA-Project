package DataAccess;

import Exceptions.CloseConnectionException;
import Exceptions.DBExceptions;

public interface DataAccess {
    void closeConnection() throws CloseConnectionException;
}
