/*
 * Copyright (c) 2021. BMI - Measurer is one of the mobile application that will help user to know their health. In this application, it will inform user about BMI category, range, and risks. It will give user awareness on what they should with their BMI's
 */

package com.example.homebmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate (R.menu.menu,menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.info:

                Intent intentt = new Intent(this, info.class);
                startActivity(intentt);

                break;


            case R.id.about:

                Intent intent = new Intent(this, about.class);
                startActivity(intent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}