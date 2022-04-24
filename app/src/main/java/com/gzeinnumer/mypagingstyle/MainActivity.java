package com.gzeinnumer.mypagingstyle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.mypagingstyle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnMenu1.setOnClickListener(view -> {
        });
    }

}