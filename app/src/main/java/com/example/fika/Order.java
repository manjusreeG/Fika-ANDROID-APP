package com.example.fika;

public class Order {
    private String ProductId;
    private  String ProductName;
    private String ProductQty;
    private String Price;

    public Order(String productId, String productName, String productQty, String price) {
        ProductId = productId;
        ProductName = productName;
        ProductQty = productQty;
        Price = price;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductQty() {
        return ProductQty;
    }

    public void setProductQty(String productQty) {
        ProductQty = productQty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
