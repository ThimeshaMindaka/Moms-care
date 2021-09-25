package com.example.momscare.Medical.Database;

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

import com.example.momscare.Medical.Add_shchedule;
import com.example.momscare.R;
import com.example.momscare.Medical.Update_shchedule;

import java.util.ArrayList;

public class DocterAdepter  extends RecyclerView.Adapter<DocterAdepter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList list_id, list_title, list_date,list_time;

    public DocterAdepter (Activity activity, Context context, ArrayList list_id, ArrayList list_title, ArrayList list_date,ArrayList list_time){
        this.activity = activity;
        this.context = context;
        this.list_id = list_id;
        this.list_title = list_title;
        this.list_date = list_date;
        this.list_time = list_time;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dctor_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.list_title_txt.setText(String.valueOf(list_title.get(position)));


        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_shchedule.class);
                intent.putExtra("id", String.valueOf(list_id.get(position)));
                intent.putExtra("name", String.valueOf(list_title.get(position)));
                intent.putExtra("date", String.valueOf(list_date.get(position)));
                intent.putExtra("time", String.valueOf(list_time.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {

        return list_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_id_txt, list_title_txt, list_date,list_time;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_txt = itemView.findViewById(R.id.list_id_txt);
            list_title_txt = itemView.findViewById(R.id.list_title_txt);
            list_date = itemView.findViewById(R.id.list_date);
            list_time = itemView.findViewById(R.id.list_time);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
