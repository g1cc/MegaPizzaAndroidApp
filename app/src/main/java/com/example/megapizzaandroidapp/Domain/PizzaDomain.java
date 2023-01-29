package com.example.megapizzaandroidapp.Domain;

import java.io.Serializable;

public class PizzaDomain implements Serializable
{
    private String title;
    private String image;
    private String ingredients;
    private String description;
    private int price;
    private int energy;
    private double fats;
    private double carbon;
    private double protein;
    private int weight;
    private int cartPosition;

    public PizzaDomain(String title, String image, String ingredients, String description, int price, int energy, double fats, double carbon, double protein, int weight) {
        this.title = title;
        this.image = image;
        this.ingredients = ingredients;
        this.description = description;
        this.price = price;
        this.energy = energy;
        this.fats = fats;
        this.carbon = carbon;
        this.protein = protein;
        this.weight = weight;
    }

    public PizzaDomain(String title, String image, String ingredients, String description, int price, int energy, double fats, double carbon, double protein, int weight, int cartPosition) {
        this.title = title;
        this.image = image;
        this.ingredients = ingredients;
        this.description = description;
        this.price = price;
        this.energy = energy;
        this.fats = fats;
        this.carbon = carbon;
        this.protein = protein;
        this.weight = weight;
        this.cartPosition = cartPosition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbon() {
        return carbon;
    }

    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCartPosition() {
        return cartPosition;
    }

    public void setCartPosition(int cartPosition) {
        this.cartPosition = cartPosition;
    }
}
