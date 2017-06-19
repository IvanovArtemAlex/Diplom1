package com.example.diplom1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    Button Prod;
    Button Sbros;
    ListView uprList;
    ListView konList;
    DatabaseHelper databaseHelper;
    public SQLiteDatabase db;
    Cursor uprCursor;
    Cursor konCursor;
    SimpleCursorAdapter uprAdapter;
    SimpleCursorAdapter konAdapter;
    FrameLayout tre;
    FrameLayout kon;
    FrameLayout upr;
    FrameLayout cal;
    TextView textView13;
    public int id_kon;
    String Name1;
    String Cal;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tre:
                    tre.setVisibility(View.VISIBLE);
                    kon.setVisibility(View.GONE);
                    upr.setVisibility(View.GONE);
                    cal.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_kon:
                    tre.setVisibility(View.GONE);
                    kon.setVisibility(View.VISIBLE);
                    upr.setVisibility(View.GONE);
                    cal.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_upr:
                    tre.setVisibility(View.GONE);
                    kon.setVisibility(View.GONE);
                    upr.setVisibility(View.VISIBLE);
                    cal.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_cal:
                    tre.setVisibility(View.GONE);
                    kon.setVisibility(View.GONE);
                    upr.setVisibility(View.GONE);
                    cal.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tre = (FrameLayout) findViewById(R.id.tre);
        kon = (FrameLayout) findViewById(R.id.kon);
        upr = (FrameLayout) findViewById(R.id.upr);
        cal = (FrameLayout) findViewById(R.id.cal);

        Prod = (Button) findViewById(R.id.button2);
        Sbros = (Button) findViewById(R.id.button4);
        textView13 = (TextView) findViewById(R.id.textView13);

        tre.setVisibility(View.VISIBLE);
        kon.setVisibility(View.GONE);
        upr.setVisibility(View.GONE);
        cal.setVisibility(View.GONE);


            Intent intent = getIntent();
            Name1 = intent.getStringExtra("Name");
            Cal = intent.getStringExtra("Cal");
            if (Name1 != null)
            {
                tre.setVisibility(View.GONE);
                kon.setVisibility(View.GONE);
                upr.setVisibility(View.GONE);
                cal.setVisibility(View.VISIBLE);
                Sbros.setVisibility(View.VISIBLE);
            }
            textView13.setText(Cal);
        Name1 = null;
        Cal = Cal+Cal;


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        uprList = (ListView)findViewById(R.id.uprlist);
        uprList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UprActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        konList = (ListView)findViewById(R.id.konlist);
        konList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UpdTrenActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.create_db();
    }
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        uprCursor =  db.rawQuery("select * from Group_upr", null);
        konCursor =  db.rawQuery("select * from Konstr", null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] upr = new String[] {"Name"};
        String[] kon = new String[] {"Name"};
        // создаем адаптер, передаем в него курсор
        uprAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,uprCursor, upr, new int[]{android.R.id.text1}, 0);
        uprList.setAdapter(uprAdapter);
        konAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,konCursor, kon, new int[]{android.R.id.text1}, 0);
        konList.setAdapter(konAdapter);





    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, TrenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        uprCursor.close();
        konCursor.close();
    }

    public void onClickProd(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ProdActivity.class);
        startActivity(intent);
    }
    public void onClickSbros(View view)
    {
        textView13.setText(null);
        Name1 = null;
        Cal = null;
        Sbros.setVisibility(View.GONE);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}
