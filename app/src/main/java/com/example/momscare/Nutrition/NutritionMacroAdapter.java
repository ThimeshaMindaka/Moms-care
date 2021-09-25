package com.example.momscare.Nutrition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;

import java.util.ArrayList;

public class NutritionMacroAdapter extends RecyclerView.Adapter<NutritionMacroAdapter.MacroViewHolder> {

    private Context context;
    private ArrayList id,foodName;


    NutritionMacroAdapter(Context context, ArrayList id, ArrayList foodName){

        this.context = context;
        this.id = id;
        this.foodName = foodName;

    }

    @NonNull
    @Override
    public MacroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_macros_recycler,parent,false);
        return  new MacroViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MacroViewHolder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.foodName.setText(String.valueOf(foodName.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MacroViewHolder extends RecyclerView.ViewHolder {

        TextView id, foodName;

        public MacroViewHolder(@NonNull View itemView) {

            super(itemView);
            id = itemView.findViewById(R.id.R_mealID);
            foodName = itemView.findViewById(R.id.R_mealName);
        }

    }
}
