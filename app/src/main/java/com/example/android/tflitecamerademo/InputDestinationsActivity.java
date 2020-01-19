package com.example.android.tflitecamerademo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class InputDestinationsActivity extends AppCompatActivity {

    Button goTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_destinations);

        if(Function.isNetworkAvailable(getApplicationContext())){
            GetMonuments getMonuments = new GetMonuments();
            getMonuments.execute();
        } else{
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        goTour = (Button) findViewById(R.id.go_tour_button);
        goTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputDestinationsActivity.this, VirtualTour.class));
                //Add in intent putextra here the actual order along with all the destinations or something
            }
        });

    }

    class GetMonuments extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String... args) {
            String xml = "";
            String urlParameters = "";
            String latlong = "40.7579747,-73.9877313";
            xml = com.example.android.tflitecamerademo.Function.excuteGet("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?apiKey=0Frq5aaFxs2HPzEYhJbmrH_6OsjFpkpReqG13U4IG5k&mode=retrieveLandmarks&prox=" + latlong + "1000", urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                String nameFind = "\",\"Name";
                ArrayList<String> monumentNames = new ArrayList<String>();
                int index = xml.indexOf(nameFind);
                while (index >= 0) {
                    monumentNames.add(xml.substring(index + 10, (xml.substring(index+9,index+100).indexOf("DisplayPosition")-3+index+9)));
                    index = xml.indexOf(nameFind, index + 1);
                }
                System.out.println(monumentNames);


            }else{
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
