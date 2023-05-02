package Controller;

import Business.ProductManager;

public class ApplicationController {

    private ProductManager productManager;

    public ApplicationController(){
         productManager = new ProductManager();
    }
    
    public  void addProduct() {
        productManager.addProduct();
    }
    
    public  void editProduct() {
        productManager.editProduct();
    }
    
    public  void deleteProduct() {
        productManager.deleteProduct();
    }
    
    public  void searchProduct() {
        productManager.searchProduct();
    }
    
    public  void searchWhoBought() {
        productManager.searchWhoBought();
    }
    
    public  void searchWhoSupplied() {
        productManager.searchWhoSupplied();
    }
    
    public  void searchBoughtHistory() {
        productManager.searchBoughtHistory();
    }
    
    public  void showStatistics() {
        productManager.showStatistics();
    }
    
    


}
