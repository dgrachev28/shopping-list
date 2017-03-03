package com.company.buylist.presenter;

import com.company.buylist.model.Product;

import java.util.List;

public interface ProductsListPresenter {

    List<List<Product>> getProductsGroupByCategories();
}
