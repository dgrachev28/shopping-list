package com.company.buylist.model;


import com.orm.SugarRecord;

import java.util.List;

public class Category extends SugarRecord {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDefinition> getProductDefinitions() {
        return ProductDefinition.find(ProductDefinition.class, "category = ?", getId().toString());
    }
}

