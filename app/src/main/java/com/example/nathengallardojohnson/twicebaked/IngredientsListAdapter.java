package com.example.nathengallardojohnson.twicebaked;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathengallardojohnson.twicebaked.model.Ingredients;

import java.util.List;

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ViewHolder> {

    private final List<Ingredients> mIngredientsList;

    public IngredientsListAdapter(List<Ingredients> ingredientsList) {
        mIngredientsList = ingredientsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIngredientTextView.setText(mIngredientsList.get(position).getIngredient());
        holder.mQuantityTextView.setText(Float.toString(mIngredientsList.get(position).getQuantity()));
        holder.mMeasureTextView.setText(mIngredientsList.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuantityTextView;
        public final TextView mMeasureTextView;
        public final TextView mIngredientTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mQuantityTextView = view.findViewById(R.id.quantity_text_view);
            mMeasureTextView = view.findViewById(R.id.measure_text_view);
            mIngredientTextView = view.findViewById(R.id.ingredient_text_view);
        }

    }
}
