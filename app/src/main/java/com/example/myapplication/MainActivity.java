package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    Button gotoAddProduct;
    Button gotoCheckInventory;
    Button gotoMealPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoAddProduct=(Button)findViewById(R.id.button_addProduct);
        gotoCheckInventory=(Button)findViewById(R.id.button_checkInventory);
        gotoMealPlan=(Button)findViewById(R.id.button_mealPlan);
        gotoAddProduct();
        gotoCheckInventory();
        gotoMealPlan();
    }
    public void gotoAddProduct(){
        gotoAddProduct.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), addProduct.class);
                        startActivity(intent);

                        if(intent!=null)
                            Toast.makeText(MainActivity.this, "GO TO ADD PRODUCT", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "ERROR: CANNOT GO TO ADD PRODUCT", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void gotoCheckInventory(){
        gotoCheckInventory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), checkInventory.class);
                        startActivity(intent);

                        if(intent!=null)
                            Toast.makeText(MainActivity.this, "GO TO CHECK INVENTORY", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "ERROR: CANNOT GO TO ADD PRODUCT", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void gotoMealPlan(){
        gotoMealPlan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), checkInventory.class);
                        startActivity(intent);

                        if(intent!=null)
                            Toast.makeText(MainActivity.this, "GO TO MEAL PLAN", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "ERROR: CANNOT GO TO ADD PRODUCT", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

}
