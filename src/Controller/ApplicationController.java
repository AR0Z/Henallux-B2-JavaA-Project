package Controller;

import Business.*;
import Exceptions.getAllCategoriesException;
import Exceptions.getAllProductsException;
import Model.Category;
import Model.Product;

import java.util.ArrayList;

public class ApplicationController {
    private ProductManager productManager;

    public ApplicationController(){
         productManager = new ProductManager();
    }
    
    public void addProduct(Product product) {
        productManager.addProduct(product);
    }
    
    public void editProduct() {
        productManager.editProduct();
    }
    
    public void deleteProduct() {
        productManager.deleteProduct();
    }
    
    public void searchProduct() {
        productManager.searchProduct();
    }
    
    public void searchWhoBought() {
        productManager.searchWhoBought();
    }
    
    public void searchWhoSupplied() {
        productManager.searchWhoSupplied();
    }
    
    public void searchBoughtHistory() {
        productManager.searchBoughtHistory();
    }
    
    public void showStatistics() {
        productManager.showStatistics();
    }

    public ArrayList<Product> getAllProducts() {
        try {
            return productManager.getAllProducts();
        } catch (getAllProductsException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Category> getAllCategories() {
        try {
            return productManager.getAllCategories();
        } catch (getAllCategoriesException e) {
            throw new RuntimeException(e);
        }
    }
}
