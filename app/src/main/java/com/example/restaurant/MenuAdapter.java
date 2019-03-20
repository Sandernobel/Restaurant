package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    // Initialize variables
    private ArrayList<MenuItem> menuList;
    private Context context;
    private int resource;

    // Constructor
    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> menuList) {
        super(context, resource, menuList);
        this.menuList = menuList;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Load layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_row, parent, false);
        }
        // Get attributes from layout
        TextView name = convertView.findViewById(R.id.foodName);
        TextView price = convertView.findViewById(R.id.foodPrice);
        ImageView food = convertView.findViewById(R.id.foodImage);

        // Set attributes to appropriate items
        name.setText(menuList.get(position).getName());
        price.setText("€" + menuList.get(position).getPrice());
        Picasso.with(context).load(menuList.get(position).getImageUrl()).into(food);

        return convertView;
    }
}
