package com.product.is.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.product.is.app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skipRoute();
    }

    public void skipRoute() {
        ImageButton addRoute = (ImageButton) findViewById(R.id.addRoute);
        ImageButton queryRoute = (ImageButton) findViewById(R.id.queryRoute);

        addRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddActivity.class);
                startActivity(intent);
                finish();
            }
        });

        queryRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
