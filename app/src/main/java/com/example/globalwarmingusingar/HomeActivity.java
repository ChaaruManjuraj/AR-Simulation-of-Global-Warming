package com.example.globalwarmingusingar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btn_current;
    private Button btn_fifty;
    private Button btn_hundred;
    private Button btn_learn_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Init buttons
        btn_current = findViewById(R.id.btn_current);
//        btn_fifty = findViewById(R.id.btn_fifty);
        btn_hundred = findViewById(R.id.btn_hundred);
        btn_learn_more = findViewById(R.id.btn_learn_more);

        btn_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                Intent cameraIntent=new Intent(getApplicationContext(),ArActivity.class);
                startActivity(cameraIntent);
            }
        });

//        btn_fifty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//                Intent cameraIntent=new Intent(getApplicationContext(),ArActivity.class);
//                startActivity(cameraIntent);
//            }
//        });

        btn_hundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent camerantent = new Intent("android.media.action.IMAGE_CAPTURE");
                Intent cameraIntent=new Intent(getApplicationContext(),after100.class);
                startActivity(cameraIntent);

            }
        });

        btn_learn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, LearnMoreActivity.class);
                startActivity(i);
            }
        });

    }




}