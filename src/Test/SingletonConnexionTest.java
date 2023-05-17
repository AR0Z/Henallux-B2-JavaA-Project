package Test;

import DataAccess.SingletonConnexion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SingletonConnexionTest {

    @Test
    void getInstance() {
        assertDoesNotThrow(
                () -> SingletonConnexion.getInstance()
        );
    }
}