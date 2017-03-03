package com.company.buylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.company.buylist.filter.InputFilterMinMax;
import com.company.buylist.model.Category;
import com.company.buylist.model.ProductDefinition;
import com.company.buylist.presenter.AddProductPresenter;
import com.company.buylist.presenter.AddProductPresenterImpl;

public class AddProductActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private AddProductPresenter addProductPresenter;

    private AutoCompleteTextView productNameAutoComplete;
    private AutoCompleteTextView categoryNameAutoComplete;
    private EditText priceEditText;
    private EditText quantityEditText;
    private Button addProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.addProductToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        initComponents();

        addProductPresenter = new AddProductPresenterImpl(this);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initComponents() {
        initProductNameAutoComplete();
        initCategoryNameAutoComplete();
        initQuantityAndPriceEditTexts();
        initAddProductButton();
    }


    private void initProductNameAutoComplete() {
        productNameAutoComplete = (AutoCompleteTextView) findViewById(R.id.productNameAutoComplete);
        ArrayAdapter<String> productDefinitions = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                Stream.of(ProductDefinition.listAll(ProductDefinition.class)).map(ProductDefinition::getName).collect(Collectors.toList()));
        productNameAutoComplete.setAdapter(productDefinitions);
        productNameAutoComplete.setThreshold(1);
        productNameAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            String categoryName = ProductDefinition.find(ProductDefinition.class, "name = ?", productNameAutoComplete.getText().toString()).get(0).getCategory().getName();
            categoryNameAutoComplete.setText(categoryName);
        });
    }

    private void initCategoryNameAutoComplete() {
        categoryNameAutoComplete = (AutoCompleteTextView) findViewById(R.id.categoryNameAutoComplete);
        ArrayAdapter<String> categories = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
                Stream.of(Category.listAll(Category.class)).map(Category::getName).collect(Collectors.toList()));
        categoryNameAutoComplete.setAdapter(categories);
        categoryNameAutoComplete.setThreshold(1);
    }

    private void initQuantityAndPriceEditTexts() {
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        priceEditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 100000000)});

        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        quantityEditText.setFilters(new InputFilter[]{new InputFilterMinMax(0, 10000000)});
    }

    private void initAddProductButton() {
        addProductButton = (Button) findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(v ->
            addProductPresenter.onAddProductButtonClick(productNameAutoComplete.getText().toString(),
                    categoryNameAutoComplete.getText().toString(),
                    priceEditText.getText().toString(),
                    quantityEditText.getText().toString())
        );
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
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
