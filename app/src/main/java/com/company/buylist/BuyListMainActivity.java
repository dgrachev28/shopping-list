package com.company.buylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.company.buylist.component.ProductBlockView;
import com.company.buylist.model.Product;
import com.company.buylist.model.ProductList;
import com.company.buylist.presenter.ProductsListPresenter;
import com.company.buylist.presenter.ProductsListPresenterImpl;

import java.util.List;


public class BuyListMainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private ProductsListPresenter productsListPresenter;
    private AppCompatActivity self = this;
    private ProductCategoryAdapter productCategoryAdapter;

    private ProductList productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.buyListToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            Intent allListsIntent = new Intent(self, AllListsActivity.class);
            startActivity(allListsIntent);
        });

        initAddProductButton();
        productsListPresenter = new ProductsListPresenterImpl(this);
    }

    private void initAddProductButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buyListFab);
        fab.setOnClickListener(view -> {
            Intent addProductIntent = new Intent(self, AddProductActivity.class);
            startActivity(addProductIntent);
        });
    }

    public void updateProductList(List<List<Product>> productsGroupByCategories) {
        productCategoryAdapter.clear();
        productCategoryAdapter.addAll(productsGroupByCategories);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

        List<List<Product>> productsGroupByCategories = productsListPresenter.getProductsGroupByCategories();
        productCategoryAdapter = new ProductCategoryAdapter(this, R.id.categoryLinearLayout, productsGroupByCategories);

        ListView listView = (ListView) findViewById(R.id.productsListView);
        listView.setAdapter(productCategoryAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_buy_list_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings: {
                Intent addProductIntent = new Intent(this, SettingsActivity.class);
                startActivity(addProductIntent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class ProductCategoryAdapter extends ArrayAdapter<List<Product>> {

        private Context context;

        public ProductCategoryAdapter(Context context, int resource, List<List<Product>> objects) {
            super(context, resource, objects);
            this.context = context;
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
                ProductBlockView inflate = (ProductBlockView) LayoutInflater.from(context).inflate(R.layout.product_block, productsContainer, false);
                inflate.init(product, productsListPresenter);
                productsContainer.addView(inflate);
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
    }


    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onStop();
    }
}
