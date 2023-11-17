package com.ccsw.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.ccsw.tutorial.streams.StreamsPlay;

//@SpringBootApplication
public class TutorialApplication2 implements CommandLineRunner {

    @Autowired
    StreamsPlay streamPlay;

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);

    }

    // Testing streams
    @Override
    public void run(String... args) throws Exception {
        streamPlay.execute();

    }
}
