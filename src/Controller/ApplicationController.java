package Controller;

import Business.ApplicationManager;
import Exceptions.DBExceptions;
import Model.*;

import java.util.ArrayList;

public class ApplicationController {
    private ApplicationManager applicationManager;

    public ApplicationController() {
        applicationManager = new ApplicationManager();
    }

    public void addProduct(Product product) throws DBExceptions {
        applicationManager.addProduct(product);
    }

    public void editProduct(Product product) throws DBExceptions {
        applicationManager.editProduct(product);
    }

    public void deleteProduct(int id) throws DBExceptions {
        applicationManager.deleteProduct(id);
    }

    public Product getProductById(int id) throws DBExceptions {
        return applicationManager.getProductById(id);
    }

    public ArrayList<Product> getAllProducts() throws DBExceptions {
        return applicationManager.getAllProducts();
    }

    public Boolean isArticleAvailableForDeleting(int id) throws DBExceptions{
        return applicationManager.isArticleAvailableForDeleting(id);
    }

    public ArrayList<CustomerByProduct> getCustomersWhoPurchasedProduct(int id) throws DBExceptions {
        return applicationManager.getCustomersWhoPurchasedProduct(id);
    }

    public Category getCategoryById(int id) throws DBExceptions {
        return applicationManager.getCategoryById(id);
    }

    public ArrayList<Category> getAllCategories() throws DBExceptions {
        return applicationManager.getAllCategories();
    }

    public Customer getCustomerById(int id) throws DBExceptions {
        return applicationManager.getCustomerById(id);
    }

    public ArrayList<Customer> getAllCustomers() throws DBExceptions {
        return applicationManager.getAllCustomers();
    }

    public ArrayList<Purchase> getBoughtHistory(int id) throws DBExceptions {
        return applicationManager.getBoughtHistory(id);
    }

    public ArrayList<SupplierByCategory> getSuppliersByCategory(int id) throws DBExceptions {
        return applicationManager.getSuppliersByCategory(id);
    }
}
