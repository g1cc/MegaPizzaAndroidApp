package com.example.megapizzaandroidapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megapizzaandroidapp.Domain.CouponDomain;
import com.example.megapizzaandroidapp.R;

import java.util.ArrayList;

public class couponAdaptor extends RecyclerView.Adapter<couponAdaptor.ViewHolder>{
    ArrayList<CouponDomain> couponDomains;

    public couponAdaptor(ArrayList<CouponDomain> couponDomains)
    {

        this.couponDomains = couponDomains;
    }

    @NonNull
    @Override
    public couponAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_coupon, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(couponDomains.get(position).getTitle());
        holder.coupon.setText(couponDomains.get(position).getCoupon());
        holder.newPrice.setText(String.valueOf(couponDomains.get(position).getNewPrice()));
        holder.oldPrice.setText(String.valueOf(couponDomains.get(position).getOldPrice()));
    }

    @Override
    public int getItemCount() {
        return couponDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView title, coupon, newPrice, oldPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cpTitle);
            coupon = itemView.findViewById(R.id.couponText);
            newPrice = itemView.findViewById(R.id.cpPriceNew);
            oldPrice = itemView.findViewById(R.id.cpPriceOld);
        }
    }
}
