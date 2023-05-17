package Exceptions;

public class ConnectionException extends Exception {
    public ConnectionException() {
        super("Il est impossible de se connecter à la base de données");
    }
}
