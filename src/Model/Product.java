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

    public Product(int id, String name, String color, double price, double cost, double size, int stock, boolean isShippable, String description, String imgLink, Category category, int category_FK) {
        this(id, name, color, price, cost, size, stock, null, isShippable, description, imgLink, category, category_FK);
    }


    public Product(String name, String color, double price, double cost, double size, int stock, boolean isShippable, String description, String imgLink, Category category, int category_FK) {
        this(-1, name, color, price, cost, size, stock, null, isShippable, description, imgLink, category, category_FK);
    }

    public Product(int id, String name, String color, double price, double cost, double size, int stock, LocalDate additionDate, boolean isShippable, String description, String imgLink, int category_FK) {
        this(id, name, color, price, cost, size, stock, additionDate, isShippable, description, imgLink, null, category_FK);
    }

    public void setCategory(Category category) {
        this.category = category;
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
