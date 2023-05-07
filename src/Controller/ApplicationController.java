package Controller;

import Business.*;
import Exceptions.getAllCategoriesException;
import Exceptions.getAllCustomersException;
import Exceptions.getAllProductsException;
import Model.Category;
import Model.Customer;
import Model.Product;
import Model.SearchBoughtHistory;

import java.util.ArrayList;

public class ApplicationController {
    private ProductManager productManager;
    private ApplicationManager applicationManager;
    public ApplicationController(){
         productManager = new ProductManager();
         applicationManager = new ApplicationManager();
    }
    
    public void addProduct(Product product) {
        productManager.addProduct(product);
    }
    
    public void editProduct(Product product) {
        productManager.editProduct(product);
    }
    
    public void deleteProduct(Product product) {
        productManager.deleteProduct(product);
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
    
    public ArrayList<SearchBoughtHistory> searchBoughtHistory(Customer customer) {
        return productManager.searchBoughtHistory(customer);
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

    public ArrayList<Customer> getAllCustomers() {
        try {
            return applicationManager.getAllCustomers();
        } catch (getAllCustomersException e) {
            throw new RuntimeException(e);
        }
    }
}
