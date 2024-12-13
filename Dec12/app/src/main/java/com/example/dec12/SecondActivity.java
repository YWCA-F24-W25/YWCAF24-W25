package com.example.dec12;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView allnames;
    Button searchbutton;
    EditText searchQuery;
    Button callButton;
    Button backButton;
    String searchTerm = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("App", "Second Activity -  onCreate");
        setContentView(R.layout.activity_second);
      allnames = findViewById(R.id.allNamesText);
      searchbutton = findViewById(R.id.searchbutton);
      searchQuery = findViewById(R.id.search_query);
        backButton = findViewById(R.id.backButton);

callButton = findViewById(R.id.callbutton);
      String todisplay = "";

      int valueFromMainActivity = getIntent().getIntExtra("mynumber",0);
      boolean iscold = getIntent().getBooleanExtra("isColdToday",false);
        User userFromMainActivity = (User)getIntent().getSerializableExtra("newUser");
        Log.d("extras",valueFromMainActivity +"");
        Log.d("extras",iscold +"");
        Log.d("extras",userFromMainActivity.userName +" " + userFromMainActivity.password);
        for (int i = 0 ; i< MyApp.list.size(); i++){
          todisplay += MyApp.list.get(i).userName + " - " + MyApp.list.get(i).password + "\n";
      }
        allnames.setText(todisplay);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!searchQuery.getText().toString().isEmpty()){
                    searchTerm = searchQuery.getText().toString();
                    Intent websearchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                    websearchIntent.putExtra(SearchManager.QUERY, searchTerm);
                    startActivity(websearchIntent);


                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backIntent = new Intent();
                backIntent.putExtra("searchTerm", searchTerm);
                setResult(RESULT_OK,backIntent);
                finish();
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!searchQuery.getText().toString().isEmpty()){
                    String phoneNumber = searchQuery.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                   callIntent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(callIntent);

                }
            }
        });
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