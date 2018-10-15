package com.logicshore.simpleuserloginapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class UserInformationAdapter extends CursorAdapter {
    public UserInformationAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.userfulldetails, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView username = view.findViewById(R.id.username);
        TextView password = view.findViewById(R.id.password);
        TextView mobilenumber = view.findViewById(R.id.mobilenumber);
        TextView mailid = view.findViewById(R.id.mailid);
        TextView dob = view.findViewById(R.id.dob);
        TextView qualification = view.findViewById(R.id.qualification);
        ImageView userpic=view.findViewById(R.id.userpic);

        Log.d("Total cursor",cursor.getString(7)+"");
        username.setText(cursor.getString(1));
        password.setText(cursor.getString(2));
        mobilenumber.setText(cursor.getString(3));
        mailid.setText(cursor.getString(4));
        dob.setText(cursor.getString(5));
        qualification.setText(cursor.getString(6));

        byte[] decodedString = Base64.decode(cursor.getString(8), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        userpic.setImageBitmap(decodedByte);

    }
}
