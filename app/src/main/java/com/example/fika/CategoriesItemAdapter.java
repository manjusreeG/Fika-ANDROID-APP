package com.example.fika;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesItemAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<CategorieModel> menuItems;

    public ArrayList<Integer> itemsimg = new ArrayList<Integer>();


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

        itemsimg.add(R.drawable.logo);
        itemsimg.add(R.drawable.omelette);
        itemsimg.add(R.drawable.bruschetta);
        itemsimg.add(R.drawable.logo);
        itemsimg.add(R.drawable.omelette);
        itemsimg.add(R.drawable.bruschetta);


        TextView foodName = (TextView) convertView.findViewById(R.id.categorieTitle);
        TextView foodDesc = (TextView) convertView.findViewById(R.id.categorieDesc);
        TextView foodType = (TextView) convertView.findViewById(R.id.categorieType);
        ImageView foodImage = convertView.findViewById(R.id.imageView);
        // getting menu data for the row
        CategorieModel m = menuItems.get(position);

        foodName.setText(m.getTitle());
        foodDesc.setText(m.getDescription());
        foodType.setText(m.getType());
       /* for(int i=0; i<itemsimg.size(); i++) {
            Log.d("going",itemsimg.get(i).toString());
            foodImage.setImageResource(itemsimg.get(i));
            Log.d("doneee",foodDesc.toString());
        }*/

        return convertView;
    }
}
