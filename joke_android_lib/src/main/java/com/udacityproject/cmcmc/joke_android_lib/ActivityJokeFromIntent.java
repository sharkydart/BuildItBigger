package com.udacityproject.cmcmc.joke_android_lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityJokeFromIntent extends AppCompatActivity {
    public static final String THE_JOKE = "the joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_from_intent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ask For Another Joke", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        } else {
            String theJoke = intent.getStringExtra(THE_JOKE);
            TextView tvJoke = findViewById(R.id.tv_joke);
            tvJoke.setText(theJoke);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Jokes not being sent!", Toast.LENGTH_SHORT).show();
    }

}
