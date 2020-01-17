package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListStream {
    public static void main(String[] args) {
        List<String> list=Arrays.asList("java","thread","concurrency","scala");
        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
