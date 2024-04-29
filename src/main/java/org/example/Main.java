package org.example;

import java.util.List;

public class Main {



    public static void main(String[] args) {
       new HzProgram(List.of(new Frequency("E1", 41.2f), new Frequency("C3", 130.8f), new Frequency("G3", 196f), new Frequency("A4", 440f), new Frequency("D5", 597.33f)));
    }
}