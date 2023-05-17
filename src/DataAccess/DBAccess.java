package DataAccess;

import Exceptions.CloseConnectionException;
import Exceptions.ConnectionException;
import Exceptions.DBExceptions;

import java.sql.*;

public class DBAccess implements DataAccess {
    private Connection connection;

    public DBAccess() throws ConnectionException {
            connection = SingletonConnexion.getInstance();
    }

    public void closeConnection() throws CloseConnectionException {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException exceptions) {
            throw new CloseConnectionException();
        }
    }
}