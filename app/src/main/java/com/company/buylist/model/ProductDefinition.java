package com.company.buylist.model;


import com.orm.SugarRecord;

import java.util.List;

public class ProductDefinition extends SugarRecord {

    private String name;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return Product.find(Product.class, "productDefinition = ?", getId().toString());
    }
}
