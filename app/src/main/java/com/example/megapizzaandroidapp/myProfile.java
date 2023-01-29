package com.example.megapizzaandroidapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.megapizzaandroidapp.databinding.MyProfileFragmentBinding;

public class myProfile extends Fragment {

    private MyProfileViewModel mViewModel;
    MyProfileFragmentBinding binding;
    TextView myProfileName, myProfileMail, myProfilePhone, myProfilePassword, myProfileBirthday;
    ImageButton nameButton, mailButton, phoneButton, passwordButton, birthdayButton;

    public static myProfile newInstance() {
        return new myProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MyProfileFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();

        setText();

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        birthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void init()
    {

        myProfileBirthday = binding.myProfileBirthday;
        myProfileMail = binding.myProfileMail;
        myProfileName = binding.myProfileName;
        myProfilePhone = binding.myProfilePhone;
        myProfilePassword = binding.myProfilePassword;

        nameButton = binding.nameButton;
        mailButton = binding.mailButton;
        phoneButton = binding.phoneButton;
        passwordButton = binding.passwordButton;
        birthdayButton = binding.birthdayButton;
    }

    private void setText()
    {
        myProfilePassword.setText(SignActivity.passwordStr);
        myProfileMail.setText(SignActivity.mail);
        myProfilePhone.setText(SignActivity.phone);
        myProfileBirthday.setText(SignActivity.birthday);
        myProfileName.setText(SignActivity.name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}