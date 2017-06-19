package com.example.diplom1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ProdPodrActivity extends AppCompatActivity {
    ListView prodlist1;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor uprCursor1;
    SimpleCursorAdapter uprAdapter;
    String vvvvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_podr);

        Intent intent = getIntent();
        long ttt = intent.getLongExtra("id", 0);
        vvvvv = String.valueOf(ttt);

        prodlist1 = (ListView) findViewById(R.id.prodpodrList1);
        prodlist1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CalActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
    }
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        uprCursor1 =  db.rawQuery("select * from Prod where Group_prod="+ vvvvv, null);
        String[] upr = new String[] {"Name"};
        uprAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,uprCursor1, upr, new int[]{android.R.id.text1}, 0);
        prodlist1.setAdapter(uprAdapter);

    }
}
