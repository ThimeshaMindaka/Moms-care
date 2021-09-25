package com.example.momscare.Nutrition;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;

import java.util.ArrayList;

public class MealPlansAdapter extends RecyclerView.Adapter<MealPlansAdapter.MealsViewHolder> {

    private Context context;
    private ArrayList mealID,mealName;
    int position;

    MealPlansAdapter(Context context, ArrayList mealID, ArrayList mealName){

        this.context = context;
        this.mealID = mealID;
        this.mealName = mealName;

    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_meals_recycler,parent,false);
        return  new MealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        this.position = position;

        holder.mealID.setText(String.valueOf(mealID.get(position)));
        holder.mealName.setText(String.valueOf(mealName.get(position)));

        //show delete popup acivity
        holder.nutrition_button_deleteMeal.setOnClickListener((view) ->{


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete meal plan");
            builder.setMessage("Are you sure want to delete " + String.valueOf(mealName.get(position)) + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DBHelper myDB = new DBHelper(context);
                    myDB.deleteMealRow(String.valueOf(mealID.get(position)));
                    Intent intent = new Intent(context,MealList.class);
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();


        });
    }

    @Override
    public int getItemCount() {
        return mealID.size();
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder {

        TextView mealID, mealName;
        Button nutrition_button_deleteMeal;

        public MealsViewHolder(@NonNull View itemView) {

            super(itemView);
            mealID = itemView.findViewById(R.id.R_mealID);
            mealName = itemView.findViewById(R.id.R_mealName);
            nutrition_button_deleteMeal = itemView.findViewById(R.id.nutrition_button_deleteMeal);
        }

    }
}
