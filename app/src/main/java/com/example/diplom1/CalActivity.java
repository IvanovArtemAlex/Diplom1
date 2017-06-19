package com.example.diplom1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CalActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor Cursor1;
    Cursor Cursor2;
    Cursor Cursor3;
    SimpleCursorAdapter uprAdapter;
    String vvvvv;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    EditText editText1;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        Intent intent = getIntent();
        long ttt = intent.getLongExtra("id", 0);
        vvvvv = String.valueOf(ttt);


        textView1 = (TextView) findViewById(R.id.textView23);
        textView2 = (TextView) findViewById(R.id.textView24);
        textView3 = (TextView) findViewById(R.id.textView25);
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button3);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
    }
    public void onResume() {
        super.onResume();
        db = databaseHelper.open();

        Cursor1 =  db.rawQuery("select Name from Prod where _id="+vvvvv, null);
        Cursor2 =  db.rawQuery("select Cal from Prod where _id="+vvvvv, null);
        Cursor1.moveToFirst();
        Cursor2.moveToNext();
        textView2.setText(Cursor1.getString(Cursor1.getColumnIndex("Name")));


    }
    public void onClickAdd(View view)
    {
        int num = Integer.parseInt(editText1.getText().toString());
        int num1 = Integer.parseInt(Cursor2.getString(Cursor2.getColumnIndex("Cal")));
        int num2 = num*num1/100;
        String Cal11 = String.valueOf(num2);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("Name", Cursor1.getString(Cursor1.getColumnIndex("Name")));
        intent.putExtra("Cal", Cal11);
        startActivity(intent);
        finish();
    }


}
