package com.ccsw.tutorial.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import jakarta.annotation.Nonnull;
import lombok.extern.java.Log;

@Log
@Component
public class StreamsPlay {

    public void execute() {
        createStreams();
        // createStreamFromCollection();
        mapVsFlatMap();
        java8_features();
        java11_features();
    }

    // https://reflectoring.io/comprehensive-guide-to-java-streams/
    public void createStreams() {
        Stream<Integer> stream = Stream.of(3, 4, 6, 2);

        IntStream integerStream = IntStream.of(3, 4, 6, 2);

        LongStream longStream = LongStream.of(3l, 4l, 6l, 2l);

        DoubleStream doubleStream = DoubleStream.of(3.0, 4.5, 6.7, 2.3);
        stream.forEach(System.out::println);
        integerStream.forEach(System.out::println);
        longStream.forEach(System.out::println);
        doubleStream.forEach(System.out::println);
        // doubleStream.forEach(c -> System.out.println(c));

        List<String> elements = Stream.of("zenia", "anna", "berta", "claudia").filter(element -> element.contains("i"))
                .map(el -> el.toUpperCase()).sorted().collect(Collectors.toList());

        String anyElement0 = elements.stream().findAny().orElse(null);
        Optional<String> firstElement0 = elements.stream().findFirst();
        log.info("filter-map-collect " + firstElement0.get());

        // Streams can not be reused,
        Stream<String> streamC = Stream.of("a", "b", "c").filter(element -> element.contains("b"));

        try {
            Optional<String> anyElement = streamC.findAny();
            Optional<String> firstElement = streamC.findFirst();// ----------> throws Exc
        } catch (IllegalStateException e) {
            log.info("Exception when reusing stream: " + e.getMessage());

        }
        Set<String> setEls = Stream.of("a", "b", "c").filter(element -> element.contains("b"))
                .collect(Collectors.toSet());
        String anyElement = setEls.stream().findAny().orElse("D");
        Optional<String> firstElement = setEls.stream().findFirst();
        log.info(anyElement);
        log.info(firstElement.get() + " " + firstElement.orElse("no val found"));// twice same
    }

    public void createStreamFromCollection() {
        Double[] elements = { 3.0, 4.5, 6.7, 2.3 };
        List<Double> elementsInCollection = Arrays.asList(elements);

        Stream<Double> stream = elementsInCollection.stream();

        Stream<Double> parallelStream = elementsInCollection.parallelStream();

        // stream.forEach(System.Logger::info);
//
//        parallelStream.forEach(logger::info);
    }

    public void mapVsFlatMap() {

        log.info("mapVsFlatMap \n"
                + "Stream without terminal operation: lazy , intermediate do not get executed(map, flatmap, filter.");

        Arrays.stream(new int[] { 1, 2, 3 }).map(i -> {
            log.info("doubling " + i);
            return i * 2;
        });

        log.info("Stream with terminal operation: intermediate get executed");
        int sum = Arrays.stream(new int[] { 1, 2, 3 }).map(i -> {
            log.info("doubling " + i);
            return i * 2;
        }).sum();
        log.info("sum is " + sum);

//            Stream without terminal operation, lazy
//            Stream with terminal operation
//            doubling 1
//            doubling 2
//            doubling 3

        Map<String, List<String>> people = new HashMap<>();
        people.put("John", Arrays.asList("555-1123", "555-3389"));
        people.put("Mary", Arrays.asList("555-2243", "555-5264"));
        people.put("Steve", Arrays.asList("555-6654", "555-3242"));
        log.info("may keys" + people.keySet().toString());
        log.info("map values" + people.values().toString());

        log.info("map");
        List<Stream<String>> phonesMap = people.values().stream().map(Collection::stream).collect(Collectors.toList());
        phonesMap.forEach(c -> System.out.println(c.toString()));

        log.info("flatMap only shows values");
        List<String> phones = people.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        phones.forEach(c -> System.out.println(c));

    }

    public void java8_features() {
        // https://www.baeldung.com/java-8-streams

        // Stream pipeline
        List<String> list = Arrays.asList("abc1", "abc2", "abc3", "aa12");
        long size = list.stream().skip(1).map(element -> element.substring(0, 3)).sorted().count();
        log.info("size " + size);

        // Lazy invocation

        Optional<String> stream = list.stream().filter(element -> {
            log.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            log.info("map() was called");
            return element.toUpperCase();
        }).sorted().findFirst();
        log.info(stream.orElse("is null"));
    }

    public void java11_features() {
        // https://www.baeldung.com/java-11-new-features

        List<String> sampleList = Arrays.asList("Java", "Kotlin", "", "pep", "Php");
        log.info("java11 features- sampleList : \"Java\", \"Kotlin\", \"\", \"pep\", \"Php\"");

        String resultString = sampleList.stream().map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        // assertThat(resultString).isEqualTo("JAVA, KOTLIN");
        log.info(resultString);
//
//        List<String> upperSampleList = sampleList.stream().map((@Nonnull var x) -> x.toUpperCase())
//                .collect(Collectors.toList());
//        upperSampleList.forEach(c -> log.info(c));

//        java.lang.IllegalStateException: Failed to execute CommandLineRunner if we add null
//   .class.
//        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1293) ~[spring-boot-3.0.5.jar:3.0.5]
//        at com.ccsw.tutorial.TutorialApplication2.main(TutorialApplication2.java:17) ~[classes/:na]
//    Caused by: java.lang.NullPointerException: Cannot invoke "String.toUpperCase()" because "x" is null

        List<String> upperSampleList = sampleList.stream().map((x) -> x.toUpperCase()).collect(Collectors.toList());
        upperSampleList.forEach(c -> log.info(c));

    }

}
