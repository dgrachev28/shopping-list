package com.company.buylist.service;

import com.company.buylist.model.Product;
import com.company.buylist.model.ProductDefinition;

public interface ProductService {

    Product switchStateAndSave(Product product);
    Product saveProduct(ProductDefinition pd, String quantity, String price);
    void deleteAllProducts();
}
