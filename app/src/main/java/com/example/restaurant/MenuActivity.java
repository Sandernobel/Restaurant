package com.example.restaurant;

import android.R.layout;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.layout.*;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ListView menu_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        MenuRequest request = new MenuRequest(this);
        request.getItems(this, name);

        menu_list = findViewById(R.id.menuList);
        onItemClickListener itemClicked = new onItemClickListener();
        menu_list.setOnItemClickListener(itemClicked);
    }

    private class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            Log.d("so far", "so good");
            intent.putExtra("item", item);
            startActivity(intent);
        }
    }

    @Override
    public void gotItems(ArrayList<MenuItem> menuItems) {
        ListView list = findViewById(R.id.menuList);

        MenuAdapter adapter;
        adapter = new MenuAdapter(this, simple_list_item_1, menuItems);
        list.setAdapter(adapter);
    }

    @Override
    public void gotItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
