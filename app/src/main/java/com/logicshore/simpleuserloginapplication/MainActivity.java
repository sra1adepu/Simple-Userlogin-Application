package com.logicshore.simpleuserloginapplication;


import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    DatabaseHandler databaseHandler;
    Cursor cursor;
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        databaseHandler = new DatabaseHandler(this);
        cursor = databaseHandler.getContactsCount();
        hashMap = new HashMap<>();
        while (cursor.moveToNext()) {
            hashMap.put(cursor.getString(1), cursor.getString(2));
        }
    }

    public void loginbutton(View view) {

        if (hashMap.containsKey(username.getText().toString())) {
            Object value = hashMap.get(username.getText().toString());
            if (value.equals(password.getText().toString())) {
                Intent intent = new Intent(this, UserInformation.class);
                intent.putExtra("loginuserid", username.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "invalid password", Toast.LENGTH_SHORT).show();
            }
            System.out.println("Key : " + username.getText().toString() + " value :" + value);
        } else {
            Toast.makeText(getApplicationContext(), "invalid username", Toast.LENGTH_SHORT).show();
        }
        username.setText("");
        password.setText("");

        // List<UserDetails> list=databaseHandler.getContactsCount();
    }

    public void register(View view) {
        Intent registrationpage = new Intent(this, RegistrationPage.class);
        startActivity(registrationpage);


    }
}
