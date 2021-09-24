package com.example.momscare.ToDo_List.Database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momscare.R;
import com.example.momscare.ToDo_List.UpdateToDo;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList list_id, list_title, list_description;

    public UserAdapter(Activity activity, Context context, ArrayList list_id, ArrayList list_title, ArrayList list_description){
        this.activity = activity;
        this.context = context;
        this.list_id = list_id;
        this.list_title = list_title;
        this.list_description = list_description;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.list_title_txt.setText(String.valueOf(list_title.get(position)));
        //holder.list_description_txt.setText(String.valueOf(list_description.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateToDo.class);
                intent.putExtra("id", String.valueOf(list_id.get(position)));
                intent.putExtra("title", String.valueOf(list_title.get(position)));
                intent.putExtra("description", String.valueOf(list_description.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {

        return list_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_id_txt, list_title_txt, list_description_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_txt = itemView.findViewById(R.id.list_id_txt);
            list_title_txt = itemView.findViewById(R.id.list_title_txt);
            list_description_txt = itemView.findViewById(R.id.list_description_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
