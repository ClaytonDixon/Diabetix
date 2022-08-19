package com.example.capstoneclaytondixon.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneclaytondixon.Database.Repository;
import com.example.capstoneclaytondixon.Entity.ReadingEntity;
import com.example.capstoneclaytondixon.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repo = new Repository(getApplication());
        ReadingEntity readingEntity = new ReadingEntity(1, 120, "07/21/22", "3:23pm",
                56, 70, "yes", "Light Headed", "Before Lunch");
        repo.insertReading(readingEntity);
    }

    public void goTakeReading(View view) {
        Intent intent = new Intent(MainActivity.this, TakeReading.class);
        startActivity(intent);
    }

    public void goReports(View view) {
        Intent intent = new Intent(MainActivity.this, Reports.class);
        startActivity(intent);
    }

    public void goReadings(View view) {
        Intent intent = new Intent(MainActivity.this, Readings.class);
        startActivity(intent);
    }

    public void goAlerts(View view) {
        Intent intent = new Intent(MainActivity.this, Alerts.class);
        startActivity(intent);
    }
}