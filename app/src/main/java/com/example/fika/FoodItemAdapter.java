package com.example.fika;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fika.Database.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

        final TextView foodName = (TextView) convertView.findViewById(R.id.menu_name);
        TextView foodPrice = (TextView) convertView.findViewById(R.id.menu_price);

        ImageView foodImage = convertView.findViewById(R.id.menu_img);
        // getting menu data for the row
        final CategorieModel m = menuItems.get(position);

        foodName.setText(m.getTitle());
        foodPrice.setText(m.getPrice());
        /*String url = "http://ashfoodlover.com/wp-content/uploads/2019/10/Screenshot_20190922-104529__01.jpg";
        Picasso.get().load(url).fit().into(foodImage, new Callback() {

            @Override
            public void onSuccess() {
                Log.d("Done scuc","ndf");
            }

            @Override
            public void onError(Exception e) {
                Log.e("Picasso failed", String.valueOf(e));
            }
        });*/

        final String foodId = "1";
        final ElegantNumberButton button = convertView.findViewById(R.id.btn_qty);
        FloatingActionButton addToCart = convertView.findViewById(R.id.floatingActionButton);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Database(activity.getBaseContext()).addToCart(new Order(
                       foodId,
                       m.getTitle(),
                       button.getNumber(),
                       m.getPrice()
                       ));
               Log.d("Added to cart",m.getTitle());
               //Toast.makeText(FoodItemAdapter.this,"Cart Details",Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = button.getNumber();
                Log.d("number changed ISSS",num);
            }
        });

        return convertView;
    }


}
