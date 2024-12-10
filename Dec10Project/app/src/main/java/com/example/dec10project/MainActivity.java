package com.example.dec10project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nameText;
    EditText yearText;
    TextView msgText;
    Button addButton;
    Switch stdSwitch;
    RadioButton redradioBut;
    RadioButton greenradioBut;
    RadioButton blueradioBut;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("my app","in onCreate Function");
        nameText = findViewById(R.id.nametext);
        yearText = findViewById(R.id.yeartext);
        msgText = findViewById(R.id.welcomeText);
        addButton = findViewById(R.id.addBut);
        stdSwitch = findViewById(R.id.stdSwitch);
        redradioBut = findViewById(R.id.red);
        greenradioBut = findViewById(R.id.green);
        blueradioBut = findViewById(R.id.blue);


        redradioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(getColor(R.color.red));
            }
        });

        greenradioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(getColor(R.color.green));

            }
        });

        blueradioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(getColor(R.color.blue));

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgText.setText("");
                Log.d("my app"," Add Button Clicked");
                String name =  nameText.getText().toString();
                String yob = yearText.getText().toString();
                if (!name.isEmpty() && ! yob.isEmpty()){
                    int year = Integer.parseInt(yob);
                    if (year > 1920 && year < 2025){
                        if (stdSwitch.isChecked()){
                            msgText.setText("Welcome New Student: "+ name + ". You are "+ ( 2024 - year) + " years old");
                        }
                        else {
                            msgText.setText("Welcome " + name + ". You are " + (2024 - year) + " years old");
                        }
                        nameText.setText("");
                        yearText.setText("");

                        }else {
                        msgText.setText(R.string.error2);
                    }
                }else {
                    msgText.setText(R.string.error1);
                }
            }
        });




    }
}