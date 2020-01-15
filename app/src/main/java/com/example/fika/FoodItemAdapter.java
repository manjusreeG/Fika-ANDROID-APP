package com.example.fika;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FoodItemAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Menu> menuItems;

    public FoodItemAdapter(Activity activity, List<Menu> menuItems) {
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
            convertView = inflater.inflate(R.layout.food_item_layout, parent,false);

        TextView foodName = (TextView) convertView.findViewById(R.id.foodTitle);
        TextView foodDesc = (TextView) convertView.findViewById(R.id.foodDesc);
        TextView foodPrice = (TextView) convertView.findViewById(R.id.foodPrice);

        // getting menu data for the row
        Menu m = menuItems.get(position);

        foodName.setText(m.getFoodName());
        foodDesc.setText("Rating: " + String.valueOf(m.getFoodDesc()));
        foodPrice.setText(String.valueOf(m.getFoodPrice()));

        return convertView;
    }
}
