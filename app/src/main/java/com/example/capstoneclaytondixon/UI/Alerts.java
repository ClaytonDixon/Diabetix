package com.example.capstoneclaytondixon.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneclaytondixon.Database.Repository;
import com.example.capstoneclaytondixon.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Alerts extends AppCompatActivity {

    EditText timeField1;
    EditText timeField2;
    EditText timeField3;
    EditText titleField1;
    EditText titleField2;
    EditText titleField3;
    Repository repo;
    int hour1, minute1;
    int hour2, minute2;
    int hour3, minute3;
    TimePickerDialog.OnTimeSetListener time1;
    TimePickerDialog.OnTimeSetListener time2;
    TimePickerDialog.OnTimeSetListener time3;
    SimpleDateFormat sdf;
    String calendarFormat;
    SimpleDateFormat df;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        repo = new Repository(getApplication());
        timeField1 = findViewById(R.id.timeField1);
        timeField2 = findViewById(R.id.timeField2);
        timeField3 = findViewById(R.id.timeField3);
        titleField1 = findViewById(R.id.titleField1);
        titleField2 = findViewById(R.id.titleField2);
        titleField3 = findViewById(R.id.titleField3);
        calendarFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(calendarFormat, Locale.US);
        df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        df.setTimeZone(TimeZone.getDefault());

    }

    public void clockTimeField1(View view) {
        time1 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour1 = hourOfDay;
                minute1 = minute;
                timeField1.setText(String.format(Locale.getDefault(), "%02d:%02d", hour1, minute1));
            }
        };
        TimePickerDialog timePickerDialog1 = new TimePickerDialog(this, time1, hour1, minute1, false);
        timePickerDialog1.setTitle("Select Time");
        timePickerDialog1.show();
    }

    public void clockTimeField2(View view) {
        time2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour2 = hourOfDay;
                minute2 = minute;
                timeField2.setText(String.format(Locale.getDefault(), "%02d:%02d", hour2, minute2));
            }
        };
        TimePickerDialog timePickerDialog2 = new TimePickerDialog(this, time2, hour2, minute2, false);
        timePickerDialog2.setTitle("Select Time");
        timePickerDialog2.show();
    }

    public void clockTimeField3(View view) {
        time3 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour3 = hourOfDay;
                minute3 = minute;
                timeField3.setText(String.format(Locale.getDefault(), "%02d:%02d", hour3, minute3));
            }
        };
        TimePickerDialog timePickerDialog3 = new TimePickerDialog(this, time3, hour3, minute3, false);
        timePickerDialog3.setTitle("Select Time");
        timePickerDialog3.show();
    }

    public void addAlert1(View view) throws ParseException {

        if (!titleField1.getText().toString().isEmpty() && !timeField1.getText().toString().isEmpty()) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            String title1 = titleField1.getText().toString();
            Date date1 = df.parse(timeField1.getText().toString());
            Long trigger1 = date1.getTime() + calendar.getTime().getTime() - 18000000;
            Intent intent1 = new Intent(Alerts.this, MyReceiver.class);
            intent1.putExtra("key", title1);
            PendingIntent sender1 = PendingIntent.getBroadcast(Alerts.this, MainActivity.numAlert1++, intent1, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager1.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger1, sender1);

        }
        if (titleField1.getText().toString().isEmpty() && timeField1.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill all fields for Alert 1", Toast.LENGTH_SHORT);
            errorToast.show();
        } else if (titleField1.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill title field for Alert 1", Toast.LENGTH_SHORT);
            errorToast.show();
        } else if (timeField1.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill time field for Alert 1", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }

    public void addAlert2(View view) throws ParseException {

        if (!titleField2.getText().toString().isEmpty() && !timeField2.getText().toString().isEmpty()) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            String title2 = titleField2.getText().toString();
            Date date2 = df.parse(timeField2.getText().toString());
            Long trigger2 = date2.getTime() + calendar.getTime().getTime() - 18000000;
            Intent intent2 = new Intent(Alerts.this, MyReceiver.class);
            intent2.putExtra("key", title2);
            PendingIntent sender2 = PendingIntent.getBroadcast(Alerts.this, MainActivity.numAlert1++, intent2, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager2.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger2, sender2);

        }
        if (titleField2.getText().toString().isEmpty() && timeField2.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill all fields for Alert 2", Toast.LENGTH_SHORT);
            errorToast.show();
        } else if (titleField2.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill title field for Alert 2", Toast.LENGTH_SHORT);
            errorToast.show();
        } else if (timeField2.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill time field for Alert 2", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }

    public void addAlert3(View view) throws ParseException {

        if (!titleField3.getText().toString().isEmpty() && !timeField3.getText().toString().isEmpty()) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            String title3 = titleField3.getText().toString();
            Date date3 = df.parse(timeField3.getText().toString());
            Long trigger3 = date3.getTime() + calendar.getTime().getTime() - 18000000;
            Intent intent3 = new Intent(Alerts.this, MyReceiver.class);
            intent3.putExtra("key", title3);
            PendingIntent sender3 = PendingIntent.getBroadcast(Alerts.this, MainActivity.numAlert1++, intent3, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager3 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager3.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trigger3, sender3);
        }
        if (titleField3.getText().toString().isEmpty() && timeField3.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill all fields for Alert 3", Toast.LENGTH_SHORT);
            errorToast.show();
        } else if (titleField3.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill title field for Alert 3", Toast.LENGTH_SHORT);
            errorToast.show();
        } else if (timeField3.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(Alerts.this, "Please fill time field for Alert 3", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }
}