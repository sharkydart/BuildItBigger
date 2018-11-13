package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.media.session.PlaybackState;
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

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacityproject.cmcmc.joke_android_lib.ActivityJokeFromIntent;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements JokeGetTaskListener {

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
        new JokesAsyncTask().execute(this);
    }

    @Override
    public void onComplete(String potentialJoke) {
        Log.d("fart", "jokes and jokes and jokes!");
        Log.d("fart", potentialJoke);
        Intent intent = new Intent(this, ActivityJokeFromIntent.class);
        intent.putExtra(ActivityJokeFromIntent.THE_JOKE, potentialJoke);
        this.startActivity(intent);
    }

//    public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
//        private MyApi myApiService = null;
//        private Context context;
//        private JokeGetTaskListener mJokeListener = null;
//
//        //a way to set the listener
//        public EndpointsAsyncTask setListener(JokeGetTaskListener listener) {
//            this.mJokeListener = listener;
//            return this;
//        }
//
//        @Override
//        protected String doInBackground(Pair<Context, String>... params) {
//            if(mIdlingResource != null)
//                mIdlingResource.setIdleState(false);
//
//            if(myApiService == null) {  // Only do this once
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                        new AndroidJsonFactory(), null)
//                        // options for running against local devappserver
//                        // - 10.0.2.2 is localhost's IP address in Android emulator
//                        // - turn off compression when running against local devappserver
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                        .setApplicationName("ca-app-pub-4988916255447155~3995113466")
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });
//                // end options for devappserver
//
//                myApiService = builder.build();
//            }
//
//            context = params[0].first;
//            String name = params[0].second;
//
//            try {
//                String theResponse = myApiService.sayHi(name).execute().getData();
//                Log.d("fart", "Joke: " + theResponse);
//                return theResponse;
//            } catch (IOException e) {
//                return e.getMessage();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String theResult) {
//            if (this.mJokeListener != null)
//                this.mJokeListener.onComplete(theResult);
//
//            Intent intent = new Intent(context, ActivityJokeFromIntent.class);
//            intent.putExtra(ActivityJokeFromIntent.THE_JOKE, theResult);
//            context.startActivity(intent);
//            if(mIdlingResource != null)
//                mIdlingResource.setIdleState(true);
//        }
//
//        @Override
//        protected void onCancelled() {
//            if (this.mJokeListener != null) {
//                this.mJokeListener.onComplete(null);
//            }
//            super.onCancelled();
//        }
//    }

}
