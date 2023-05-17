package Exceptions;

public class IsArticleAvailableForDeletingException extends Exception {
    public IsArticleAvailableForDeletingException() {
        super("Il est impossible de savoir si on peut supprimer l'article");
    }
}
