package com.example.megapizzaandroidapp;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.megapizzaandroidapp.databinding.ProfileFragmentBinding;

import org.w3c.dom.Text;

public class profileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    SignActivity signActivity;

    ProfileFragmentBinding binding;
    TextView profileTitle, userIDTitle;
    Button myProfile, myOrders, myExit;

    public static int chooseButton;

    public static profileFragment newInstance() {
        return new profileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ProfileFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        signActivity = new SignActivity();

        profileTitle = binding.profileTitle;
        userIDTitle = binding.userIDTitle;
        myProfile = binding.myProfileButton;
        myOrders = binding.myOrdersButton;
        myExit = binding.myExitButton;

        profileTitle.setText("Здравствуйте, " + SignActivity.name);
        userIDTitle.setText("ID: " + SignActivity.id);


        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton = 1;
                Intent intent = new Intent(getContext(), profileActivity.class);
                startActivity(intent);
            }
        });

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton = 2;
                Intent intent = new Intent(getContext(), profileActivity.class);
                startActivity(intent);
            }
        });

        myExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton = 3;
                Intent intent = new Intent(getContext(), profileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}