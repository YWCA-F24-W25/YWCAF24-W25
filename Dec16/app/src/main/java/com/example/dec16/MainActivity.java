package com.example.dec16;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Switch colorswitch;
    ListView collegesList;
    TextView titleText;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> collegsInToronto = new ArrayList<>(0);
        collegsInToronto.add(getResources().getString(R.string.scollege));
        collegsInToronto.add(getResources().getString(R.string.hcollege));
        collegsInToronto.add(getResources().getString(R.string.ccollege));

        if (savedInstanceState != null){
           counter =  savedInstanceState.getInt("counter");
        }

        collegesList = findViewById(R.id.collegesList);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.row_layout,
                R.id.collegenameinrow,
                collegsInToronto);
        collegesList.setAdapter(adapter);


        colorswitch = findViewById(R.id.changeColor);
        titleText = findViewById(R.id.titletext);
        titleText.setTextColor(getResources().getColor(R.color.lightRed));
        this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.light));
        Log.d("myAPP","Backgroud colors changed " + counter + " times");

        colorswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                View view = MainActivity.this.getWindow().getDecorView();
                counter++;
                Log.d("myAPP","Backgroud colors changed " + counter + " times");

                if (b) {
                    view.setBackgroundColor(getResources().getColor(R.color.dark));
                    titleText.setTextColor(getResources().getColor(R.color.dardRed));
                }else {
                    view.setBackgroundColor(getResources().getColor(R.color.light));
                    titleText.setTextColor(getResources().getColor(R.color.lightRed));
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
    }



}