package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatabaseHelper myDb;
    EditText editName, editQuantity;
    Spinner editMeasurement;
    Button btnAddData;
    String[] items = new String[]{"ml", "g", "pcs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        myDb=new DatabaseHelper(this);

        editName=(EditText)findViewById(R.id.editText);
        editQuantity=(EditText)findViewById(R.id.editText2);
        btnAddData=(Button)findViewById(R.id.button_add);

        editMeasurement = (Spinner) findViewById(R.id.spinner1);
        editMeasurement.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editMeasurement.setAdapter(adapter);

        AddData();

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted= myDb.insertData(editName.getText().toString(), editQuantity.getText().toString(), editMeasurement.getSelectedItem().toString());

                        if(isInserted)
                            Toast.makeText(addProduct.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addProduct.this, "Data IS NOT Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
