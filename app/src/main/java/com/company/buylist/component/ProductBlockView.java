package com.company.buylist.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.company.buylist.R;
import com.company.buylist.model.Product;
import com.company.buylist.presenter.ProductsListPresenter;

public class ProductBlockView extends LinearLayout {

    private ProductsListPresenter productsListPresenter;

    private Drawable originalBackground  = null;

    public ProductBlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Product product, ProductsListPresenter productsListPresenter) {
        this.productsListPresenter = productsListPresenter;

        TextView productNameTextView = (TextView) findViewById(R.id.productName);
        TextView quantityTextView = (TextView) findViewById(R.id.productQuantity);
        TextView priceTextView = (TextView) findViewById(R.id.productPrice);

        productNameTextView.setText(product.getProductDefinition().getName());
        quantityTextView.setText(Integer.toString(product.getQuantity()));
        if (product.getPrice() != null) {
            priceTextView.setText(product.getPrice().toString());
        }

        setTag(product);
        toggleProductInfoByState(product.getState());
        setOnClickListener(v -> {
            this.productsListPresenter.onProductBlockClick((Product) v.getTag());
        });
    }

    private void toggleProductInfoByState(int state) {
        if (originalBackground != null) {
            originalBackground = getBackground();
        }
        if (state == Product.BOUGHT_STATE) {
            setBackgroundColor(Color.GRAY);
        } else {
            setBackground(originalBackground);
        }
    }
}
