package com.example.dec12;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;// converted to bytes

public class User implements Serializable {
    String userName;
    String password;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
