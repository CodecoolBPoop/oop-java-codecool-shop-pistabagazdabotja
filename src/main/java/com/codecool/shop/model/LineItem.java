package com.codecool.shop.model;

public class LineItem {
    private Product product;
    private int amount;
    private int id;

    public LineItem(Product product) {
        this.product = product;
        this.amount = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() { return amount; }

    public void setAmount(int amount) {this.amount = amount; }

    public void increaseAmount() {
        amount++;
    }

    public void decreaseAmount() {
        if (amount > 1) amount--;
    }

    public Supplier getSupplier() {
        return product.getSupplier();
    }

    public ProductCategory getProductCategory() {
        return product.getProductCategory();
    }

    public String toString() {
        return product.toString() + ", amount: " + amount;
    }
}
