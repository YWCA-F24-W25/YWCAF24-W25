package com.example.dec12;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nametext;
    EditText passowrdtext;
    Button logIn;
    // MVVM
    Button register;

    private ActivityResultLauncher<Intent> myLauncher;

    // Incorrect Option
    // ArrayList<User> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("App", "Main Activity -  OnCreate");
        nametext = findViewById(R.id.username);
        passowrdtext = findViewById(R.id.password);
        logIn = findViewById(R.id.logIn);
        register = findViewById(R.id.register);


        myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                String searchTermInSecondActivity =  data.getStringExtra("searchTerm");
                Log.d("search term", searchTermInSecondActivity);
            }
        });

        Log.d("list", "The size of the list in OnCreate: " + MyApp.list.size());

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nametext.getText().toString().isEmpty() && !passowrdtext.getText().toString().isEmpty() ){
                    // add the new user to one array
                    User newUser = new User(nametext.getText().toString(), passowrdtext.getText().toString());
                    MyApp.list.add(newUser);
                    Log.d("list", "The size of the list in Log In function: " + MyApp.list.size());
                    // Context = the application memory with all access to resources
                    Intent tosecond = new Intent(MainActivity.this, SecondActivity.class);

                    tosecond.putExtra("mynumber", 33);
                    tosecond.putExtra("isColdToday", true);
                   tosecond.putExtra("newUser",newUser);
                   // startActivity(tosecond);
                    myLauncher.launch(tosecond);

                }else {

                    // error
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tothird = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(tothird);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("App", "Main Activity -  onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("App", "Main Activity -  onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("App", "Main Activity -  onDestroy");


    }
}