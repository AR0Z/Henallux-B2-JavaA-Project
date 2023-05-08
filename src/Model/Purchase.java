package Model;

public class Purchase {
    private int orderID;
    private int articleQuantity;
    private double articlePrice;
    private String articleName;
    private String categoryLabel;

    public Purchase(int orderID, int articleQuantity, double articlePrice, String articleName, String categoryLabel) {
        this.orderID = orderID;
        this.articleQuantity = articleQuantity;
        this.articlePrice = articlePrice;
        this.articleName = articleName;
        this.categoryLabel = categoryLabel;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getArticleQuantity() {
        return articleQuantity;
    }

    public double getArticlePrice() {
        return articlePrice;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }
}
