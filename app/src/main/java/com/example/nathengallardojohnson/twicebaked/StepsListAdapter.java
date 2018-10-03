package com.example.nathengallardojohnson.twicebaked;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathengallardojohnson.twicebaked.model.Steps;

import java.util.List;

public class StepsListAdapter extends RecyclerView.Adapter<StepsListAdapter.StepListViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    List<Steps> stepsList;

    public StepsListAdapter(Context context, List<Steps> stepsList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.stepsList = stepsList;
    }

    @NonNull
    @Override
    public StepListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.steps_list_content, parent, false);
            return new StepListViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListViewHolder holder, int position) {
        if (position == 0) {
            holder.populate();
        } else {
            holder.populate(stepsList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return stepsList.size() + 1;
    }

    public class StepListViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView shortDescriptionTextView;
        final Context mContext;

        StepListViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            this.idTextView = itemView.findViewById(R.id.item_number);
            this.shortDescriptionTextView = itemView.findViewById(R.id.short_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                }
            });
        }

        public void populate() {
            shortDescriptionTextView.setText("Ingredients");
        }

        public void populate(Steps steps){
            if (steps.getId() >= 0) {
                idTextView.setText(Float.toString(steps.getId()));
                shortDescriptionTextView.setText(steps.getShortDescription());
            }
        }
    }
}