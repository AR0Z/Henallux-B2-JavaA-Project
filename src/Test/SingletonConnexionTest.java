package Test;

import DataAccess.SingletonConnexion;
import Exceptions.DBExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonConnexionTest {

    @Test
    void getInstance() {
        assertDoesNotThrow(
                () -> SingletonConnexion.getInstance()
        );
    }
}