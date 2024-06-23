package org.example.Components;

public class Product {
    private int id;
    private int foreign_id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, int foreign_id, String name, double price) {
        this.id = id;
        this.foreign_id = foreign_id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(int foreign_id) {
        this.foreign_id = foreign_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", foreign_id=" + foreign_id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
