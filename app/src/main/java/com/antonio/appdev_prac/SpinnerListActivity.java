package com.antonio.appdev_prac;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SpinnerListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private Spinner listSpin;
    private ListView listView;
    private final String[] courses = {"None", "Android", "Java", "Python", "C++", "C#", "Kotlin", "Swift", "PHP", "Ruby", "JavaScript"};
    private final String[] topics = {"Algorithms and Complexities", "Data Structures", "Object-oriented Programming", "Web Development", "Mobile Development", "Game Development", "Software Engineering", "Database Management", "Computer Networks", "Operating Systems"};
    private ArrayAdapter<String> arrCourses, arrTopics;
    private TextView headerTextView, subtextTextView;
    private final Integer[] imageID = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food5, R.drawable.food6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_list);

        MyListAdapter adapter = new MyListAdapter(this, courses, topics, imageID);

        listSpin = findViewById(R.id.listspin);
        arrCourses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);

        listView = findViewById(R.id.listview);
        arrTopics = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topics);

//        listView.setAdapter(arrTopics);
        listView.setAdapter(adapter);

        listSpin.setAdapter(arrCourses);
        listSpin.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "You selected " + courses[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}