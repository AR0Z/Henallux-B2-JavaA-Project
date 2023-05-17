package DataAccess;

import Exceptions.ConnectionException;
import Exceptions.DBExceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection instance;

    private SingletonConnexion() throws SQLException {
        try {
            instance = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/maisondumeuble", "root", "root");
        } catch (SQLException exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    public static Connection getInstance() throws ConnectionException {
        if (instance == null) {
            try {
                new SingletonConnexion();
            } catch (SQLException exception) {
                throw new ConnectionException();
            }
        }
        return instance;
    }
}
