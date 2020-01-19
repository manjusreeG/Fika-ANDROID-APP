package com.example.fika;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class HomeFragment extends Fragment {

    CategoriesItemAdapter adapter;
    DatabaseReference databaseReference;

    ArrayList<CategorieModel> list;
    ListView categoriesList;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        view = inflater.inflate(R.layout.fragment_home, null);
        categoriesList = view.findViewById(R.id.listViewHome);
        //ArrayList<CategorieModel> list = (new CategorieModel()).list();
        list = new ArrayList<>();
        loadData();
        Log.d("List", String.valueOf(list));
        if(list != null){
            adapter = new CategoriesItemAdapter(HomeFragment.super.getActivity(), list);
            categoriesList.setAdapter(adapter);
        }


        /*CategorieModel categorieModel = new CategorieModel();

        ArrayList<CategorieModel> listNew = categorieModel.list();*/


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
                        Log.d("foodDesc",menu.child("foodDesc").getValue().toString());*/

                        CategorieModel item1= new CategorieModel();
                        item1.setTitle(menu.child("foodName").getValue().toString());
                        item1.setDescription(menu.child("foodDesc").getValue().toString());
                        item1.setPrice(menu.child("foodPrice").getValue().toString());
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