package com.example.dec17_todoapp;

import java.util.ArrayList;
import java.util.Date;

public class ToDoServiceClass {

    ArrayList<ToDo> todolist = new ArrayList<>(0);


    void addNewTodo(ToDo newtask){
        todolist.add(newtask);
    }



}
