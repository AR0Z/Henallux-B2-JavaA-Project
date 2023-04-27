package Model;

import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private String color;
    private double price;
    private double cost;
    private double size;
    private int stock;
    private Date additionDate;
    private boolean isShippable;
    private String description;
    private String imgLink;
    private Category category;

    public Product(int id, String name, String color, double price, double cost, double size, int stock, Date additionDate, String description, boolean isShippable, String imgLink) {
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
    }

    public Product(int id, String name, String color, double price, double cost, double size, int stock, Date additionDate, String description, boolean isShippable) { // no image link
        this(id, name, color, price, cost, size, stock, additionDate, description, isShippable, null);
    }

    public Product(int id, String name, String color, double price, double cost, double size, int stock, Date additionDate, boolean isShippable, String imgLink) { // no desc
        this(id, name, color, price, cost, size, stock, additionDate, null, isShippable, imgLink);
    }

    public Product(int id, String name, String color, double price, double cost, double size, int stock, Date additionDate, boolean isShippable) { // no image link nor desc
        this(id, name, color, price, cost, size, stock, additionDate, null, isShippable, null);
    }



}
