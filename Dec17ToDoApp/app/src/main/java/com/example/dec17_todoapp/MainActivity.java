package com.example.dec17_todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity
        extends AppCompatActivity implements ToDoBaseAdapter.SwitchListener {
    ToDoBaseAdapter adapter;
    ListView todolist;
    FloatingActionButton addbutton;
    ToDoServiceClass serviceClass;
    private ActivityResultLauncher<Intent> myLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceClass = ((MyAPP)getApplication()).myservice;
        todolist = findViewById(R.id.tasklist);
        addbutton = findViewById(R.id.addnewtask);

        adapter = new ToDoBaseAdapter(serviceClass.todolist,this);
        todolist.setAdapter(adapter);
        adapter.switchListener = this;

        myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                ToDo d = (ToDo) data.getSerializableExtra("newtodo");
                ((MyAPP)getApplication()).myservice.todolist.add(d);
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
        adapter.notifyDataSetChanged();
    }
}