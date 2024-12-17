package com.example.dec17_todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity implements
        ToDoBaseAdapter.SwitchListener, ToDoBaseAdapter.DeleteClickListener {

    ToDoBaseAdapter adapter;
    ListView todolist;
    FloatingActionButton addbutton;
    ToDoServiceClass serviceClass;
    private ActivityResultLauncher<Intent> myLauncher;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceClass = ((MyAPP)getApplication()).myservice;
        todolist = findViewById(R.id.tasklist);
        addbutton = findViewById(R.id.addnewtask);
        sharedPref = this.getSharedPreferences("alltodos",Context.MODE_PRIVATE);
        // MODE_PRIVATE is overwriting the exsisting file
        //MODE_APPEND is continue writing -- only works with wrting to a file
        readFromSharedPreferences();

        adapter = new ToDoBaseAdapter(serviceClass.todolist,this);
        todolist.setAdapter(adapter);
        adapter.switchListener = this;
        adapter.deleteClickListener = this;

        myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                ToDo d = (ToDo) data.getSerializableExtra("newtodo");
                ((MyAPP)getApplication()).myservice.todolist.add(d);
                updateTheListInStorage();

                adapter.notifyDataSetChanged();
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtaskIntent = new Intent(MainActivity.this, AddToDo.class);
               // startActivity(addnewtaskIntent);
                myLauncher.launch(addnewtaskIntent);
            }
        });
    }
    @Override
    public void switchChanged(int taskIndex, Boolean value) {
        ((MyAPP)getApplication()).myservice.todolist.get(taskIndex).isUrgent = value;
        updateTheListInStorage();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteClicked(int taskIndex) {
        // add an alert
        new AlertDialog.Builder(this).setTitle("Delete!!!").
                setMessage("Are You Sure You Want To Delete this Task?").
                setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // on delete
                        ((MyAPP)getApplication()).myservice.todolist.remove(taskIndex);
                        updateTheListInStorage();
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No",null).show();
    }

void updateTheListInStorage(){
    SharedPreferences.Editor editor = sharedPref.edit();
    Gson gson = new Gson();

    // convert list to string(JSON)
    // convert string(JSON) to list
    //encoding
    String json = gson.toJson( ((MyAPP)getApplication()).myservice.todolist);
    editor.putString("alltasks",json);
    editor.apply();
}
    void readFromSharedPreferences() {
        // read one todo at a time (string, string, boolean)
        String jsonfromPreferences = sharedPref.getString("alltasks", "");
        Gson gson = new Gson();
        // decoding
        ArrayList<ToDo> list = gson.fromJson(jsonfromPreferences, new TypeToken<ArrayList<ToDo>>() {
        }.getType());
        if (list == null) {
            ((MyAPP) getApplication()).myservice.todolist = new ArrayList<>(0);
        } else {
            ((MyAPP) getApplication()).myservice.todolist = list;
        }
    }
}