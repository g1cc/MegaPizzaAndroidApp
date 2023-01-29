package com.example.megapizzaandroidapp;

import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.megapizzaandroidapp.Adaptor.PizzaAdaptor;
import com.example.megapizzaandroidapp.Domain.PizzaDomain;
import com.example.megapizzaandroidapp.databinding.FragmentHomeFragmentBinding;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class fragment_Home extends Fragment
{
    private FragmentHomeViewModel mViewModel;
    private FragmentHomeFragmentBinding binding;
    private RecyclerView.Adapter adapter;
    private  RecyclerView pizzaRecView, burgerRecView, snacksRecView, dessertsRecView, drinksRecView, sauceRecView;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    String query;

    String foodName;
    String foodPrice;
    String foodIng;
    String foodImage;
    String foodDescription;
    String foodEnergy;
    String foodProtein;
    String foodFats;
    String foodCarbon;
    String foodWeight;

    int foodNameID;
    int foodPriceID;
    int foodIngID;
    int foodImageID;

    int foodDescriptionID;
    int foodEnergyID;
    int foodProteinID;
    int foodFatsID;
    int foodCarbonID;
    int foodWeightID;

    public static fragment_Home newInstance() {
        return new fragment_Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = FragmentHomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        databaseHelper = new DatabaseHelper(getContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();


        setPizzaRecView();
        setBurgerRecView();
        setSnacksRecView();
        setDessertsRecView();
        setDrinksRecView();
        setSauceRecView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentHomeViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setPizzaRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        pizzaRecView = binding.pizzaRecView;
        pizzaRecView.setLayoutManager(linearLayoutManager);

        query = "select Меню.Название as 'Блюдо', Меню.Картинка as 'Картинка', Меню.Цена as 'Цена', Меню.Состав as 'Состав', Категории.Название as 'Категория', Меню.Белки, Меню.Жиры, Меню.Углеводы, Меню.Описание, Меню.Вес, Меню.[Энергетическая ценность]\n" +
                "from Меню inner join Категории on Меню.Категория = Категории.Код\n" +
                "where Категории.Название = 'Пицца'";

        cursor = db.rawQuery(query, null);

        ArrayList<PizzaDomain> pizzaDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            pizzaDomains.add(new PizzaDomain(foodName, foodImage, foodIng, foodDescription, Integer.parseInt(foodPrice), Integer.parseInt(foodEnergy), Double.parseDouble(foodFats), Double.parseDouble(foodCarbon), Double.parseDouble(foodProtein), Integer.parseInt(foodWeight)));
        }

        adapter = new PizzaAdaptor(pizzaDomains);
        pizzaRecView.setAdapter(adapter);
    }

    private void setBurgerRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        burgerRecView = binding.burgerRecView;
        burgerRecView.setLayoutManager(linearLayoutManager);

        query = "select Меню.Название as 'Блюдо', Меню.Картинка as 'Картинка', Меню.Цена as 'Цена', Меню.Состав as 'Состав', Категории.Название as 'Категория', Меню.Белки, Меню.Жиры, Меню.Углеводы, Меню.Описание, Меню.Вес, Меню.[Энергетическая ценность]\n" +
                "from Меню inner join Категории on Меню.Категория = Категории.Код\n" +
                "where Категории.Название = 'Бургеры'";

        cursor = db.rawQuery(query, null);

        ArrayList<PizzaDomain> pizzaDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            pizzaDomains.add(new PizzaDomain(foodName, foodImage, foodIng, foodDescription, Integer.parseInt(foodPrice), Integer.parseInt(foodEnergy), Double.parseDouble(foodFats), Double.parseDouble(foodCarbon), Double.parseDouble(foodProtein), Integer.parseInt(foodWeight)));
        }

        adapter = new PizzaAdaptor(pizzaDomains);
        burgerRecView.setAdapter(adapter);
    }

    private void setSnacksRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        snacksRecView = binding.snackRecView;
        snacksRecView.setLayoutManager(linearLayoutManager);

        query = "select Меню.Название as 'Блюдо', Меню.Картинка as 'Картинка', Меню.Цена as 'Цена', Меню.Состав as 'Состав', Категории.Название as 'Категория', Меню.Белки, Меню.Жиры, Меню.Углеводы, Меню.Описание, Меню.Вес, Меню.[Энергетическая ценность]\n" +
                "from Меню inner join Категории on Меню.Категория = Категории.Код\n" +
                "where Категории.Название = 'Закуски'";

        cursor = db.rawQuery(query, null);

        ArrayList<PizzaDomain> pizzaDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            pizzaDomains.add(new PizzaDomain(foodName, foodImage, foodIng, foodDescription, Integer.parseInt(foodPrice), Integer.parseInt(foodEnergy), Double.parseDouble(foodFats), Double.parseDouble(foodCarbon), Double.parseDouble(foodProtein), Integer.parseInt(foodWeight)));
        }

        adapter = new PizzaAdaptor(pizzaDomains);
        snacksRecView.setAdapter(adapter);
    }

    private void setDessertsRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dessertsRecView = binding.dessertsRecView;
        dessertsRecView.setLayoutManager(linearLayoutManager);

        query = "select Меню.Название as 'Блюдо', Меню.Картинка as 'Картинка', Меню.Цена as 'Цена', Меню.Состав as 'Состав', Категории.Название as 'Категория', Меню.Белки, Меню.Жиры, Меню.Углеводы, Меню.Описание, Меню.Вес, Меню.[Энергетическая ценность]\n" +
                "from Меню inner join Категории on Меню.Категория = Категории.Код\n" +
                "where Категории.Название = 'Десерты'";

        cursor = db.rawQuery(query, null);

        ArrayList<PizzaDomain> pizzaDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            pizzaDomains.add(new PizzaDomain(foodName, foodImage, foodIng, foodDescription, Integer.parseInt(foodPrice), Integer.parseInt(foodEnergy), Double.parseDouble(foodFats), Double.parseDouble(foodCarbon), Double.parseDouble(foodProtein), Integer.parseInt(foodWeight)));
        }

        adapter = new PizzaAdaptor(pizzaDomains);
        dessertsRecView.setAdapter(adapter);
    }

    private void setDrinksRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        drinksRecView = binding.drinksRecView;
        drinksRecView.setLayoutManager(linearLayoutManager);

        query = "select Меню.Название as 'Блюдо', Меню.Картинка as 'Картинка', Меню.Цена as 'Цена', Меню.Состав as 'Состав', Категории.Название as 'Категория', Меню.Белки, Меню.Жиры, Меню.Углеводы, Меню.Описание, Меню.Вес, Меню.[Энергетическая ценность]\n" +
                "from Меню inner join Категории on Меню.Категория = Категории.Код\n" +
                "where Категории.Название = 'Напитки'";

        cursor = db.rawQuery(query, null);

        ArrayList<PizzaDomain> pizzaDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();

            pizzaDomains.add(new PizzaDomain(foodName, foodImage, foodIng, foodDescription, Integer.parseInt(foodPrice), Integer.parseInt(foodEnergy), Double.parseDouble(foodFats), Double.parseDouble(foodCarbon), Double.parseDouble(foodProtein), Integer.parseInt(foodWeight)));
        }

        adapter = new PizzaAdaptor(pizzaDomains);
        drinksRecView.setAdapter(adapter);
    }

    private void setSauceRecView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        sauceRecView = binding.sauceRecView;
        sauceRecView.setLayoutManager(linearLayoutManager);

        query = "select Меню.Название as 'Блюдо', Меню.Картинка as 'Картинка', Меню.Цена as 'Цена', Меню.Состав as 'Состав', Категории.Название as 'Категория', Меню.Белки, Меню.Жиры, Меню.Углеводы, Меню.Описание, Меню.Вес, Меню.[Энергетическая ценность]\n" +
                "from Меню inner join Категории on Меню.Категория = Категории.Код\n" +
                "where Категории.Название = 'Соусы'";

        cursor = db.rawQuery(query, null);

        ArrayList<PizzaDomain> pizzaDomains = new ArrayList<>();

        while (cursor.moveToNext())
        {
            listFill();
            pizzaDomains.add(new PizzaDomain(foodName, foodImage, foodIng, foodDescription, Integer.parseInt(foodPrice), Integer.parseInt(foodEnergy), Double.parseDouble(foodFats), Double.parseDouble(foodCarbon), Double.parseDouble(foodProtein), Integer.parseInt(foodWeight)));

        }

        adapter = new PizzaAdaptor(pizzaDomains);
        sauceRecView.setAdapter(adapter);
    }


    public class NoScrollRecyclerView extends RecyclerView
    {
        public  NoScrollRecyclerView(Context context)
        {
            super(context);
        }

        public NoScrollRecyclerView(Context context, AttributeSet attributes)
        {
            super(context, attributes);
        }

        public  NoScrollRecyclerView(Context context, AttributeSet attributes, int style)
        {
            super(context, attributes, style);
        }

        public boolean dispatchTouchEvent(MotionEvent ev)
        {
            if(ev.getAction() == MotionEvent.ACTION_MOVE)
                return true;
            return  super.dispatchTouchEvent(ev);
        }
    }

    private void listFill()
    {
        foodNameID = cursor.getColumnIndex("Блюдо");
        foodPriceID = cursor.getColumnIndex("Цена");
        foodImageID = cursor.getColumnIndex("Картинка");
        foodIngID = cursor.getColumnIndex("Состав");
        foodDescriptionID = cursor.getColumnIndex("Описание");
        foodProteinID = cursor.getColumnIndex("Белки");
        foodFatsID = cursor.getColumnIndex("Жиры");
        foodCarbonID = cursor.getColumnIndex("Углеводы");
        foodEnergyID = cursor.getColumnIndex("Энергетическая ценность");
        foodWeightID = cursor.getColumnIndex("Вес");

        foodName = cursor.getString(foodNameID);
        foodIng = cursor.getString(foodIngID);
        foodPrice = cursor.getString(foodPriceID);
        foodImage = cursor.getString(foodImageID);
        foodProtein = cursor.getString(foodProteinID);
        foodFats = cursor.getString(foodFatsID);
        foodCarbon = cursor.getString(foodCarbonID);
        foodWeight = cursor.getString(foodWeightID);
        foodDescription = cursor.getString(foodDescriptionID);
        foodEnergy = cursor.getString(foodEnergyID);
    }

}