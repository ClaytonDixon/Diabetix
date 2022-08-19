package com.example.capstoneclaytondixon.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneclaytondixon.Database.Repository;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import com.example.capstoneclaytondixon.R;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class Reports extends AppCompatActivity {

    Spinner reportsSpinner;
    ListView reportsListView;
    TextView resultsField;
    TextView timeStamp;
    String searchString;
    Repository repo;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        repo = new Repository(getApplication());
        resultsField = findViewById(R.id.resultsField);
        reportsSpinner = findViewById(R.id.reportsSpinner);
        timeStamp = findViewById(R.id.timeStamp);
        ArrayAdapter<CharSequence> reportsAdapter = ArrayAdapter.createFromResource(this, R.array.reports_array, android.R.layout.simple_spinner_item);
        reportsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        reportsSpinner.setAdapter(reportsAdapter);
        reportsListView = findViewById(R.id.reportsListView);
    }

    public void generateReport(View view) throws ParseException {

        Timestamp time = new Timestamp(System.currentTimeMillis());
        timeStamp.setText("Timestamp " + time.toString() + " GMT");

        if(reportsSpinner.getSelectedItem().toString().equals("Generate Average Blood Sugar Levels")) {
            List<ReadingEntity> readingEntities = repo.getAllReadings();
            List<String> readingStrings = new ArrayList<>();
            Integer sugar = 0;

            for(int count = 0; readingEntities.size() > count; count++) {
                sugar += readingEntities.get(count).getReadingSugar();
                if(count == (readingEntities.size()-1)){
                    sugar = sugar / (count+1);
                }
            }

            for(int count = 0; readingEntities.size() > count; count++) {
                readingStrings.add(count, readingEntities.get(count).toString());
            }

            resultsField.setText(sugar.toString());
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            reportsListView.setAdapter(arrayAdapter);
        }

        if(reportsSpinner.getSelectedItem().toString().equals("Generate Time of Day with Lowest Blood Sugar")) {
            List<ReadingEntity> readingEntities = repo.getAllReadings();
            List<String> readingStrings = new ArrayList<>();
            Integer lowest = 999999999;
            String tod = "";

            for(int count = 0; readingEntities.size() > count; count++) {
                if(readingEntities.get(count).getReadingSugar() <= lowest) {
                  lowest = readingEntities.get(count).getReadingSugar();
                  tod = readingEntities.get(count).getReadingTOD();
                }
            }

            resultsField.setText(tod);

            for(int count = 0; readingEntities.size() > count; count++) {
                readingStrings.add(count, readingEntities.get(count).toString());
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            reportsListView.setAdapter(arrayAdapter);
        }

        if(reportsSpinner.getSelectedItem().toString().equals("Generate Time of Day with Highest Blood Sugar")) {
            List<ReadingEntity> readingEntities = repo.getAllReadings();
            List<String> readingStrings = new ArrayList<>();

            Integer highest = 0;
            String tod = "";

            for(int count = 0; readingEntities.size() > count; count++) {
                if(readingEntities.get(count).getReadingSugar() >= highest) {
                    highest = readingEntities.get(count).getReadingSugar();
                    tod = readingEntities.get(count).getReadingTOD();
                }
            }

            resultsField.setText(tod);


            for(int count = 0; readingEntities.size() > count; count++) {
                readingStrings.add(count, readingEntities.get(count).toString());
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            reportsListView.setAdapter(arrayAdapter);
        }

        if(reportsSpinner.getSelectedItem().toString().equals("Generate Average Number of Readings Per Day")) {
            List<ReadingEntity> readingEntities = repo.getAllReadings();
            List<String> readingStrings = new ArrayList<>();
            List<String> readingsDates = new ArrayList<>();
            Long earliestDate = 2658512800773L;
            Long latestDate = 0L;
            int readingCount = repo.getReadingCount();

            for(int count = 0; readingEntities.size() > count; count++) {
                readingsDates.add(count, readingEntities.get(count).getReadingDate());
            }

            for(int count = 0; readingEntities.size() > count; count++) {
                if(sdf.parse(readingEntities.get(count).getReadingDate()).getTime() > latestDate) {
                    latestDate = sdf.parse(readingEntities.get(count).getReadingDate()).getTime();
                }
                if(sdf.parse(readingEntities.get(count).getReadingDate()).getTime() < earliestDate) {
                    earliestDate = sdf.parse(readingEntities.get(count).getReadingDate()).getTime();
                }
            }
            long diffInMillies = Math.abs(latestDate - earliestDate);
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            float diffFloat = diff;
            float countFloat = readingCount;
            float average = countFloat/diffFloat;

            resultsField.setText(String.valueOf(average));

            for(int count = 0; readingEntities.size() > count; count++) {
                readingStrings.add(count, readingEntities.get(count).toString());
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            reportsListView.setAdapter(arrayAdapter);
        }

        if(reportsSpinner.getSelectedItem().toString().equals("Generate Readings Under 201")) {
            List<ReadingEntity> readingEntities = repo.getAllReadings();
            List<String> readingStrings = new ArrayList<>();
            Integer sugar = 200;

            for(int count = 0; readingEntities.size() > count; count++) {
                if(readingEntities.get(count).getReadingSugar() <= sugar) {
                    readingStrings.add(readingEntities.get(count).toString());
                }
            }

            resultsField.setText(String.valueOf(readingStrings.size()));

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            reportsListView.setAdapter(arrayAdapter);
        }

        if(reportsSpinner.getSelectedItem().toString().equals("Generate Readings Over 200")) {
            List<ReadingEntity> readingEntities = repo.getAllReadings();
            List<String> readingStrings = new ArrayList<>();
            Integer sugar = 201;

            for(int count = 0; readingEntities.size() > count; count++) {
                if(readingEntities.get(count).getReadingSugar() >= sugar) {
                    readingStrings.add(readingEntities.get(count).toString());
                }
            }

            resultsField.setText(String.valueOf(readingStrings.size()));

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            reportsListView.setAdapter(arrayAdapter);
        }

    }
}