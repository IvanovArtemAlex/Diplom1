package com.example.diplom1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class PodrActivity extends AppCompatActivity {

    TextView textView10;
    TextView textView11;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor uprCursor1;
    Cursor uprCursor2;
    String vvvvv;
    ImageView image111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podr);

        Intent intent = getIntent();
        long ttt = intent.getLongExtra("id", 0);
        vvvvv = String.valueOf(ttt);

        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
    }
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();

        uprCursor1 =  db.rawQuery("select Name from Upr where _id="+vvvvv, null);
        uprCursor2 =  db.rawQuery("select Text from Upr where _id="+vvvvv, null);
        uprCursor1.moveToFirst();
        uprCursor2.moveToNext();
        textView10.setText(uprCursor1.getString(uprCursor1.getColumnIndex("Name")));
        textView11.setText(uprCursor2.getString(uprCursor2.getColumnIndex("Text")));


    }


}
