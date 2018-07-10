package com.example.nathengallardojohnson.twicebaked;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathengallardojohnson.twicebaked.model.Recipe;

import static com.example.nathengallardojohnson.twicebaked.RecipeDetailActivity.ARG_RECIPE_ID;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;

    public RecipeListAdapter(Context context) {
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
        Recipe currentRecipe = null;
        if (Baking.recipeList != null) {
            currentRecipe = Baking.recipeList.get(position);
            if(currentRecipe.getName() != null) {
                String name = currentRecipe.getName();
                String servings = Integer.toString(currentRecipe.getServings());
                holder.nameTextView.setText(name);
                holder.servingsTextView.setText(servings);
            } else
                holder.nameTextView.setText(R.string.no_data);
        }
    }

    @Override
    public int getItemCount() {
        return Baking.recipeList.size();
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView servingsTextView;
        final Context mContext;
        public RecipeListViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            this.nameTextView = itemView.findViewById(R.id.id_name);
            this.servingsTextView = itemView.findViewById(R.id.id_servings);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = (getAdapterPosition() + 1);
                    Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                    intent.putExtra(ARG_RECIPE_ID, Integer.toString(pos));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
