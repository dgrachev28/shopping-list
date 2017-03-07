package com.company.buylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


public class AllListsActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.allListsToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

//        initAddProductButton();
    }

//    private void initAddProductButton() {
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(view -> {
//            Intent addProductIntent = new Intent(self, AddProductActivity.class);
//            startActivity(addProductIntent);
//        });
//    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

//        List<List<Product>> productsGroupByCategories = productsListPresenter.getProductsGroupByCategories();

//        ListView listView = (ListView) findViewById(R.id.productsListView);
//        listView.setAdapter(productCategoryAdapter);
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
