package com.example.android.tflitecamerademo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.TSP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.TSP.populateMatrix;


public class InputDestinationsActivity extends AppCompatActivity {

    Button goTour;
    ArrayList<String> monumentNames = new ArrayList<String>();
    EditText originText;
    EditText dest1;
    EditText dest2;
    EditText dest3;
    EditText dest4;
    EditText dateText;
    String megaString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_destinations);

        dest1 = (EditText) findViewById(R.id.dest_1_text);
        dest2 = (EditText) findViewById(R.id.dest_2_text);
        dest3 = (EditText) findViewById(R.id.dest_3_text);
        dest4 = (EditText) findViewById(R.id.dest_4_text);
        originText = (EditText) findViewById(R.id.origin_text);
        dateText = (EditText) findViewById(R.id.date_text);

        originText.setText("BLR");
        dest1.setText("JFK");
        dest2.setText("CDG");
        dest3.setText("AMS");
        dest4.setText("FCO");
        dateText.setText("2020-06-15");


        goTour = (Button) findViewById(R.id.go_tour_button);
        goTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Function.isNetworkAvailable(getApplicationContext())){
                    GetMonuments getMonuments = new GetMonuments();
                    getMonuments.execute();
                    GetTSP getTSP = new GetTSP();
                    getTSP.execute();
                } else{
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }

                //makeMegaString();

            }
        });

    }

    public void makeMegaString(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i<40; i++){
                    System.out.println(monumentNames);
                    megaString = megaString + monumentNames.get(i) + ",";
                }
                Intent intent = new Intent(InputDestinationsActivity.this, VirtualTour.class);
                intent.putExtra("MONUMENTS", megaString);
                startActivity(intent);
            }
        }, 10000);

    }

    class GetMonuments extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        public String getCoordinates(String airportCode){

            String coordinates = "";
            if (airportCode.equals("LHR")){
                coordinates = "51.509865, -0.118092";
            } else if (airportCode.equals("HND")){
                coordinates = "35.652832, 139.839478" ;
            } else if (airportCode.equals("RUH")){
                coordinates = "24.774265, 46.738586";
            } else if (airportCode.equals("VIE")){
                coordinates = "48.210033, 16.363449";
            }  else if (airportCode.equals("PVG")){
                coordinates = "31.224361, 121.469170";
            } else if (airportCode.equals("TPE")){
                coordinates = "25.105497, 121.597366";
            } else if (airportCode.equals("FCO")){
                coordinates = "41.902782, 12.496366";
            } else if (airportCode.equals("MXP")){
                coordinates = "45.464664, 9.188540";
            } else if (airportCode.equals("AMS")){
                coordinates = "52.377956, 4.897070";
            } else if (airportCode.equals("BCN")){
                coordinates = "41.390205, 2.154007";
            } else if (airportCode.equals("ICN")){
                coordinates = "37.532600, 127.024612";
            } else if (airportCode.equals("HKG")){
                coordinates = "22.302711, 114.177216";
            } else if (airportCode.equals("KUL")){
                coordinates = "3.140853, 101.693207";
            } else if (airportCode.equals("IST")){
                coordinates = "41.015137, 28.979530";
            } else if (airportCode.equals("JFK")){
                coordinates = "40.730610, -73.935242";
            } else if (airportCode.equals("DXB")){
                coordinates = "25.276987, 55.296249";
            } else if (airportCode.equals("SIN")){
                coordinates = "1.290270, 103.851959";
            } else if (airportCode.equals("CDG")){
                coordinates = "48.864716, 2.349014";
            } else if (airportCode.equals("BKK")){
                coordinates = "13.736717, 100.523186";
            } else if (airportCode.equals("BLR")){
                coordinates = "12.972442, 77.580643";
            } else {
                coordinates = "12.972442, 77.580643";
            }
            return coordinates;
        }

        protected String doInBackground(String... args) {
            String xml = "";
            String urlParameters = "";



            String latlong1 = getCoordinates(dest1.getText().toString());
            String latlong2 = getCoordinates(dest2.getText().toString());
            String latlong3 = getCoordinates(dest3.getText().toString());
            String latlong4 = getCoordinates(dest4.getText().toString());


            xml = com.example.android.tflitecamerademo.Function.excuteGet("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?apiKey=0Frq5aaFxs2HPzEYhJbmrH_6OsjFpkpReqG13U4IG5k&mode=retrieveLandmarks&prox=" + latlong1 + "1000", urlParameters);
            xml = xml + com.example.android.tflitecamerademo.Function.excuteGet("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?apiKey=0Frq5aaFxs2HPzEYhJbmrH_6OsjFpkpReqG13U4IG5k&mode=retrieveLandmarks&prox=" + latlong2 + "1000", urlParameters);
            xml = xml + com.example.android.tflitecamerademo.Function.excuteGet("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?apiKey=0Frq5aaFxs2HPzEYhJbmrH_6OsjFpkpReqG13U4IG5k&mode=retrieveLandmarks&prox=" + latlong3 + "1000", urlParameters);
            xml = xml + com.example.android.tflitecamerademo.Function.excuteGet("https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json?apiKey=0Frq5aaFxs2HPzEYhJbmrH_6OsjFpkpReqG13U4IG5k&mode=retrieveLandmarks&prox=" + latlong4 + "1000", urlParameters);

            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                String nameFind = "\",\"Name";
                int index = xml.indexOf(nameFind);
                while (index >= 0) {
                    monumentNames.add(xml.substring(index + 10, (xml.substring(index+9,index+100).indexOf("DisplayPosition")-3+index+9)));
                    index = xml.indexOf(nameFind, index + 1);
                }
                System.out.println(monumentNames);
                Toast.makeText(getApplicationContext(), monumentNames.get(0), Toast.LENGTH_SHORT).show();

                for (int i = 0; i<40; i++){
                    megaString = megaString + monumentNames.get(i) + ",";
                }
                Intent intent = new Intent(getApplicationContext(), VirtualTour.class);
                intent.putExtra("MONUMENTS", megaString);
                startActivity(intent);

            }else{
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }
    }


    class GetTSP extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        int[][] matrix = new int[5][5];

        protected String doInBackground(String... args) {
            String xml = "";
            String urlParameters = "";

            String latlong1 = dest1.getText().toString();
            String latlong2 = dest2.getText().toString();
            String latlong3 = dest3.getText().toString();
            String latlong4 = dest4.getText().toString();
            String origin = originText.getText().toString();
            String date = dateText.getText().toString();

            String[] destinations = {origin, latlong1, latlong2, latlong3, latlong4};

            TSP tsp = new TSP();

            for(int i=0; i< destinations.length; i++){
                for(int j=0; j<destinations.length; j++){
                    System.out.println(destinations[i] + " " + destinations[j]);
                    try {
                        int weight = populateMatrix(destinations[i], destinations[j], date);
                        matrix[i][j] = weight;


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            String str = "";
            for(int i = 1; i<destinations.length; i++){
                str = str + i;
            }
            int n = str.length();
            tsp.calculatePerm(str, 0, n - 1);
            /*System.out.println(finalRouteNumbers.substring(6, 7));

            for(int i =2; i<=finalRouteNumbers.length(); i++){
                int index = Integer.parseInt(finalRouteNumbers.substring(i-1, i));
                finalRoute[i] = destinations[index];

            }*/




            /*xml = com.example.android.tflitecamerademo.Function.excuteGet("https://ff4fb229.ngrok.io/" +  + "1000", urlParameters);
            xml = xml + com.example.android.tflitecamerademo.Function.excuteGet("https://ff4fb229.ngrok.io/" + latlong2 + "1000", urlParameters);
            xml = xml + com.example.android.tflitecamerademo.Function.excuteGet("https://ff4fb229.ngrok.io/" + latlong3 + "1000", urlParameters);
            xml = xml + com.example.android.tflitecamerademo.Function.excuteGet("https://ff4fb229.ngrok.io/" + latlong4 + "1000", urlParameters);*/
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                String nameFind = "\",\"Name";
                int index = xml.indexOf(nameFind);
                while (index >= 0) {
                    monumentNames.add(xml.substring(index + 10, (xml.substring(index+9,index+100).indexOf("DisplayPosition")-3+index+9)));
                    index = xml.indexOf(nameFind, index + 1);
                }
                System.out.println(monumentNames);
                Toast.makeText(getApplicationContext(), monumentNames.get(0), Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
