package com.example.diplom1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class TrenActivity extends Activity {

    TextView textv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren);

        Intent intent = getIntent();
        long ttt = intent.getLongExtra("id", 0);
        String vvvvv = String.valueOf(ttt);
        textv.setText(vvvvv);


    }
}
