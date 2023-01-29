package com.example.megapizzaandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.megapizzaandroidapp.Adaptor.PizzaAdaptor;
import com.example.megapizzaandroidapp.Domain.PizzaDomain;
import com.example.megapizzaandroidapp.Helper.ManagmentCart;

import java.util.ArrayList;

public class AboutfoodActivity extends AppCompatActivity {
    static ArrayList<PizzaDomain> abFoodDomain = new ArrayList<>();

    private PizzaDomain pizzaDomain;
    TextView abName, abDescription, abIng, abEnergy, abProtein, abCarbon, abFats;
    ImageView abImage;
    Button abAddBut;

    private ManagmentCart managmentCart;

    public static String cartTitle, cartDescription, cartImage, cartIng;
    public static int cartPrice, cartWeight, cartEnergy;
    public static double cartFats, cartProtein, cartCarbon;

    fragment_Cart fragmentCart;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutfood);

        fragmentCart = new fragment_Cart();
        managmentCart = new ManagmentCart(this);

        getSupportActionBar().hide();

        pizzaDomain = (PizzaDomain) getIntent().getSerializableExtra("object");

        init();

        setText();

        cartTitle = PizzaAdaptor.stringName;
        cartImage = PizzaAdaptor.stringImage;
        cartIng = PizzaAdaptor.stringIng;
        cartDescription = PizzaAdaptor.stringDescription;
        cartPrice = PizzaAdaptor.stringPrice;
        cartCarbon = PizzaAdaptor.stringCarbon;
        cartFats = PizzaAdaptor.stringFats;
        cartProtein = PizzaAdaptor.stringProtein;

        abName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        abAddBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_Cart.qtyItems += 1;


                pizzaDomain.setCartPosition(1);
                managmentCart.insertFood(pizzaDomain);
            }
        });
    }

    private void init()
    {
        abName = findViewById(R.id.abFoodTitle);
        abIng = findViewById(R.id.abFoodIng);
        abDescription = findViewById(R.id.abFoodDescription);
        abEnergy = findViewById(R.id.abFoodEnergy);
        abCarbon = findViewById(R.id.abCarbon);
        abFats = findViewById(R.id.abFats);
        abProtein = findViewById(R.id.abProtein);
        abImage = findViewById(R.id.abFoodImage);
        abAddBut = findViewById(R.id.abAddButton);
    }

    private void setText()
    {
        abName.setText(pizzaDomain.getTitle());
        abIng.setText(pizzaDomain.getIngredients());
        abDescription.setText(pizzaDomain.getDescription());
        abCarbon.setText(String.valueOf(pizzaDomain.getCarbon()));
        abProtein.setText(String.valueOf(pizzaDomain.getProtein()));
        abFats.setText(String.valueOf(pizzaDomain.getFats()));
        abEnergy.setText(String.valueOf(Integer.valueOf(pizzaDomain.getEnergy())));
        int drawableResourceId = this.getResources().getIdentifier(pizzaDomain.getImage(), "drawable", this.getPackageName());
        abImage.setImageResource(drawableResourceId);
        abAddBut.setText("Добавить в корзину за " + pizzaDomain.getPrice() + " ₽");
    }
}