package com.example.megapizzaandroidapp;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.megapizzaandroidapp.Adaptor.cartAdaptor;
import com.example.megapizzaandroidapp.Domain.PizzaDomain;
import com.example.megapizzaandroidapp.Helper.ManagmentCart;
import com.example.megapizzaandroidapp.databinding.FragmentCartFragmentBinding;
import com.example.megapizzaandroidapp.databinding.FragmentHomeFragmentBinding;
import com.example.megapizzaandroidapp.iface.ChangeNumberItemsListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class fragment_Cart extends Fragment {

    private FragmentCartViewModel mViewModel;

    @SuppressLint("StaticFieldLeak")
    public static FragmentCartFragmentBinding binding;

    private RecyclerView.Adapter adapter;
    private RecyclerView cartRecyclerView;

    ScrollView scrollView;

    cartAdaptor cartAdaptor;
    TextView summ, delivery, total, emptyCart, summTitle, deliveryTitle, totalTitle, cartTitle;
    Button cartButton;

    ManagmentCart managmentCart;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    int summPrice = 0;

    AboutfoodActivity aboutfoodActivity;

    public static int qtyItems = 0;

    public static fragment_Cart newInstance() {
        return new fragment_Cart();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCartFragmentBinding.inflate(getLayoutInflater());

        managmentCart = new ManagmentCart(getContext());

        View view = binding.getRoot();

        aboutfoodActivity = new AboutfoodActivity();

        scrollView = binding.scrollView2;

        summ = binding.summText;
        delivery = binding.deliveryText;
        total = binding.totalText;
        cartButton = binding.cartOrderButton;
        emptyCart = binding.emptyCartText;
        cartRecyclerView = binding.cartRecView;
        summTitle = binding.summTitle;
        deliveryTitle = binding.deliveryTitle;
        totalTitle = binding.totalTitle;
        cartTitle = binding.cartTitle;

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        int deliveryPrice = 150;

        for (int i = 0; i < aboutfoodActivity.abFoodDomain.size(); i++)
        {
            summPrice = summPrice + aboutfoodActivity.abFoodDomain.get(i).getPrice();
        }

        delivery.setText(deliveryPrice + "₽");

        initList();
        calculateCart();

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                ArrayList<PizzaDomain> pizzaDomains = managmentCart.getListCart();


                String cvSumOrder = String.valueOf(managmentCart.getTotalFee());
                String cvDelivery = "150";
                String cvTotal = String.valueOf(Math.round(managmentCart.getTotalFee() + 150));
                String cvStatus = "Готовится";
                String cvDateOrder = "2020-12-12";
                String cvDateComplete = "2020-12-12";
                String cvClientID = "1";
                String cvStreet = "LOL";
                String cvHouse = "LOL";
                String cvFlat = "LOL";

                cv.put(DatabaseHelper.ordersColumnSumOrder, cvSumOrder);
                cv.put(DatabaseHelper.ordersColumnSumDelivery, cvDelivery);
                cv.put(DatabaseHelper.ordersColumnTotal, cvTotal);
                cv.put(DatabaseHelper.ordersColumnStatus, cvStatus);
                cv.put(DatabaseHelper.ordersColumnDateOrder, cvDateOrder);
                cv.put(DatabaseHelper.ordersColumnDateComplete, cvDateComplete);
                cv.put(DatabaseHelper.ordersColumnClientID, cvClientID);
                cv.put(DatabaseHelper.ordersColumnStreet, cvStreet);
                cv.put(DatabaseHelper.ordersColumnHouse, cvHouse);
                cv.put(DatabaseHelper.ordersColumnFlat, cvFlat);

                db.insert(DatabaseHelper.tableOrders, null, cv);
            }
        });

        return view;
    }

    private void initList()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new cartAdaptor(managmentCart.getListCart(), getContext(), new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        cartRecyclerView.setAdapter(adapter);
        if(managmentCart.getListCart().isEmpty())
        {
            emptyCart.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.INVISIBLE);
        }
        else
        {
            emptyCart.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart()
    {
        int delivery = 150;
        int totalSum = Math.round(managmentCart.getTotalFee() + delivery);
        int itemTotalSum = Math.round(managmentCart.getTotalFee());

        total.setText(totalSum + "₽");
        summ.setText(managmentCart.getTotalFee() + "₽");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentCartViewModel.class);
        // TODO: Use the ViewModel
    }

}