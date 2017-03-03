package com.company.buylist.service;

import com.company.buylist.model.Category;
import com.company.buylist.model.Product;
import com.company.buylist.model.ProductDefinition;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category getOrCreateAndGetCategory(String categoryName);
}
