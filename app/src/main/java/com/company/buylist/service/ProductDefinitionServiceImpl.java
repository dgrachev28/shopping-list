package com.company.buylist.service;


import com.company.buylist.model.Category;
import com.company.buylist.model.ProductDefinition;

import java.util.List;

public class ProductDefinitionServiceImpl implements ProductDefinitionService {

    @Override
    public ProductDefinition saveProductDefinition(String productName, Category category) {
        ProductDefinition pd = new ProductDefinition();
        pd.setName(productName);
        pd.setCategory(category);
        List<ProductDefinition> productDefinitions = ProductDefinition.find(ProductDefinition.class, "name = ?", productName);
        if (productDefinitions.size() != 0) {
            return productDefinitions.get(0);
        }
        ProductDefinition.save(pd);
        return pd;
    }

    @Override
    public List<ProductDefinition> findAll() {
        return ProductDefinition.listAll(ProductDefinition.class);
    }

    @Override
    public ProductDefinition findByProductName(String productName) {
        return ProductDefinition.find(ProductDefinition.class, "name = ?", productName).get(0);
    }

}
