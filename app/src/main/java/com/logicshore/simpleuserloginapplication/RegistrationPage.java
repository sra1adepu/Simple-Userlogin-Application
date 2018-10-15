package com.logicshore.simpleuserloginapplication;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class RegistrationPage extends AppCompatActivity {

    EditText username, password, confirmpassword, mobilenumber, mailid, dob;
    Spinner qualification;
    RadioGroup gendergroup;
    String gender;
    String selectedstring;

    DatabaseHandler databaseHandler;
    DatePickerDialog datePickerDialog;
    ImageView user_profilepic;
    private static final int CAMERA_REQUEST = 1888;
    String encoded="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        mobilenumber = (EditText) findViewById(R.id.mobilenumber);
        mailid = (EditText) findViewById(R.id.mailid);
        dob = (EditText) findViewById(R.id.dob);
        qualification = (Spinner) findViewById(R.id.qualification);
        gendergroup = (RadioGroup) findViewById(R.id.gendergroup);
        user_profilepic=(ImageView)findViewById(R.id.user_profilepic);

        user_profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        databaseHandler=new DatabaseHandler(this);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RegistrationPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ArrayAdapter arrayAdapter=new ArrayAdapter(RegistrationPage.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.qualification));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        qualification.setAdapter(arrayAdapter);

        qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getSelectedItem().toString().equals("Select")){
                    selectedstring="";
                }else{
                    selectedstring=adapterView.getSelectedItem().toString();
                    Toast.makeText(getApplicationContext(),selectedstring,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male:
                        gender = "male";
                        break;
                    case R.id.female:
                        gender = "female";
                        break;
                    default:
                        return;
                }
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
             encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.d("ConvertedImage",encoded);

//            byte[] decodedString = Base64.decode(encoded, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            user_profilepic.setImageBitmap(photo);
        }
    }
    public void registerbutton(View view) {

//       DatabaseHandler databaseHandler=new DatabaseHandler(this);
//        SQLiteDatabase sqLiteDatabase=databaseHandler.getWritableDatabase();
//        databaseHandler.insertintodb(1,username.getText().toString(), password.getText().toString(),
//               mobilenumber.getText().toString(), mailid.getText().toString(),dob.getText().toString() ,selectedstring,gender,sqLiteDatabase);
       // databaseHandler.close();

        databaseHandler.insertintodb(1,username.getText().toString(), password.getText().toString(),
                mobilenumber.getText().toString(), mailid.getText().toString(),dob.getText().toString() ,selectedstring,gender,encoded);


        Intent intent= new Intent(RegistrationPage.this, MainActivity.class);
        startActivity(intent);
    }
}
