package org.example.models;

import org.example.Components.DocumentType;
import org.example.Components.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order extends Document{
    private int id;
    private int number;
    private Date date;
    private DocumentType documentType;
    private List<Product> products;

    public Order() {
        this.documentType = DocumentType.ORDER;
        products = new ArrayList<>();
    }

    public Order(int id, int number, Date date, List<Product> products) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.products = products;
        this.documentType = DocumentType.ORDER;
    }

    @Override
    public List<String> info() {
        return List.of(
                String.valueOf(id),
                String.valueOf(number),
                String.valueOf(date),
                String.valueOf(products.get(0).toString())
        );
    }

    @Override
    public void setData(String[] data) {
        setId(Integer.parseInt(data[0].split("=")[1]));
        setNumber(Integer.parseInt(data[1].split("=")[1]));
        parseAndSetDate(data[2]);
        parseProducts(data[4]+ ", " +data[5]+ ", " +data[6] + ", " +data[7]);
    }

    private void parseProducts(String value) {
        Pattern pattern = Pattern.compile("Product\\{(.*?)}");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            String productString = matcher.group(1);
            Product product = new Product();
            String[] productParts = productString.split(", ");
            for (String productPart : productParts) {
                String[] parts = productPart.split("=");
                System.out.println(Arrays.toString(parts));
                String key = parts[0].trim();
                String val = parts[1].trim();
                switch (key) {
                    case "id" -> product.setId(Integer.parseInt(val));
                    case "foreign_id" -> product.setForeign_id(Integer.parseInt(val));
                    case "name" -> product.setName(val);
                    case "price" -> product.setPrice(Double.parseDouble(val));
                }
            }
            addProduct(product);
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public DocumentType getDocumentType() {
        return documentType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", documentType='" + documentType + '\'' +
                ", products=" + products +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    private void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
