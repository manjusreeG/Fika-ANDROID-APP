package com.example.fika.Database;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.fika.Order;
import com.example.fika.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView cart_name,cart_price;
    public ImageView image_cart_count;

    //private ItemClickListener itemClickListener;

    public void setText_cartName(TextView text_cartName){
        this.cart_name=text_cartName;

    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cart_name = itemView.findViewById(R.id.cart_item_name);
        cart_price = itemView.findViewById(R.id.cart_item_price);
        image_cart_count = itemView.findViewById(R.id.cart_item_count);
    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_layout,parent,false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(""+listData.get(position).getProductQty(), Color.RED);
        holder.image_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("fr","FRANCE");
        NumberFormat frmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getProductQty()));
        holder.cart_price.setText(frmt.format(price));
        holder.cart_name.setText(listData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
