package com.company.buylist.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.company.buylist.R;
import com.company.buylist.model.Product;
import com.company.buylist.presenter.ProductsListPresenter;
import com.company.buylist.presenter.ProductsListPresenterImpl;

import java.util.List;


//TODO: refactor
public class ProductCategoryAdapter extends ArrayAdapter<List<Product>> {

    private Context context;

    private ProductsListPresenter productsListPresenter;

    private Drawable originalBackground  = null;

    public ProductCategoryAdapter(Context context, int resource, List<List<Product>> objects) {
        super(context, resource, objects);
        this.context = context;
        productsListPresenter = new ProductsListPresenterImpl();
    }


    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.category_products, parent, false);
        } else {
            view = convertView;
        }
        List<Product> products = getItem(position);

        String categoryName = getCategoryName(products);
        ((TextView) view.findViewById(R.id.categoryName)).setText(categoryName);

        LinearLayout productsContainer = (LinearLayout) view.findViewById(R.id.productsLinearLayout);

        productsContainer.removeAllViews();
        Stream.of(products).forEach(product -> {
            LinearLayout productBlock = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.product_block, null);
            TextView productNameTextView = (TextView) productBlock.findViewById(R.id.productName);
            TextView quantityTextView = (TextView) productBlock.findViewById(R.id.productQuantity);
            TextView priceTextView = (TextView) productBlock.findViewById(R.id.productPrice);

            productNameTextView.setText(product.getProductDefinition().getName());
            quantityTextView.setText(Integer.toString(product.getQuantity()));
            if (product.getPrice() != null) {
                priceTextView.setText(product.getPrice().toString());
            }
            productsContainer.addView(productBlock);

            LinearLayout productInfo = (LinearLayout) productBlock.findViewById(R.id.productInfoLinearLayout);
            productInfo.setTag(product);
            toggleProductInfoByState(productInfo, product.getState());

            productInfo.setOnClickListener(v -> {
                Product clickedProduct = (Product) v.getTag();
                switchState(clickedProduct);
                Product.save(clickedProduct);
                clear();
                addAll(productsListPresenter.getProductsGroupByCategories());
            });
        });

        return view;
    }

    private String getCategoryName(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }
        Product firstProduct = products.get(0);
        if (firstProduct.getState() == Product.BOUGHT_STATE) {
            return "Куплено";
        } else {
            return firstProduct.getProductDefinition().getCategory().getName();
        }
    }


    private void toggleProductInfoByState(LinearLayout productInfo, int state) {
        if (originalBackground != null) {
            originalBackground = productInfo.getBackground();
        }
        if (state == Product.BOUGHT_STATE) {
            productInfo.setBackgroundColor(Color.GRAY);
        } else {
            productInfo.setBackground(originalBackground);
        }
    }


    //TODO: refactor magic constants
    private void switchState(Product product) {
        if (product.getState() == 0) {
            product.setState(1);
        } else if (product.getState() == 1) {
            product.setState(0);
        }
    }
}

