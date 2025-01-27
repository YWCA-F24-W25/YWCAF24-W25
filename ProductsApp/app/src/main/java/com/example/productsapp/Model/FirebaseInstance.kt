package com.example.productsapp.Model

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseInstance{
    val database : FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
}