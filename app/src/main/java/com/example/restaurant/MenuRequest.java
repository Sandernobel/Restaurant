package com.example.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    // Callback methods
    public interface Callback {
        void gotItems(ArrayList<MenuItem> menuItems);
        void gotItemsError(String message);
    }

    // Initialise variables
    public Callback items;
    public Context context;
    public String name;

    // Constructor
    public MenuRequest(Context context) {
        this.context = context;
    }

    // // Get items from API
    public void getItems(Callback activity, String name) {
        items = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu?category="+name;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // Get error message when API doesn't work
    @Override
    public void onErrorResponse(VolleyError error) {
        String err_message = error.getMessage();
        items.gotItemsError(err_message);
    }

    // Get items from API if it works
    @Override
    public void onResponse(JSONObject response) {
        // Initialize arraylist
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        try {
            // Loop over JSONarray to get values from item
            JSONArray items = response.getJSONArray("items");
            for (int position = 0; position < items.length(); position ++) {
                JSONObject menu = (JSONObject) items.get(position);
                String name = menu.getString("name");
                String description = menu.getString("description");
                String imageUrl = menu.getString("image_url");
                String category = menu.getString("category");
                String price = menu.getString("price");

                // Assign values to menu_item
                MenuItem menu_item = new MenuItem(name, description, imageUrl, category, price);
                menuItems.add(menu_item);
            }
        }
        // Listen for errors
        catch (JSONException error) {
            error.printStackTrace();
        }
        items.gotItems(menuItems);
    }
}
