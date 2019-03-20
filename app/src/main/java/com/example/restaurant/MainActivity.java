package com.example.restaurant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    // Initialize listview
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get categoriesrequest
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // Set listener to list view
        onItemClickListener itemClicked = new onItemClickListener();
        list = findViewById(R.id.listView);
        list.setOnItemClickListener(itemClicked);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        list = findViewById(R.id.listView);

        // Set adapter to listview if categories is found
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);

        list.setAdapter(adapter);
    }

    private class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get clicked item and give it to next activity
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            String name = (String) parent.getItemAtPosition(position);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

    @Override
    public void gotCategoriesError(String message) {
        // If gotcategories failed, display error message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
