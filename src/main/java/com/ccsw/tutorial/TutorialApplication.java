package com.ccsw.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);

    }

    /*
     * http://localhost:8080/
     * 
     * Whitelabel Error Page This application has no explicit mapping for /error, so
     * you are seeing this as a fallback.
     * 
     * Wed Mar 29 19:27:40 CEST 2023 There was an unexpected error (type=Not Found,
     * status=404).
     */

}

//@SpringBootApplication
//public class TutorialApplication implements CommandLineRunner {
//
//    @Autowired
//    StreamsPlay streamPlay;
//
//    public static void main(String[] args) {
//        SpringApplication.run(TutorialApplication.class, args);
//
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // TODO Auto-generated method stub
//        streamPlay.createStreams();
//    }
//}
