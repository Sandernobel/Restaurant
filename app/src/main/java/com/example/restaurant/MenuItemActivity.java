package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");

        ImageView food = findViewById(R.id.foodImage);
        TextView name = findViewById(R.id.foodName);
        TextView price = findViewById(R.id.foodPrice);
        TextView description = findViewById(R.id.description);

        name.setText(item.getName());
        price.setText("€" + item.getPrice());
        description.setText(item.getDescription());

        String imageUrl = item.getImageUrl();
        Picasso.with(getApplicationContext()).load(imageUrl).into(food);
    }
}
