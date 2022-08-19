package com.example.capstoneclaytondixon.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneclaytondixon.Database.Repository;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import com.example.capstoneclaytondixon.R;

import java.util.ArrayList;
import java.util.List;

public class Readings extends AppCompatActivity {

    Spinner searchSpinner;
    ListView listView;
    Repository repo;
    EditText searchField;
    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readings);
        RecyclerView recyclerView = findViewById(R.id.readingsRecycle);
        repo = new Repository(getApplication());
        List<ReadingEntity> readings = repo.getAllReadings();
        final ReadingsAdapter adapter = new ReadingsAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setReadingEntities(readings);
        recyclerView.setAdapter(adapter);
        searchField= findViewById(R.id.searchField);
        searchSpinner = findViewById(R.id.searchSpinner);
        ArrayAdapter<CharSequence> searchAdapter = ArrayAdapter.createFromResource(this, R.array.search_array, android.R.layout.simple_spinner_item);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        searchSpinner.setAdapter(searchAdapter);
        listView = findViewById(R.id.listView);
    }

    public void search(View view) {

        searchString = searchField.getText().toString();
        if(searchSpinner.getSelectedItem().toString().equals("Search By Date")) {
            List<ReadingEntity> readingEntities = repo.getReadingsByDate(searchString);
            List<String> readingStrings = new ArrayList<>();

            if(readingEntities.isEmpty()) {
                readingStrings.add("No matches have been found");
            } else {
                for(int count = 0; readingEntities.size() > count; count++) {
                    readingStrings.add(count, readingEntities.get(count).toString());
                }
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            listView.setAdapter(arrayAdapter);
        }

        if(searchSpinner.getSelectedItem().toString().equals("Search By Feeling")) {
            List<ReadingEntity> readingEntities = repo.getReadingsByFeeling(searchString);
            List<String> readingStrings = new ArrayList<>();


            if(readingEntities.isEmpty()) {
                readingStrings.add("No matches have been found");
            } else {
                for(int count = 0; readingEntities.size() > count; count++) {
                    readingStrings.add(count, readingEntities.get(count).toString());
                }
            }




            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            listView.setAdapter(arrayAdapter);
        }

        if(searchSpinner.getSelectedItem().toString().equals("Search By Time of Day")) {
            List<ReadingEntity> readingEntities = repo.getReadingsByTOD(searchString);
            List<String> readingStrings = new ArrayList<>();

            if(readingEntities.isEmpty()) {
                readingStrings.add("No matches have been found");
            } else {
                for(int count = 0; readingEntities.size() > count; count++) {
                    readingStrings.add(count, readingEntities.get(count).toString());
                }
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, readingStrings);
            listView.setAdapter(arrayAdapter);
        }
    }

}