package com.company.buylist;


import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.company.buylist.service.ProductService;
import com.company.buylist.service.ProductServiceImpl;


public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {

        private ProductService productService;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            Preference myPref = findPreference("delete_bought");
            myPref.setOnPreferenceClickListener(preference -> {
                productService.deleteAllProducts();
                Toast.makeText(getActivity(), "Bought products were deleted", Toast.LENGTH_SHORT).show();
                return true;
            });
            productService = new ProductServiceImpl();
        }
    }

}
