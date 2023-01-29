package com.example.megapizzaandroidapp.Adaptor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megapizzaandroidapp.AboutfoodActivity;
import com.example.megapizzaandroidapp.Domain.PizzaDomain;
import com.example.megapizzaandroidapp.R;

import java.util.ArrayList;

public class PizzaAdaptor extends RecyclerView.Adapter<PizzaAdaptor.ViewHolder> {
    ArrayList<PizzaDomain> pizzaDomains;
    public static String stringName, stringIng, stringDescription, stringImage;
    public static double stringFats, stringCarbon, stringProtein;
    public static int stringPrice, stringWeight, stringEnergy;
    public static int imageID;

    public PizzaAdaptor(ArrayList<PizzaDomain> pizzaDomains)
    {
        this.pizzaDomains = pizzaDomains;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pizza, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(pizzaDomains.get(position).getTitle());
        holder.description.setText(pizzaDomains.get(position).getIngredients());
        holder.price.setText(String.valueOf(pizzaDomains.get(position).getPrice()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(pizzaDomains.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.pizzaImage.setImageResource(drawableResourceId);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), AboutfoodActivity.class);
                intent.putExtra("object", pizzaDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() { return pizzaDomains.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, description;
        ImageView pizzaImage;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.vhpTitle);
            pizzaImage = itemView.findViewById(R.id.vhpImage);
            price = itemView.findViewById(R.id.vhpPrice);
            description = itemView.findViewById(R.id.vhpDescription);
            constraintLayout = itemView.findViewById(R.id.constLay);
        }
    }
}