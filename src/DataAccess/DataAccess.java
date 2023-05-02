package DataAccess;

import Exceptions.*;


public interface DataAccess {
    void addProduct() throws addProductException;

    void editProduct() throws editProductException;

    void deleteProduct() throws deleteProductException;

    void searchProduct() throws searchProductException;

    void searchWhoBought();

    void searchWhoSupplied();

    void searchBoughtHistory();

    void showStatistics();
}
