package Exceptions;

public class CloseConnectionException extends Exception {
    public CloseConnectionException() {
        super("Il est impossible de fermer la connexion");
    }
}
