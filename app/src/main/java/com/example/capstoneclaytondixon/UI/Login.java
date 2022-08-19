package com.example.capstoneclaytondixon.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneclaytondixon.R;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    Button login;
    EditText usernameField;
    EditText passwordField;
    String username;
    String password;
    public List<String> userList = new ArrayList<>();
    public List<String> passList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        userList.add("username");
        passList.add("password");
    }

    public void signIn(View view) {
        username = usernameField.getText().toString();
        password = passwordField.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast errorToast1 = Toast.makeText(Login.this, "Please fill Username and Password field", Toast.LENGTH_SHORT);
            errorToast1.show();
        } else {
                if(!userList.contains(username) || !passList.contains(password)) {
                    Toast errorToast3 = Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT);
                    errorToast3.show();
                } else {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }

        }
    }