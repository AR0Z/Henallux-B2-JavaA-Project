package Business;

import DataAccess.ProductDBAccess;

public class ProductManager {

    private ProductDBAccess dao;

    public ProductManager() {
        setDao(new ProductDBAccess());
    }


    public void setDao(ProductDBAccess dao) {
        this.dao = dao;
    }

    public void addProduct() {

    }

    public void editProduct() {

    }

    public void deleteProduct() {

    }

    public void searchProduct() {

    }

    public void searchWhoBought() {

    }

    public void searchWhoSupplied() {

    }

    public  void searchBoughtHistory() {

    }

    public  void showStatistics() {

    }
}
