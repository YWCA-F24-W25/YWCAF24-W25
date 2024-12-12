package com.example.dec12;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView allnames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("App", "Second Activity -  onCreate");
        setContentView(R.layout.activity_second);
      allnames = findViewById(R.id.allNamesText);
      String todisplay = "";
      for (int i = 0 ; i< MyApp.list.size(); i++){
          todisplay += MyApp.list.get(i).userName + " - " + MyApp.list.get(i).password + "\n";
      }
        allnames.setText(todisplay);


    }



    protected void onResume() {
        super.onResume();
        Log.d("App", "Second Activity -  onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("App", "Second Activity -  onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("App", "Second Activity -  onDestroy");


    }
}