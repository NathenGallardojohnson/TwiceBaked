package com.example.nathengallardojohnson.twicebaked;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathengallardojohnson.twicebaked.RecipeStepsFragment.OnListFragmentInteractionListener;
import com.example.nathengallardojohnson.twicebaked.dummy.DummyContent.DummyItem;
import com.example.nathengallardojohnson.twicebaked.model.Ingredients;
import com.example.nathengallardojohnson.twicebaked.model.Recipe;
import com.example.nathengallardojohnson.twicebaked.model.Steps;

import java.util.List;

import static com.example.nathengallardojohnson.twicebaked.RecipeDetailActivity.ARG_RECIPE_ID;

public class StepsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    //private final int recipeId;
    final int VIEW_TYPE_INGREDIENTS = 0;
    final int VIEW_TYPE_STEPS = 1;
    List<Ingredients> ingredientsList;
    List<Steps> stepsList;

    public StepsListAdapter(Context context, List<Ingredients> ingredientsList, List<Steps> stepsList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        //this.recipeId = recipeId;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = mInflater.inflate(R.layout.steps_list_content, parent, false);
        if(viewType == VIEW_TYPE_INGREDIENTS){
            View itemView = mInflater.inflate(R.layout.ingredient_list_content, parent, false);
            return new IngredientViewHolder(itemView, context);
        }

        if(viewType == VIEW_TYPE_STEPS){
            View itemView = mInflater.inflate(R.layout.steps_list_content, parent, false);
            return new StepListViewHolder(itemView, context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof IngredientViewHolder){
            ((IngredientViewHolder) holder).populate(ingredientsList.get(position));
        }

        if(holder instanceof StepListViewHolder){
            ((StepListViewHolder) holder).populate(stepsList.get(position - (ingredientsList.size() + 1)));

        }

    }

    @Override
    public int getItemViewType(int position){
        if(position < ingredientsList.size()){
            return VIEW_TYPE_INGREDIENTS;
        }

        if(position - ingredientsList.size() < stepsList.size()){
            return VIEW_TYPE_STEPS;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size() + stepsList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{
        public TextView quantityTextView;
        public TextView measureTextView;
        public TextView ingredientTextView;
        final Context mContext;
        public IngredientViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            this.quantityTextView = itemView.findViewById(R.id.quantity_text_view);
            this.measureTextView = itemView.findViewById(R.id.measure_text_view);
            this.ingredientTextView = itemView.findViewById(R.id.ingredient_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                    intent.putExtra(ARG_RECIPE_ID, Integer.toString(pos));
                    mContext.startActivity(intent);
                }
            });
        }

        public void populate(Ingredients ingredients){
            quantityTextView.setText(Float.toString(ingredients.getQuantity()));
            measureTextView.setText(ingredients.getMeasure());
            ingredientTextView.setText(ingredients.getIngredient());
        }
    }
    public class StepListViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView shortDescriptionTextView;
        final Context mContext;
        public StepListViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            this.idTextView = itemView.findViewById(R.id.item_number);
            this.shortDescriptionTextView = itemView.findViewById(R.id.short_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                    intent.putExtra(ARG_RECIPE_ID, Integer.toString(pos));
                    mContext.startActivity(intent);
                }
            });
        }

        public void populate(Steps steps){
            if(steps.getId() > 0) {
                idTextView.setText(Float.toString(steps.getId()));
                shortDescriptionTextView.setText(steps.getShortDescription());
            }
        }
    }
}