/*
 * Copyright (c) 2021. BMI - Measurer is one of the mobile application that will help user to know their health. In this application, it will inform user about BMI category, range, and risks. It will give user awareness on what they should with their BMI's
 */

package com.example.homebmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etWeight,etHeight;
    Button buttonMeasure;
    TextView tvResult,category,range,risk;



    SharedPreferences sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);
        buttonMeasure = (Button) findViewById(R.id.buttonMeasure);
        tvResult = (TextView) findViewById((R.id.tvResult));
        category = (TextView) findViewById((R.id.category));
        range = (TextView) findViewById((R.id.range));
        risk = (TextView) findViewById((R.id.risk));

        float height = (float) 0.0;
        float weight = (float) 0.0;

        buttonMeasure.setOnClickListener(this);

        sharedPref = this.getSharedPreferences("BMIdata", Context.MODE_PRIVATE);
        

        //Load data
        weight = sharedPref.getFloat("BMIweight",0);
        height = sharedPref.getFloat("BMIheight",0);
        etWeight.setText(""+weight);
        etHeight.setText(""+height *100);

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



    @Override
    public void onClick(View v){
        if (v.getId() == R.id.buttonMeasure) {
            try {

                float w = Float.parseFloat(etWeight.getText().toString());
                float h = Float.parseFloat(etHeight.getText().toString())/100;
                float BMI = w / (h*h); //BMI Formula

                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                tvResult.setText(decimalFormat.format(BMI) + ""); // Set the result to 2 decimal places

                if (BMI <= 18.4) {
                    category.setText("Underweight");
                    range.setText("18.4 and Below");
                    risk.setText("Malnutrition Risk");
                } else if ((BMI >= 18.5) && (BMI <= 24.9)) {
                    category.setText("Normal Weight");
                    range.setText("18.5 - 24.9");
                    risk.setText("Low Risk");
                } else if ((BMI >= 25) && (BMI <= 29.9)) {
                    category.setText("OverWeight");
                    range.setText("25 - 29.9");
                    risk.setText("Enhanced Risk");
                } else if ((BMI >= 30) && (BMI <= 34.9)) {
                    category.setText("Moderately Obese");
                    range.setText("30 - 34.9");
                    risk.setText("Medium Risk");
                } else if ((BMI >= 35) && (BMI <= 39.9)) {
                    category.setText("Severely Obese");
                    range.setText("35 - 39.9");
                    risk.setText("High Risk");
                } else if (BMI >= 40) {
                    category.setText("Very Severely Obese");
                    range.setText("40 and Above");
                    risk.setText("Very High Risk");
                }

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat("BMIweight", w);
                editor.putFloat("BMIheight", h );
                editor.apply();

            }
            catch (NumberFormatException nfe) {
                Toast toast = Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT);
                toast.show();
            } catch (Exception exp) {
                Toast toast = Toast.makeText(this, "Unkown Exception" + exp.getMessage(), Toast.LENGTH_SHORT);

                Log.d("Exception", exp.getMessage());
                toast.show();
            }
        }

    }


}