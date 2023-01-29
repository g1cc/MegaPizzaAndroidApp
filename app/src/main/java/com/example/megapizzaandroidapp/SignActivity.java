package com.example.megapizzaandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.megapizzaandroidapp.databinding.ActivitySignBinding;

import java.io.IOException;

public class SignActivity extends AppCompatActivity {

    private ActivitySignBinding binding;
    TextView authLabel;
    EditText edPhone;
    EditText edPassword;
    Button loginButton;
    Button registerButton;
    String login;
    String password;
    CheckBox passwordSave;

    public static ConstraintLayout constraintLayout;

    public static String id, name, mail, phone, passwordStr, birthday;
    int idID, nameID, mailID, phoneID, passwordStrID, birthdayID;

    Fragment fragment5 = null;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    public static FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign);

        setTitle("Мега Пицца");


        authLabel = findViewById(R.id.AuthorizationLabel);
        edPhone = findViewById(R.id.editTextPhone);
        edPassword = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        passwordSave = findViewById(R.id.passwordSave);
        frameLayout = findViewById(R.id.framelay);
        constraintLayout = findViewById(R.id.constLayout);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.open();

        edPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    if (edPhone.length() == 0)
                    edPhone.setText("+7");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = edPhone.getText().toString();
                password = edPassword.getText().toString();

                if (login.length() == 12) {

                    String query = "SELECT Код, Имя, Пароль, [Дата рождения] as 'Дата рождения', [Номер телефона] as 'Номер телефона', [Электронный адрес] as 'Электронный адрес'\n" +
                            "FROM Клиенты WHERE [Номер телефона] = '" + login + "' and Пароль = '" + password + "'";

                    cursor = db.rawQuery(query, null);

                    if (cursor.moveToNext()) {

                        idID = cursor.getColumnIndex("Код");
                        nameID = cursor.getColumnIndex("Имя");
                        mailID = cursor.getColumnIndex("Электронный адрес");
                        phoneID = cursor.getColumnIndex("Номер телефона");
                        birthdayID = cursor.getColumnIndex("Дата рождения");
                        passwordStrID = cursor.getColumnIndex("Пароль");


                        id = cursor.getString(idID);
                        name = cursor.getString(nameID);
                        mail = cursor.getString(mailID);
                        phone = cursor.getString(phoneID);
                        birthday = cursor.getString(birthdayID);
                        passwordStr = cursor.getString(passwordStrID);

                        db.close();
                        Intent intent = new Intent(SignActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        authLabel.setText("-");
                    }

                }

                else
                {
                    authLabel.setText("unc");
                }


            }
        });




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.close();

                loginButton.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                edPassword.setVisibility(View.INVISIBLE);
                edPhone.setVisibility(View.INVISIBLE);
                authLabel.setVisibility(View.INVISIBLE);
                passwordSave.setVisibility(View.INVISIBLE);

                fragment5 = new fragment_registration();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelay, fragment5).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
