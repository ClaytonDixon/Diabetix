package com.example.capstoneclaytondixon.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneclaytondixon.Database.Repository;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import com.example.capstoneclaytondixon.R;

public class editReading extends AppCompatActivity {

    TextView idField;
    EditText sugarDeleteField;
    EditText dateDeleteField;
    EditText timeDeleteField;
    Spinner todDeleteSpinner;
    EditText carbsDeleteField;
    EditText insulinDeleteField;
    EditText medicineDeleteField;
    Spinner feelingDeleteSpinner;
    Integer id;
    Integer sugar;
    String date;
    String time;
    String tod;
    Integer carbs;
    Integer insulin;
    String medicine;
    String feeling;
    Repository repo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reading);

        idField = findViewById(R.id.idField);
        sugarDeleteField = findViewById(R.id.sugarDeleteField);
        dateDeleteField = findViewById(R.id.dateDeleteField);
        timeDeleteField = findViewById(R.id.timeDeleteField);
        todDeleteSpinner = findViewById(R.id.todDeleteSpinner);
        carbsDeleteField = findViewById(R.id.carbsDeleteField);
        insulinDeleteField = findViewById(R.id.insulinDeleteField);
        medicineDeleteField = findViewById(R.id.medicineDeleteField);
        feelingDeleteSpinner = findViewById(R.id.feelingDeleteSpinner);

        id = getIntent().getIntExtra("id", 0);
        sugar = getIntent().getIntExtra("sugar", 0);
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        tod = getIntent().getStringExtra("tod");
        carbs = getIntent().getIntExtra("carbs", 0);
        insulin = getIntent().getIntExtra("insulin", 0);
        medicine = getIntent().getStringExtra("medicine");
        feeling = getIntent().getStringExtra("feeling");

        ArrayAdapter<CharSequence> todAdapter = ArrayAdapter.createFromResource(this, R.array.tod_array, android.R.layout.simple_spinner_item);
        todAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        todDeleteSpinner.setAdapter(todAdapter);

        ArrayAdapter<CharSequence> feelingAdapter = ArrayAdapter.createFromResource(this, R.array.feeling_array, android.R.layout.simple_spinner_item);
        feelingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        feelingDeleteSpinner.setAdapter(feelingAdapter);

        idField.setText(id.toString());
        sugarDeleteField.setText(sugar.toString());
        dateDeleteField.setText(date);
        timeDeleteField.setText(time);

        if(tod.equals("Before Breakfast")){
            todDeleteSpinner.setSelection(0);
        }
        if(tod.equals("After Breakfast")){
            todDeleteSpinner.setSelection(1);
        }
        if(tod.equals("Before Lunch")){
            todDeleteSpinner.setSelection(2);
        }
        if(tod.equals("After Lunch")){
            todDeleteSpinner.setSelection(3);
        }
        if(tod.equals("Before Dinner")){
            todDeleteSpinner.setSelection(4);
        }
        if(tod.equals("After Dinner")){
            todDeleteSpinner.setSelection(5);
        }
        if(tod.equals("No Meal")){
            todDeleteSpinner.setSelection(6);
        }
        if(tod.equals("Other/Snack")){
            todDeleteSpinner.setSelection(7);
        }
        if(tod.equals("After Exercise")){
            todDeleteSpinner.setSelection(8);
        }

        if(feeling.equals("Feel Fine")) {
            feelingDeleteSpinner.setSelection(0);
        }
        if(feeling.equals("Stressed Out")) {
            feelingDeleteSpinner.setSelection(1);
        }
        if(feeling.equals("Light Headed")) {
            feelingDeleteSpinner.setSelection(2);
        }
        if(feeling.equals("Don't Fell Well")) {
            feelingDeleteSpinner.setSelection(3);
        }
        if(feeling.equals("Other")) {
            feelingDeleteSpinner.setSelection(4);
        }

        carbsDeleteField.setText(carbs.toString());
        insulinDeleteField.setText(insulin.toString());
        medicineDeleteField.setText(medicine);


        repo = new Repository(getApplication());
    }

    public void deleteReading(View view) {
        ReadingEntity readingEntity = new ReadingEntity(id, sugar, date, time, carbs, insulin, medicine, feeling, tod);
        repo.deleteReading(readingEntity);
        Intent intent = new Intent(editReading.this, Readings.class);
        startActivity(intent);
    }

    public void saveReading(View view) {
        ReadingEntity readingEntity = new ReadingEntity(id, Integer.parseInt(sugarDeleteField.getText().toString()), dateDeleteField.getText().toString(),
                timeDeleteField.getText().toString(), Integer.parseInt(carbsDeleteField.getText().toString()), Integer.parseInt(insulinDeleteField.getText().toString())
                , medicineDeleteField.getText().toString(), feelingDeleteSpinner.getSelectedItem().toString(), todDeleteSpinner.getSelectedItem().toString());
        repo.updateReading(readingEntity);
        Intent intent = new Intent(editReading.this, Readings.class);
        startActivity(intent);
    }
}