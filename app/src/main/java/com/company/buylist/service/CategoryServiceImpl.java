package com.company.buylist.service;

import com.company.buylist.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() {
        return Category.listAll(Category.class);
    }

    @Override
    public Category getOrCreateAndGetCategory(String categoryName) {
        List<Category> categories = Category.find(Category.class, "name = ?", categoryName);
        if (categories.size() == 0) {
            Category category = new Category();
            category.setName(categoryName);
            Category.save(category);
            return category;
        }
        return categories.get(0);
    }
}
