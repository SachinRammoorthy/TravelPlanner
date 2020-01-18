package com.example.android.tflitecamerademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    String LANDMARK_NAME;
    ImageView imageView;
    TextView infoText;
    TextView cityText;
    TextView monumentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        LANDMARK_NAME = getIntent().getStringExtra("LANDMARK_NAME");

        imageView = (ImageView) findViewById(R.id.img);
        infoText = (TextView) findViewById(R.id.info_text);
        cityText = (TextView) findViewById(R.id.city_text);
        monumentText = (TextView) findViewById(R.id.name);

        if (LANDMARK_NAME.equals("eiffel")){
            imageView.setBackgroundResource(R.drawable.eiffel1);
            infoText.setText("Named after the engineer who built it (Gustave Eiffel), the Eiffel Tower is 324m in height. Constructed from 1887 to 1889 as the entrance to the 1889 World's Fair, it was initially criticized by some of France's leading artists and intellectuals for its design, but it has become a global cultural icon of France and one of the most recognizable structures in the world. The Eiffel Tower is the most-visited paid monument in the world; 6.91 million people ascended it in 2015.");
            cityText.setText("Paris, France");
            monumentText.setText("Eiffel Tower");
        } else if (LANDMARK_NAME.equals("opera")){
            imageView.setBackgroundResource(R.drawable.opera);
            infoText.setText("One of the most renowned performing arts venues in the world, the Sydney Opera House took 14 years to built and was completed on 20 October 1973. It is visited by numerous tourists each year, regardless of their interest in opera. It consists of five theatres -  the Concert Hall (with a seating capacity of 2679), Opera Theatre (1547 seats), Drama Theatre (544 seats), Playhouse (398 seats) and Studio Theatre (364 seats).");
            cityText.setText("Sydney, Australia");
            monumentText.setText("The Opera House");
        } else if (LANDMARK_NAME.equals("liberty")){
            imageView.setBackgroundResource(R.drawable.liberty);
            infoText.setText("The Statue of Liberty is a colossal sculpture on Liberty Island in New York, in the United States. The copper statue, a gift from the people of France to the people of the United States, was designed by French sculptor Frédéric Auguste Bartholdi and its metal framework was built by Gustave Eiffel. The statue was dedicated on October 28, 1886.");
            cityText.setText("New York, United States");
            monumentText.setText("The Statue of Liberty");
        } else if (LANDMARK_NAME.equals("bigben")){
            imageView.setBackgroundResource(R.drawable.bigbentest);
            infoText.setText("Its official name is the Great Bell. It was built and designed by Augustus Pugin and Charles Barry in Gothic Revival architecture in 1859. It was rung for the first time on 31 May 1859. The Bell inside weighs about 13.7 tonnes, while the hammer weighs 200kg.");
            cityText.setText("London, England");
            monumentText.setText("Big Ben");
        } else if (LANDMARK_NAME.equals("taj")){
            imageView.setBackgroundResource(R.drawable.taj);
            infoText.setText("The Taj Mahal was built by Mughal Emperor Shah Jahan in memory of his wife The four minarets surrounding the Taj Mahal were constructed away from the main structure than usual. The minarets also lean slightly outward rather than stand straight to prevent them from falling on the main tomb in a natural calamity like an earthquake.");
            cityText.setText("Agra, India");
            monumentText.setText("Taj Mahal");
        } else if (LANDMARK_NAME.equals("oakpodium")){
            imageView.setBackgroundResource(R.drawable.oakpodium);
            infoText.setText("The Oakridge auditorium podium was the birthplace of Travel Planner (TM). It was where we witnessed history in the making with flash mobs, music performances, and a lot more.");
            cityText.setText("Bangalore, India");
            monumentText.setText("Oakridge Auditorium Podium");
        }
    }
}
