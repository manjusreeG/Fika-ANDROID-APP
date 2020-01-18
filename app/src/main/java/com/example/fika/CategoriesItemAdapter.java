package com.example.fika;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesItemAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<CategorieModel> menuItems;

    public CategoriesItemAdapter(Activity activity, ArrayList<CategorieModel> menuItems) {
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
            convertView = inflater.inflate(R.layout.categorie_item_layout, parent,false);

        TextView foodName = (TextView) convertView.findViewById(R.id.categorieTitle);
        //TextView foodDesc = (TextView) convertView.findViewById(R.id.foodDesc);
        TextView foodPrice = (TextView) convertView.findViewById(R.id.categorieDesc);

        // getting menu data for the row
        CategorieModel m = menuItems.get(position);

        foodName.setText(m.getTitle());
        //foodDesc.setText("Rating: " + String.valueOf(m.getFoodDesc()));
        foodPrice.setText(m.getDescription());

        return convertView;
    }
}
