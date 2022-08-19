package com.example.capstoneclaytondixon.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneclaytondixon.Database.Repository;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import com.example.capstoneclaytondixon.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TakeReading extends AppCompatActivity {

    EditText sugarField;
    EditText dateField;
    EditText timeField;
    Spinner todSpinner;
    EditText carbsField;
    EditText insulinField;
    EditText medicineField;
    Spinner feelingSpinner;
    Integer sugar;
    String date;
    String time;
    String tod;
    Integer carbs;
    Integer insulin;
    String medicine;
    String feeling;
    DatePickerDialog.OnDateSetListener readingDate;
    final Calendar myCalendar = Calendar.getInstance();
    Repository repo;
    String myFormat;
    SimpleDateFormat sdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_reading);
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        repo = new Repository(getApplication());
        sugarField = findViewById(R.id.sugarField);
        dateField = findViewById(R.id.dateField);
        timeField = findViewById(R.id.timeField);
        todSpinner = findViewById(R.id.todSpinner);
        carbsField = findViewById(R.id.carbsField);
        insulinField = findViewById(R.id.insulinField);
        medicineField = findViewById(R.id.medicineField);
        feelingSpinner = findViewById(R.id.feelingSpinner);
        ArrayAdapter<CharSequence> todAdapter = ArrayAdapter.createFromResource(this, R.array.tod_array, android.R.layout.simple_spinner_item);
        todAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        todSpinner.setAdapter(todAdapter);

        ArrayAdapter<CharSequence> feelingAdapter = ArrayAdapter.createFromResource(this, R.array.feeling_array, android.R.layout.simple_spinner_item);
        feelingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        feelingSpinner.setAdapter(feelingAdapter);

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String info = dateField.getText().toString();
                if(info.equals(""))info="02/10/22";
                try {
                    myCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TakeReading.this, readingDate, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        readingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

    }

    private void updateLabelStart() {
        dateField.setText(sdf.format(myCalendar.getTime()));
    }

    public void addReading(View view) {
        if(sugarField.getText().toString().isEmpty() || dateField.getText().toString().isEmpty() ||
        timeField.getText().toString().isEmpty() || todSpinner.getSelectedItem().toString().isEmpty() ||
        carbsField.getText().toString().isEmpty() || insulinField.getText().toString().isEmpty() ||
        medicineField.getText().toString().isEmpty() || feelingSpinner.getSelectedItem().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(TakeReading.this, "Please fill in all fields and drop downs", Toast.LENGTH_SHORT);
            errorToast.show();
        } else {
           sugar = Integer.parseInt(sugarField.getText().toString());
           date = dateField.getText().toString();
           time = timeField.getText().toString();
           tod = todSpinner.getSelectedItem().toString();
           carbs = Integer.parseInt(carbsField.getText().toString());
           insulin = Integer.parseInt(insulinField.getText().toString());
           medicine = medicineField.getText().toString();
           feeling = feelingSpinner.getSelectedItem().toString();
           ReadingEntity readingEntity = new ReadingEntity(repo.getReadingCount() + 1, sugar, date, time, carbs, insulin
           , medicine, feeling, tod);
           repo.insertReading(readingEntity);
            Intent intent = new Intent(TakeReading.this, Readings.class);
            startActivity(intent);
        }

    }
}