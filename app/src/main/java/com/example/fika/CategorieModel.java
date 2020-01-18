package com.example.fika;

import android.icu.text.Edits;
import android.util.Log;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class CategorieModel {

    String title;
    String description;
    ArrayList<CategorieModel> list;

    CategoriesItemAdapter adapter;

    public String getTitle() { return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<CategorieModel> list(){
        return  list;
    }

}
