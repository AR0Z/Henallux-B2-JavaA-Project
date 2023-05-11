package Model;

import java.time.LocalDate;
import java.util.Date;

public class Product {
    private int id;
    private String name;
    private String color;
    private double price;
    private double cost;
    private double size;
    private int stock;
    private LocalDate additionDate;
    private Boolean isShippable;
    private String description;
    private String imgLink;
    private Category category;
    private int category_FK;

    public Product(int id, String name, String color, double price, double cost, double size, int stock, LocalDate additionDate, Boolean isShippable, String description, String imgLink, Category category, int category_FK) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.cost = cost;
        this.size = size;
        this.stock = stock;
        this.additionDate = additionDate;
        this.isShippable = isShippable;
        this.description = description;
        this.imgLink = imgLink;
        this.category = category;
        this.category_FK = category_FK;
    }

    public Product() {
        this(-1, "", "", 0, 0, 0, 0, null, false, "", "", null, -1);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAdditionDate(LocalDate additionDate) {
        this.additionDate = additionDate;
    }

    public void setShippable(Boolean shippable) {
        isShippable = shippable;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", color='" + color + "'" +
                ", price=" + price +
                ", cost=" + cost +
                ", size=" + size +
                ", stock=" + stock +
                ", additionDate=" + additionDate +
                ", isShippable=" + isShippable +
                ", description='" + description + "'" +
                ", imgLink='" + imgLink + "'" +
                ", category=" + category +
                ", category_FK=" + category_FK +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public double getCost() {
        return cost;
    }

    public double getSize() {
        return size;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getAdditionDate() {
        return additionDate;
    }

    public Boolean getShippable() {
        return isShippable;
    }

    public String getDescription() {
        return description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public Category getCategory() {
        return category;
    }

    public int getCategory_FK() {
        return category_FK;
    }

    public int getId() {
        return this.id;
    }

    public void setCategory_FK(int categoryFk) {
        this.category_FK = categoryFk;
    }
}
