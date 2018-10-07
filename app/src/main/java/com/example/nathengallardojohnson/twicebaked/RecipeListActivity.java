package com.example.nathengallardojohnson.twicebaked;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class RecipeListActivity extends AppCompatActivity {

    private String TAG = String.valueOf(getClass());
    public static String ARG_RECIPE_ID = "recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        Baking.twoPane = (findViewById(R.id.wide) != null);

        RecyclerView recyclerView = findViewById(R.id.recipe_list);
        recyclerView.setAdapter(new RecipeListAdapter(this));
    }

}
