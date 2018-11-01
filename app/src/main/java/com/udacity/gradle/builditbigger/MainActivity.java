package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacityproject.cmcmc.joke_android_lib.ActivityJokeFromIntent;
import com.udacityproject.cmcmc.joke_provider.JokeProvider;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
//        JokeProvider comedian = new JokeProvider();
//        Toast.makeText(this, comedian.makeJoke(), Toast.LENGTH_SHORT).show();
        passJokeToAndroidLibrary(view.getContext());
    }
    public void passJokeToAndroidLibrary(Context theContext)
    {
        Intent intent = new Intent(theContext, ActivityJokeFromIntent.class);
        JokeProvider comedian = new JokeProvider();
        intent.putExtra(ActivityJokeFromIntent.THE_JOKE, comedian.makeJoke());
        theContext.startActivity(intent);
    }
}
