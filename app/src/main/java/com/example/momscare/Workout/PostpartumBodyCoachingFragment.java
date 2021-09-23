package com.example.momscare.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.momscare.R;
import com.example.momscare.Workout.DataBase.DBHelper;
import com.example.momscare.Workout.DataBase.WorkOut;

import java.util.ArrayList;
import java.util.List;


public class PostpartumBodyCoachingFragment extends Fragment {

    TextView tv1;
    private ListView muscleGainListView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_postpartum_body, container, false);

        tv1 = v.findViewById(R.id.post_fragment_tv2);
        muscleGainListView = (ListView)v.findViewById(R.id.postListView);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);

        muscleGainListView.setAdapter(adapter);


        viewAll();
        tv1.setText("BMI calculated with a level lower than the required or in the normal level, is required to follow this workout package.");
        return v;
    }

    public void viewAll(){
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());

        List unames = dbHelper.readAllMuscleGainPackageInfo();

        for(Object woName : unames){
            arrayList.add(woName.toString());
        }
        if (unames != null) {
            Toast.makeText(getActivity().getApplicationContext(), unames.toString(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "No Exercises Available", Toast.LENGTH_SHORT).show();
        }

        muscleGainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listItemName = parent.getItemAtPosition(position).toString();
                WorkOut wt = new WorkOut();
                wt = dbHelper.readOneWorkOutInfo(listItemName);


                String woId = wt.getWorkoutID();
                String name = wt.getWorkoutName();
                String pkg = wt.getWorkoutPackage();
                String due = wt.getWorkoutDuration();
                String cal = wt.getWorkoutCalorie();
                String steps = wt.getWorkoutSteps();


                Intent intent = new Intent(getActivity(), ViewExercise.class);
                intent.putExtra("id", woId);
                intent.putExtra("name", name);
                intent.putExtra("pkg", pkg);
                intent.putExtra("due", due);
                intent.putExtra("cal", cal);
                intent.putExtra("step", steps);
                startActivity(intent);

            }
        });
    }
}