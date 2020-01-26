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

    String price;

    String image;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

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


    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public ArrayList<CategorieModel> list(){
        return  list;
    }

}
