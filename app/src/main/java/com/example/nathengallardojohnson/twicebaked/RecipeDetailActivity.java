package com.example.nathengallardojohnson.twicebaked;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * An activity representing a single Recipe detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeListActivity}.
 */
public class RecipeDetailActivity extends AppCompatActivity implements RecipeStepsFragment.OnListFragmentInteractionListener, RecipeDetailFragment.OnRecipeDetailFragmentInteractionListener {

    private static final String ARG_STEP_ID = "step";
    public static String ARG_RECIPE_ID  = "recipe";
    static int mRecipeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the Step fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            mRecipeId = getIntent().getIntExtra(ARG_RECIPE_ID, 0);
            arguments.putInt(ARG_RECIPE_ID, mRecipeId);
            RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
            recipeStepsFragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_step_container, recipeStepsFragment)
                    .commit();
            // If this is a tablet, add the ingredient fragment as well
            if (findViewById(R.id.recipe_detail_container) != null) {
                Baking.twoPane = true;
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_detail_container, ingredientsFragment)
                        .commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            navigateUpTo(new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(int pos) {

        Bundle arguments = new Bundle();
        arguments.putInt(ARG_RECIPE_ID, mRecipeId);
        arguments.putInt(ARG_STEP_ID, pos);

        if (Baking.twoPane) {
            if (pos == 0) {
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_detail_container, ingredientsFragment)
                        .commit();
            } else {
                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                recipeDetailFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_detail_container, recipeDetailFragment)
                        .commit();
            }
        } else {
            if (pos == 0) {
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                ingredientsFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_step_container, ingredientsFragment)
                        .commit();
            } else {
                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                recipeDetailFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_step_container, recipeDetailFragment)
                        .commit();
            }
        }

    }

    @Override
    public void onRecipeDetailFragmentInteraction(int position) {

    }
}
