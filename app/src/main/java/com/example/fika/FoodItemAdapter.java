package com.example.fika;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class FoodItemAdapter extends BaseAdapter {
    private ArrayList<CategorieModel> menuItems;
    private LayoutInflater inflater;
    private Activity activity;

    public FoodItemAdapter(Activity activity, ArrayList<CategorieModel> menuItems) {
        this.activity = activity;
        this.menuItems = menuItems;
    }
    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.menu_list_layout, parent,false);

        TextView foodName = (TextView) convertView.findViewById(R.id.menu_name);
        TextView foodPrice = (TextView) convertView.findViewById(R.id.menu_price);

        // getting menu data for the row
        CategorieModel m = menuItems.get(position);

        foodName.setText(m.getTitle());
        foodPrice.setText(m.getPrice()+"€");

        return convertView;
    }
}
