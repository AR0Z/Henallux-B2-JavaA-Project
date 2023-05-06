package Business;

import DataAccess.*;
import Exceptions.*;
import Model.Product;

import java.util.ArrayList;

public class ProductManager {

    private ProductDBAccess dao;

    public ProductManager() {
        setDao(new ProductDBAccess());
    }


    public void setDao(ProductDBAccess dao) {
        this.dao = dao;
    }

    public void addProduct() {
        try {
            dao.addProduct();
        } catch (addProductException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProduct() {
        try {
            dao.editProduct();
        } catch (editProductException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct() {
        try {
            dao.deleteProduct();
        } catch (deleteProductException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchProduct() {
        try {
            dao.searchProduct();
        } catch (searchProductException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchWhoBought() {
        try {
            dao.searchWhoBought();
        } catch (searchWhoBoughtException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchWhoSupplied() {
        try {
            dao.searchWhoSupplied();
        } catch (searchWhoSuppliedException e) {
            throw new RuntimeException(e);
        }
    }

    public  void searchBoughtHistory() {
        try {
            dao.searchBoughtHistory();
        } catch (searchBoughtHistoryException e) {
            throw new RuntimeException(e);
        }
    }

    public  void showStatistics() {
        try {
            dao.showStatistics();
        } catch (showStatisticsException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Product> getAllProducts() throws getAllProductsException {
        return dao.getAllProducts();
    }
}
