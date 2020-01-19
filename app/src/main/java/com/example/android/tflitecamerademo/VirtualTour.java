package com.example.android.tflitecamerademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class VirtualTour extends AppCompatActivity {

    ImageView btnPrevious;
    ImageView btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_tour);

        btnPrevious = (ImageView) findViewById(R.id.btn_previous);
        btnNext = (ImageView) findViewById(R.id.btn_next);
    }
}
