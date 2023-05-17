package DataAccess;

import Exceptions.CloseConnectionException;
import Exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

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