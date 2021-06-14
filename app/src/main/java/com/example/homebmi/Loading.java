/*
 * Copyright (c) 2021. BMI - Measurer is one of the mobile application that will help user to know their health. In this application, it will inform user about BMI category, range, and risks. It will give user awareness on what they should with their BMI's
 */

package com.example.homebmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(Loading.this,MainActivity.class));
            finish();
            }
        },4000);
    }
}