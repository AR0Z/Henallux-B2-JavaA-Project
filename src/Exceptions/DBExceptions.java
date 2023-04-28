package Exceptions;

public class DBExceptions extends Exception {
    private String message;

    public DBExceptions(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur lors d'une opération avec la base de données :\n   " + message;
    }
}
