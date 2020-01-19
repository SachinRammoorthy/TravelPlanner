package com.example.android.tflitecamerademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class InputDestinationsActivity extends AppCompatActivity {

    Button goTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_destinations);

        goTour = (Button) findViewById(R.id.go_tour_button);
        goTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputDestinationsActivity.this, VirtualTour.class));
                //Add in intent putextra here the actual order along with all the destinations or something
            }
        });

    }
}
