package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokesAsyncTask extends AsyncTask<JokeGetTaskListener, Void, String> {
    private MyApi myApiService = null;
    private JokeGetTaskListener mJokeListener = null;

    @Override
    protected String doInBackground(JokeGetTaskListener... params) {
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

        mJokeListener = params[0];

        try {
            String theResponse = myApiService.sayHi("thiscanbeanything").execute().getData();
            Log.d("fart", "Joke: " + theResponse);
            return theResponse;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String theResult) {
        if (this.mJokeListener != null)
            this.mJokeListener.onComplete(theResult);

//        Intent intent = new Intent(mContext, ActivityJokeFromIntent.class);
//        intent.putExtra(ActivityJokeFromIntent.THE_JOKE, theResult);
//        mContext.startActivity(intent);
    }

    @Override
    protected void onCancelled() {
        if (this.mJokeListener != null) {
            this.mJokeListener.onComplete(null);
        }
        super.onCancelled();
    }
}
