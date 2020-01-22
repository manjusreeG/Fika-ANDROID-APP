package com.example.fika;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class FoodItemFragment extends Fragment {


    private View view;
    ListView menuList;
    ArrayList<CategorieModel> list;
    private FoodItemAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fooditem, null);
        menuList = view.findViewById(R.id.menuListView);
        list = new ArrayList<>();
        Log.d("List", String.valueOf(list));
        loadData();
        if(list != null){
            adapter = new FoodItemAdapter(FoodItemFragment.super.getActivity(), list);
            menuList.setAdapter(adapter);
        }

        return view;
    }


    private void loadData(){
        try {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Menu");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                    Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> da = data.iterator();
                    while (da.hasNext()){
                        DataSnapshot menu = da.next();
                        /*Log.d("foodName",dataSnapshot.toString());
                        Log.d("value",dataSnapshot.getValue().toString());
                        Log.d("foodName",menu.child("foodName").getValue().toString());
                        Log.d("foodDesc",menu.child("foodDesc").getValue().toString());
                        Log.d("foodDesc",menu.child("foodImg").getValue().toString());*/

                        CategorieModel item1= new CategorieModel();
                        item1.setTitle(menu.child("foodName").getValue().toString());
                        item1.setPrice(menu.child("foodPrice").getValue().toString());
                        item1.setImage(menu.child("foodImg").getValue().toString());
                        list.add(item1);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
