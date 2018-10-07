package com.example.nathengallardojohnson.twicebaked;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathengallardojohnson.twicebaked.model.Ingredients;
import com.example.nathengallardojohnson.twicebaked.model.Steps;

import java.util.List;

public class RecipeStepActivity extends AppCompatActivity {

    private static final String ARG_STEP_ID = "step";
    public static String ARG_RECIPE_ID = "recipe";
    static int mRecipeId;
    //static int currentStep = 0;
    List<Steps> stepsList;
    boolean mTwoPane = false;
    private RecyclerView stepRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        //Initialize the variables
        Bundle arguments = new Bundle();
        mRecipeId = getIntent().getIntExtra(ARG_RECIPE_ID, 0);
        stepsList = Baking.recipeList.get(mRecipeId).getSteps();
        arguments.putInt(ARG_RECIPE_ID, mRecipeId);
        //arguments.putInt(ARG_STEP_ID, currentStep);

        Toolbar toolbar = findViewById(R.id.step_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(Baking.recipeList.get(mRecipeId).getName());
        }

        //Set up the step list
        stepRecyclerView = findViewById(R.id.steps_list);
        stepRecyclerView.setAdapter(new StepListAdapter(this, stepsList));

        // If this is a tablet, add the detail fragment as well
        if (findViewById(R.id.recipe_detail_container) != null) {
            mTwoPane = true;
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            recipeDetailFragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, recipeDetailFragment)
                    .commit();
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

    public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepHolder> {

        private List<Steps> mStepslist;
        private Context mContext;

        StepListAdapter(Context context, List<Steps> stepsList) {
            mStepslist = stepsList;
            mContext = context;
        }

        @NonNull
        @Override
        public StepListAdapter.StepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list_content, parent, false);
            return new StepHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull StepListAdapter.StepHolder stepHolder, int position) {
            if (position == 0) {
                stepHolder.populateIngredients(BuildIngredientsString(Baking.recipeList.get(mRecipeId).getIngredients()));
            } else {
                stepHolder.populateSteps(mStepslist.get(position - 1));
            }
        }

        String BuildIngredientsString(List<Ingredients> ingredientsList) {
            StringBuilder output = new StringBuilder(" ");
            for (Ingredients s : ingredientsList) {
                output.append(s.buildIngredientString());
            }
            return output.toString();
        }

        @Override
        public int getItemCount() {
            return stepsList.size() + 1;
        }

        class StepHolder extends RecyclerView.ViewHolder {

            TextView idTextView;
            TextView shortDescriptionTextView;

            StepHolder(View itemView) {
                super(itemView);
                this.idTextView = itemView.findViewById(R.id.step_number);
                this.shortDescriptionTextView = itemView.findViewById(R.id.short_description);
                itemView.setOnClickListener(v -> {
                    int step_id = getAdapterPosition() - 1;
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putInt(ARG_RECIPE_ID, mRecipeId);
                        arguments.putInt(ARG_STEP_ID, step_id);
                        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                        recipeDetailFragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.recipe_detail_container, recipeDetailFragment)
                                .commit();
                    } else {
                        Intent detail_intent = new Intent(mContext, RecipeDetailActivity.class);
                        detail_intent.putExtra(ARG_RECIPE_ID, mRecipeId);
                        detail_intent.putExtra(ARG_STEP_ID, step_id);
                        mContext.startActivity(detail_intent);
                    }
                });

            }

            void populateIngredients(String ingredients) {
                shortDescriptionTextView.setText(ingredients);
            }

            void populateSteps(Steps step) {
                shortDescriptionTextView.setText(step.getId() + " " + step.getShortDescription());
            }
        }
    }
}
