package com.company.buylist.service;

import com.company.buylist.model.Category;
import com.company.buylist.model.ProductDefinition;

import java.util.List;

public interface ProductDefinitionService {

    ProductDefinition saveProductDefinition(String productName, Category category);
    List<ProductDefinition> findAll();
    ProductDefinition findByProductName(String productName);

}
