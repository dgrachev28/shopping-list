package com.company.buylist.model;


import com.orm.SugarRecord;

import java.util.List;

public class ProductList extends SugarRecord {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return Product.find(Product.class, "productList = ?", getId().toString());
    }
}

