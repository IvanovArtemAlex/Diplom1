package com.example.diplom1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class UpdTrenActivity extends AppCompatActivity {

    ListView trenlist1;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor uprCursor1;
    SimpleCursorAdapter uprAdapter;
    String vvvvv;
    Cursor Cursor1;
    Cursor Cursor2;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_tren);


        Intent intent = getIntent();
        long ttt = intent.getLongExtra("id", 0);
        vvvvv = String.valueOf(ttt);

        trenlist1 = (ListView) findViewById(R.id.uprList1);

        textView2 = (TextView) findViewById(R.id.textView14);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();

    }
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        Cursor1 =  db.rawQuery("select * from Konstr where _id="+ vvvvv, null);
        Cursor1.moveToFirst();
        textView2.setText(Cursor1.getString(Cursor1.getColumnIndex("Name")));

        Cursor2 =  db.rawQuery("select * from Spisok where Konstr=" + vvvvv, null);

        String[] kon = new String[] {"Upr"};
        uprAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,Cursor2, kon, new int[]{android.R.id.text1}, 0);
        //trenlist1.setAdapter(uprAdapter);

    }
    public void onClickDel(View view)
    {

    }
}
