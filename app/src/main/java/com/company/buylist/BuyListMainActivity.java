package com.company.buylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.company.buylist.adapter.ProductCategoryAdapter;
import com.company.buylist.model.Product;
import com.company.buylist.presenter.ProductsListPresenter;
import com.company.buylist.presenter.ProductsListPresenterImpl;

import java.util.List;
import java.util.Set;


public class BuyListMainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private ProductsListPresenter productsListPresenter;
    private AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.buyListToolbar);
        setSupportActionBar(toolbar);

        initAddProductButton();
        productsListPresenter = new ProductsListPresenterImpl();
    }

    private void initAddProductButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent addProductIntent = new Intent(self, AddProductActivity.class);
            startActivity(addProductIntent);
        });
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

        List<List<Product>> productsGroupByCategories = productsListPresenter.getProductsGroupByCategories();
        ProductCategoryAdapter productCategoryAdapter = new ProductCategoryAdapter(this, R.id.categoryLinearLayout, productsGroupByCategories);

        ListView listView = (ListView) findViewById(R.id.productsListView);
        listView.setAdapter(productCategoryAdapter);
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
}
