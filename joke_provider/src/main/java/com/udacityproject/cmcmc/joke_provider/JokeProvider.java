package com.udacityproject.cmcmc.joke_provider;

public class JokeProvider {
    public String makeJoke(){
        //send a random joke, of the desired type, from the joke repository
        return randomJoke();
    }
    private String randomJoke(){
        String[] jokes = {
                "Drunk driving is very whiskey.",
                "We might be snorkeling this weekend, but I'm not holding my breath.",
                "You don't get a body like this overnight. It takes years of neglect.",
                "Q: What kind of dog hears voices? A: A Shih-Tzu-Phrenic. ",
                "I was going to see the Royal Bermuda Philharmonic Orchestra last night, but the triangle player vanished.",
                "Early funerals are no good if you're a mourning person.",
                "Getting decapitated is a once-in-a-lifetime experience.",
                "There's no 'I' in delusion.",
                "My family treats me like a god; ignoring my existence until they need something.",
                "Opening a new funeral parlor can be quite an undertaking.",
                "The problem with the flat earth society is you can see their lies coming from miles away.",
                "The most expensive thing I own is my lack of impulse control.",
                "I just met Bruce Lee's vegan brother, Brocco",
                "My drug dealer really cracks me up.",
                "Reincarnation is making a comeback.",
                "She sold all her bourbon to start brewing beer, but I loved her still.",
                "These corduroy pillows are making headlines",
                "A soviet banner hanging above someone's mantle is a big red flag.",
                "People often say that my life must be tough, living with erectile dysfuction, but it's not so hard.",
                "My origami startup folded.",
                "I don't like ambiguity - I prefer...something else.",
                "A hug without u is very toxic.",
                "The earth cannot possibly be flat - cats would've pushed everything off the edges by now.",
                "I always wondered why a male sheep was called a 'ram', then it hit me.",
                "When I was a kid, I wanted to be a brain surgeon, but apparently I was too young.",
                "My company does random urine tests to detect any traces of hope or optimism.",
                "A man was drinking the blood of a vampire and said, 'Mmmm... irony.'",
                "The overconfident lion tamer was consumed by his own pride."
        };

        return jokes[(int)(Math.random() * jokes.length)];
    }
}
