package com.company.buylist.model;


import com.orm.SugarRecord;

public class Product extends SugarRecord {

    public static final int NEED_TO_BUY_STATE = 0;
    public static final int BOUGHT_STATE = 1;
    public static final int DELETED_STATE = 2;

    private ProductDefinition productDefinition;
    private ProductList productList;
    private int quantity;
    private String quantityUnit;
    private Double price;
    private String priceUnit;
    private int state; // 0 - need to buy, 1 - bought, 2 - deleted


    public ProductDefinition getProductDefinition() {
        return productDefinition;
    }

    public void setProductDefinition(ProductDefinition productDefinition) {
        this.productDefinition = productDefinition;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
