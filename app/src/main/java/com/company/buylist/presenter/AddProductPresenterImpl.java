package com.company.buylist.presenter;

import com.company.buylist.AddProductActivity;
import com.company.buylist.model.Category;
import com.company.buylist.model.ProductDefinition;
import com.company.buylist.service.CategoryService;
import com.company.buylist.service.CategoryServiceImpl;
import com.company.buylist.service.ProductDefinitionService;
import com.company.buylist.service.ProductDefinitionServiceImpl;
import com.company.buylist.service.ProductService;
import com.company.buylist.service.ProductServiceImpl;

public class AddProductPresenterImpl implements AddProductPresenter {

    //TODO заменить на интерфейс
    private AddProductActivity addProductActivity;

    private ProductDefinitionService productDefinitionService;
    private ProductService productService;
    private CategoryService categoryService;

    public AddProductPresenterImpl(AddProductActivity addProductActivity) {
        this.addProductActivity = addProductActivity;
        productDefinitionService = new ProductDefinitionServiceImpl();
        productService = new ProductServiceImpl();
        categoryService = new CategoryServiceImpl();
    }

    @Override
    public void onAddProductButtonClick(String productName, String categoryName, String quantity, String price) {
        Category category = categoryService.getOrCreateAndGetCategory(categoryName);
        ProductDefinition pd = productDefinitionService.saveProductDefinition(productName, category);
        productService.saveProduct(pd, quantity, price);
        addProductActivity.showMessage("Product \" " + pd.getName() + "\" was created");
    }
}
