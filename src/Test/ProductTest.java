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
    void getPrice() {
        assertEquals(1.0, product.getPrice());
        assertNotEquals(2.0, product.getPrice());
    }

    @org.junit.jupiter.api.Test
    void getCost() {
        assertEquals(1.0, product.getCost());
        assertNotEquals(2.0, product.getCost());
    }

    @org.junit.jupiter.api.Test
    void getSize() {
        assertEquals(1.0, product.getSize());
        assertNotEquals(2.0, product.getSize());
    }

    @org.junit.jupiter.api.Test
    void getStock() {
        assertEquals(1, product.getStock());
        assertNotEquals(2, product.getStock());
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("name", product.getName());
        assertNotEquals("name2", product.getName());
    }

    @org.junit.jupiter.api.Test
    void getColor() {
        assertEquals("color", product.getColor());
        assertNotEquals("color2", product.getColor());
    }

    @org.junit.jupiter.api.Test
    void getDescription() {
        assertEquals("description", product.getDescription());
        assertNotEquals("description2", product.getDescription());
    }

    @org.junit.jupiter.api.Test
    void getImgLink() {
        assertEquals("imgLink", product.getImgLink());
        assertNotEquals("imgLink2", product.getImgLink());
    }

    @org.junit.jupiter.api.Test
    void isShippable() {
        assertEquals(true, product.getShippable());
        assertNotEquals(false, product.getShippable());
    }
}