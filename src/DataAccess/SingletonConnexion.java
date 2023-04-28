package DataAccess;

import Exceptions.DBExceptions;

import java.sql.*;

public class SingletonConnexion {
    private static Connection instance;

    private SingletonConnexion() throws SQLException {
        instance = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/java_project",
                "root",
                "root"
        );
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
