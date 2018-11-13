package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Pair;
import android.app.Application;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ASyncTaskTest implements JokeGetTaskListener {
    CountDownLatch signal;
    String returnedJoke;

    public ASyncTaskTest(){}

    @Test
    public void tryGettingJoke(){
        try{
            signal = new CountDownLatch(1);
            new JokesAsyncTask().execute(this);
            signal.await();

            assertFalse(TextUtils.isEmpty(returnedJoke));
        }
        catch(Exception bork){
            fail();
        }

    }

    @Override
    public void onComplete(String potentialJoke) {
        returnedJoke = potentialJoke;
        signal.countDown();
    }
}
//    public void testSomething(){
//        final CountDownLatch signal = new CountDownLatch(1);
//        Service.doSomething(new Callback() {
//
//            @Override
//            public void onResponse(){
//                // test response data
//                // assertEquals(..
//                // assertTrue(..
//                // etc
//                signal.countDown();// notify the count down latch
//            }
//
//        });
//        signal.await();// wait for callback
//    }