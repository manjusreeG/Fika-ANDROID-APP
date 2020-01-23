package com.example.fika;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class PaymentFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        Toast.makeText(PaymentFragment.super.getActivity(),"Navigated", Toast.LENGTH_SHORT).show();

        Button button = view.findViewById(R.id.paymentBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.remove(PaymentFragment.this);
                fragment.add(R.id.fragment_container,new TrackingFragment());
                fragment.commit();
            }
        });
        return view;
    }

}
