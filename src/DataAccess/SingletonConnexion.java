package DataAccess;

import Exceptions.*;
import java.sql.*;

public class SingletonConnexion {
    private static Connection instance;

    private SingletonConnexion() throws SQLException {
        try {
            instance = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atrouver", "root", "root");
        } catch (SQLException exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    public static Connection getInstance() throws DBExceptions {
        if (instance == null) {
            try {
                new SingletonConnexion();
            } catch (SQLException exception) {
                throw new DBExceptions(exception.getMessage());
            }
        }
        return instance;
    }
}
