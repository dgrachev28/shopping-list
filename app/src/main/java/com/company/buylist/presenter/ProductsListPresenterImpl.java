package com.company.buylist.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.company.buylist.BuyListMainActivity;
import com.company.buylist.model.Product;
import com.company.buylist.service.CategoryService;
import com.company.buylist.service.CategoryServiceImpl;
import com.company.buylist.service.ProductService;
import com.company.buylist.service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsListPresenterImpl implements ProductsListPresenter {

    //TODO заменить на интерфейс
    private BuyListMainActivity buyListMainActivity;

    private CategoryService categoryService;
    private ProductService productService;

    public ProductsListPresenterImpl(BuyListMainActivity buyListMainActivity) {
        this.buyListMainActivity = buyListMainActivity;
        categoryService = new CategoryServiceImpl();
        productService = new ProductServiceImpl();
    }

    public List<List<Product>> getProductsGroupByCategories() {
        List<Product> products = Product.find(Product.class, "state != ?", String.valueOf(Product.DELETED_STATE));
        Map<Integer, List<Product>> statusToProductsMap = splitBoughtProducts(products);

        List<List<Product>> categoryGroups = new ArrayList<>();
        if (statusToProductsMap.containsKey(Product.NEED_TO_BUY_STATE)) {
            List<Product> needToBuyProducts = Stream.of(statusToProductsMap.get(Product.NEED_TO_BUY_STATE)).map(product -> {
                if (product.getProductDefinition().getCategory() == null) {
                    product.getProductDefinition().setCategory(categoryService.getOrCreateAndGetCategory(""));
                }
                return product;
            }).collect(Collectors.toList());
            Map<String, List<Product>> categoryGroupsMap = Stream.of(needToBuyProducts)
                    .collect(Collectors.groupingBy(product -> product.getProductDefinition().getCategory().getName()));
            categoryGroups.addAll(Stream.of(categoryGroupsMap.entrySet())
                    .sortBy(Map.Entry::getKey)
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList()));
        }

        if (statusToProductsMap.containsKey(Product.BOUGHT_STATE)) {
            categoryGroups.add(statusToProductsMap.get(Product.BOUGHT_STATE));
        }
        return categoryGroups;
    }

    @Override
    public void onProductBlockClick(Product product) {
        productService.switchStateAndSave(product);
        buyListMainActivity.updateProductList(getProductsGroupByCategories());
    }


    private Map<Integer, List<Product>> splitBoughtProducts(List<Product> products) {
        return Stream.of(products).collect(Collectors.groupingBy(Product::getState));
    }

}
