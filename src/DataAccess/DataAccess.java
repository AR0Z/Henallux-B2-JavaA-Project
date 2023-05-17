package DataAccess;

import Exceptions.CloseConnectionException;

public interface DataAccess {
    void closeConnection() throws CloseConnectionException;
}
