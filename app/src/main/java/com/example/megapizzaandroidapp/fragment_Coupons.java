package com.example.megapizzaandroidapp;

import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
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

import com.example.megapizzaandroidapp.Adaptor.couponAdaptor;
import com.example.megapizzaandroidapp.Domain.CouponDomain;
import com.example.megapizzaandroidapp.databinding.FragmentCouponsFragmentBinding;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_Coupons extends Fragment {

    private FragmentCouponsViewModel mViewModel;
    private RecyclerView.Adapter adapter;
    private RecyclerView couponRecyclerView;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    String query;
    Cursor cursor;

    com.example.megapizzaandroidapp.Adaptor.couponAdaptor couponAdaptor;

    FragmentCouponsFragmentBinding binding;



    public static fragment_Coupons newInstance() {
        return new fragment_Coupons();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCouponsFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        setCouponRecyclerView();

        return view;
    }

    private void setCouponRecyclerView()
    {
        couponRecyclerView = binding.couponsRecView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        couponRecyclerView.setLayoutManager(linearLayoutManager);

        query = "select ????????.???????????????? as '??????????', ????????.???????? as '????????', ????????.[?????????? ????????] as ??????????????????, ????????.????????????????\n" +
                "from ???????? inner join ?????????????????? on ????????.?????????????????? = ??????????????????.??????\n" +
                "where ??????????????????.???????????????? = '????????????'";

        String title, oldPrice, newPrice, coupon;
        int titleID, oldPriceID, newPriceID, couponID;

        cursor = db.rawQuery(query, null);

        ArrayList<CouponDomain> couponDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            titleID = cursor.getColumnIndex("??????????");
            oldPriceID = cursor.getColumnIndex("????????");
            newPriceID = cursor.getColumnIndex("??????????????????");
            couponID = cursor.getColumnIndex("????????????????");

            title = cursor.getString(titleID);
            oldPrice = cursor.getString(oldPriceID);
            newPrice = cursor.getString(newPriceID);
            coupon = cursor.getString(couponID);

            couponDomains.add(new CouponDomain(title, coupon, Integer.parseInt(oldPrice), Integer.parseInt(newPrice)));
        }

        adapter = new couponAdaptor(couponDomains);
        couponRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentCouponsViewModel.class);
        // TODO: Use the ViewModel
    }

}