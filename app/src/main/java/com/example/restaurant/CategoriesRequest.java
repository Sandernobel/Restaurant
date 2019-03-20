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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        // Initialize callback methods
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Initialize context and callback vars
    public Context context;
    private Callback cats;

    // constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Get categories from API
    public void getCategories(Callback activity) {
        cats = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String err_message = error.getMessage();
        cats.gotCategoriesError(err_message);
    }

    @Override
    public void onResponse(JSONObject response) {
        // Initialize arraylist
        ArrayList<String> categories = new ArrayList<String>();

        // Loop over JSONArray and add each category to arraylist
        try {
            JSONArray cats = response.getJSONArray("categories");
            for (int position = 0; position < cats.length(); position ++) {
                categories.add(cats.getString(position));
            }
        }
        // Listen for errors
        catch (JSONException error) {
            error.printStackTrace();
        }
        // call gotCategories
        cats.gotCategories(categories);
    }
}
