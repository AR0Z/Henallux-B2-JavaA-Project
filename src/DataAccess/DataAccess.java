package DataAccess;

import Exceptions.DBExceptions;

public interface DataAccess {
    void closeConnection() throws DBExceptions;
}
