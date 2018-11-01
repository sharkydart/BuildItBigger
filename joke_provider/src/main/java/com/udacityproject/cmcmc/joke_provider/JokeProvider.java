package com.udacityproject.cmcmc.joke_provider;

public class JokeProvider {
    public String makeJoke(){
        //send a random joke, of the desired type, from the joke repository
        return randomJoke();
    }
    private String randomJoke(){
        String[] jokes = {
                "fart",
                "burp",
                "queef",
                "sneeze",
                "cough"
        };

        return jokes[(int)(Math.random() * jokes.length)];
    }
}
