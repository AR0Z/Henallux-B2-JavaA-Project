package Test;

import Model.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product;
    @org.junit.jupiter.api.BeforeEach
    void setup() {
        product = new Product(1, "name", "color", 1.0, 1.0, 1.0, 1, true, "description", "imgLink", null, 1);
    }
    @org.junit.jupiter.api.Test
    void setPrice() {
        product.setPrice(-10);
        assertNotEquals(-10, product.getPrice());
        assertEquals(1, product.getPrice());
    }

    @org.junit.jupiter.api.Test
    void setCost() {
        product.setCost(-8);
        assertNotEquals(-8, product.getCost());
        assertEquals(1, product.getCost());
    }

    @org.junit.jupiter.api.Test
    void setSize() {
        product.setSize(-3);
        assertNotEquals(-3, product.getSize());
        assertEquals(1, product.getSize());
    }

    @org.junit.jupiter.api.Test
    void setStock() {
        product.setStock(-6);
        assertNotEquals(-6, product.getStock());
        assertEquals(1, product.getStock());
    }
}