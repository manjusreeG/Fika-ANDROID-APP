package com.example.fika;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;


public class FoodItemFragment extends Fragment {

    String JSON_STRING = "{\"menu\":{\"foodName\":\"Bruschetta\",\"foodDesc\":\"Sliced Onions,Cheese, Toasted bread,Garlic \",\"foodPrice\":120}}";
    String name, desc;
    double price;
    TextView foodName, foodDesc, foodPrice;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.food_item_layout, null);
    }
    public  void loadMenu(View view){
        Resources resources = getResources();
        InputStream inputStream = resources.openRawResource(R.raw.menu_list);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
            Log.d("build", String.valueOf(stringBuilder));
        }
        parseJSON(stringBuilder.toString());
    }

    private void parseJSON(String toString) {
    }

}
