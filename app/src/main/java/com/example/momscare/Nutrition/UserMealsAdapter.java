package com.example.momscare.Nutrition;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;

import java.util.ArrayList;

public class UserMealsAdapter extends RecyclerView.Adapter<UserMealsAdapter.MealsViewHolder> {

    private Context context;
    private ArrayList mealID,mealName;

    UserMealsAdapter(Context context, ArrayList mealID, ArrayList mealName){

        this.context = context;
        this.mealID = mealID;
        this.mealName = mealName;

        }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_user_meals_recycler,parent,false);
        return  new MealsViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {

        //holder.mealID.setText(String.valueOf(mealID.get(position)));
        holder.mealName.setText(String.valueOf(mealName.get(position)));

        //method to view meal plan details user selected
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("clicked id is <<<<<<<<",String.valueOf(mealID.get(position)));
                Intent intent = new Intent(context,user_meal_view.class);
                intent.putExtra("mealID",String.valueOf(mealID.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealID.size();
        }

    public class MealsViewHolder extends RecyclerView.ViewHolder {

        TextView mealID, mealName;
        LinearLayout mainLayout;

    public MealsViewHolder(@NonNull View itemView) {

        super(itemView);
        mealID = itemView.findViewById(R.id.user_viewMeal_Id);
        mealName = itemView.findViewById(R.id.user_viewMeal);
        mainLayout = itemView.findViewById(R.id.um_mainLayout);
    }

}
}