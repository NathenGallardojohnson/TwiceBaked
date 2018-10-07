package com.example.nathengallardojohnson.twicebaked;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathengallardojohnson.twicebaked.model.Recipe;

import static com.example.nathengallardojohnson.twicebaked.RecipeDetailActivity.ARG_RECIPE_ID;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    private String TAG = String.valueOf(getClass());

    RecipeListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_list_content, parent, false);
        return new RecipeListViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeListViewHolder holder, int position) {
        try {
            Recipe currentRecipe = Baking.recipeList.get(position);
            if (currentRecipe.getName() != null) {
                holder.nameTextView.setText(currentRecipe.getName());
                Log.d(TAG, "currentRecipeName: " + currentRecipe.getName());

            } else
                holder.nameTextView.setText(R.string.no_data);
            if (currentRecipe.getServings() > 0) {
                holder.servingsTextView.setText(Integer.toString(currentRecipe.getServings()));
                Log.d(TAG, "currentRecipeServings: " + currentRecipe.getServings());
            } else
                holder.servingsTextView.setText(R.string.no_data);
        } catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: " + e);
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = Baking.recipeList.size();
        Log.d(TAG, "getItemCount: " + itemCount);
        return itemCount;
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView servingsTextView;
        Context mContext;

        RecipeListViewHolder(View recipeListView, Context context) {
            super(recipeListView);
            this.mContext = context;
            this.nameTextView = recipeListView.findViewById(R.id.id_name);
            this.servingsTextView = recipeListView.findViewById(R.id.id_servings);

            recipeListView.setOnClickListener(v -> {
                Intent step_intent = new Intent(mContext, RecipeStepActivity.class);
                step_intent.putExtra(ARG_RECIPE_ID, getAdapterPosition());
                mContext.startActivity(step_intent);
            });
        }
    }
}
