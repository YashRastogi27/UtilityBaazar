package com.example.utkarsh.utilitybaazar;

/**
 * Created by Yash on 22-11-2017.
 */

public class Product {
    private String title, image;
    private int price, id;



    private double rating;

    public Product(int id, String title, int price, String image, double rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}
