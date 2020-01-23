package com.example.fika;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fika.Database.CartAdapter;
import com.example.fika.Database.Database;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {

    private  View view;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference requests;

    TextView txtTotalPrice;
    Button orderBtn;

    List<Order> cart = new ArrayList<>();

    CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, null);

        //Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        requests = firebaseDatabase.getReference("Requests");

        //Init
        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = view.findViewById(R.id.totaltextView);
        orderBtn = view.findViewById(R.id.placeOrderBtn);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAddressAlert();
            }
        });

        loadCartItems();

        return view;

    }

    private void showAddressAlert() {
        AlertDialog.Builder alertdialaog = new AlertDialog.Builder(CartFragment.super.getActivity());
        alertdialaog.setTitle("Add Delivery Address");
        alertdialaog.setMessage("Enter your address");

        final EditText enterAddress = new EditText(CartFragment.super.getActivity());
        LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        enterAddress.setLayoutParams(linearLayout);
        alertdialaog.setView(enterAddress);
        alertdialaog.setIcon(R.drawable.ic_add_shopping_cart_black_24dp);

        alertdialaog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request = new Request("0293713213","John",
                        enterAddress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart);
            requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);

            new Database(getContext()).cleanCart();
            Toast.makeText(CartFragment.super.getActivity(),"Thank you", Toast.LENGTH_SHORT).show();
            //finish();
                FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.remove(CartFragment.this);
                fragment.add(R.id.fragment_container,new PaymentFragment());
                fragment.commit();
            };
        });

        alertdialaog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertdialaog.show();

    }

    private void loadCartItems() {
        cart = new Database(getActivity()).getCartDetails();
        cartAdapter = new CartAdapter(cart,getActivity());
        recyclerView.setAdapter(cartAdapter);

        int total = 0;
        for(Order order:cart)
            total += (Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getProductQty()));

        Locale locale = new Locale("en","US");
        NumberFormat frmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(frmt.format(total));

    }
}
