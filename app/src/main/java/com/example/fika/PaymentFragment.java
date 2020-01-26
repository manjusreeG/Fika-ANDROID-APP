package com.example.fika;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;




public class PaymentFragment extends Fragment {

    View view;

    CardForm cardForm;
    AlertDialog.Builder alertBuilder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        Toast.makeText(PaymentFragment.super.getActivity(),"Navigated", Toast.LENGTH_SHORT).show();

        Button button = view.findViewById(R.id.paymentBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.remove(PaymentFragment.this);
                fragment.add(R.id.fragment_container,new TrackingFragment());
                fragment.commit();*/

                cardFormSubmit();
            }
        });

        cardForm = (CardForm) view.findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .actionLabel("Purchase")
                .setup((AppCompatActivity) getActivity());
        cardForm.getCardNumber();
        cardForm.getExpirationMonth();
        cardForm.getExpirationYear();
        cardForm.getCvv();
        cardForm.getCardholderName();
        cardForm.getPostalCode();
        cardForm.getCountryCode();
        cardForm.getMobileNumber();
        Log.d("Card Form",cardForm.toString());


        return view;
    }



    public void cardFormSubmit() {
        if (cardForm.isValid()) {
            alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setTitle("Confirm before purchase");
            alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                    "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                    "Card CVV: " + cardForm.getCvv() + "\n" +
                    "Postal code: " + cardForm.getPostalCode() + "\n" +
                    "Phone number: " + cardForm.getMobileNumber());
            alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Toast.makeText(PaymentFragment.super.getActivity(), "Thank you for order", Toast.LENGTH_LONG).show();
                    FragmentTransaction fragment = getFragmentManager().beginTransaction();
                    fragment.remove(PaymentFragment.this);
                    fragment.add(R.id.fragment_container,new HomeFragment());
                    fragment.commit();
                }
            });
            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();

        } else {
            Toast.makeText(PaymentFragment.super.getActivity(), "Please complete the form", Toast.LENGTH_LONG).show();
        }
    }

}
