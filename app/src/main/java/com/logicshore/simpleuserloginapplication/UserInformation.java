package com.logicshore.simpleuserloginapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


import java.util.ArrayList;

public class UserInformation extends AppCompatActivity{

    DatabaseHandler databaseHandler;
    Cursor cursor;
    ListView userdetailslistview;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        userdetailslistview=(ListView) findViewById(R.id.userdetailslistview);
        arrayList = new ArrayList<>();
        databaseHandler=new DatabaseHandler(this);
        //SQLiteDatabase sqLiteDatabase = databaseHandler.getReadableDatabase();


         //this is for getting All User inforamtions which are in Databse.
      //  cursor=databaseHandler.getContactsCount();
//        UserInformationAdapter userInformationAdapter = new UserInformationAdapter(UserInformation.this, cursor);
//        userdetailslistview.setAdapter(userInformationAdapter);


        String userid=getIntent().getStringExtra("loginuserid");

        cursor=databaseHandler.getContactInfo(userid);
        UserInformationAdapter userInformationAdapter = new UserInformationAdapter(UserInformation.this, cursor);
        userdetailslistview.setAdapter(userInformationAdapter);

            }

    @Override
    protected void onResume() {
        super.onResume();


    }

//    @SuppressLint("MissingPermission")
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mGoogleMap=googleMap;
//        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
//        mGoogleMap.getUiSettings().setCompassEnabled(true);
//        LatLng latLng = new LatLng(latitude, longitude);
//        mGoogleMap.setMyLocationEnabled(true);
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(latitude,longitude)).zoom(16).build();
//        mGoogleMap.animateCamera(CameraUpdateFactory
//                .newCameraPosition(cameraPosition));
//    }
}
