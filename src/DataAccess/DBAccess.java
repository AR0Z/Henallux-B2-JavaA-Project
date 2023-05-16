package DataAccess;

import Exceptions.DBExceptions;

import java.sql.*;

public class DBAccess implements DataAccess {
    private Connection connection;

    public DBAccess() {
        try{
            connection = SingletonConnexion.getInstance();
        }catch (DBExceptions exceptions) {
            exceptions.printStackTrace();
        }

    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException exceptions) {
            exceptions.printStackTrace();
        }
    }
}