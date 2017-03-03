package com.company.buylist.service;

import com.annimon.stream.Stream;
import com.company.buylist.model.Product;
import com.company.buylist.model.ProductDefinition;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product switchStateAndSave(Product product) {
        switchState(product);
        Product.save(product);
        return product;
    }

    @Override
    public Product saveProduct(ProductDefinition pd, String quantity, String price) {
        Product product = new Product();
        product.setProductDefinition(pd);
        if (!quantity.equals("")) {
            product.setPrice(Double.valueOf(quantity));
        }
        if (!price.equals("")) {
            product.setQuantity(Integer.valueOf(price));
        } else {
            product.setQuantity(1);
        }
        Product.save(product);
        return product;
    }

    public void deleteAllProducts() {
        List<Product> products = Product.find(Product.class, "state = ?", String.valueOf(Product.BOUGHT_STATE));
        Stream.of(products).forEach(product -> {
            product.setState(Product.DELETED_STATE);
            Product.save(product);
        });
    }

    private void switchState(Product product) {
        if (product.getState() == Product.NEED_TO_BUY_STATE) {
            product.setState(Product.BOUGHT_STATE);
        } else if (product.getState() == Product.BOUGHT_STATE) {
            product.setState(Product.NEED_TO_BUY_STATE);
        }
    }

}
