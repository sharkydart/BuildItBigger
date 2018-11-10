package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacityproject.cmcmc.joke_android_lib.ActivityJokeFromIntent;
import com.udacityproject.cmcmc.joke_provider.JokeProvider;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static String mJokeReceived = "";
    public String getmJokeReceived(){
        return mJokeReceived;
    }
    @Nullable private SimpleIdlingResource mIdlingResource;
    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MobileAds.initialize(this, "ca-app-pub-4988916255447155~3995113466");
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

    public void tellJoke(android.view.View view) {
//        JokeProvider comedian = new JokeProvider();
//        Toast.makeText(this, comedian.makeJoke(), Toast.LENGTH_SHORT).show();
        passJokeToAndroidLibrary(view.getContext());
    }
    public void passJokeToAndroidLibrary(Context theContext)
    {
//        JokeProvider comedian = new JokeProvider();
//
//        Intent intent = new Intent(theContext, ActivityJokeFromIntent.class);
//        intent.putExtra(ActivityJokeFromIntent.THE_JOKE, comedian.makeJoke());
//        theContext.startActivity(intent);
        String jokeQuality = "free";

        new EndpointsAsyncTask().execute(new Pair<Context, String>(theContext, jokeQuality));
    }

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(mIdlingResource != null)
                mIdlingResource.setIdleState(false);

            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setApplicationName("ca-app-pub-4988916255447155~3995113466")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;
            Log.d("fart", "input: " + name);

            try {
                String theResponse = myApiService.sayHi(name).execute().getData();
                Log.d("fart", "Joke: " + theResponse);
                mJokeReceived = theResponse;
                return theResponse;
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String theResult) {
//            JokeProvider comedian = new JokeProvider();
//            ((TextView)findViewById(R.id.tvJokeTest)).setText(theResult);
            Intent intent = new Intent(context, ActivityJokeFromIntent.class);
            intent.putExtra(ActivityJokeFromIntent.THE_JOKE, theResult);
            context.startActivity(intent);
            if(mIdlingResource != null)
                mIdlingResource.setIdleState(true);
        }
    }

}
