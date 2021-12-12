package com.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main( String[] args ) {    

        final int windowSize = 3;   

        System.out.println();
        System.out.println("------ Day one: Sonar readings. Solution part 1 ------");
        System.out.println();
        System.out.println("Answer: " + calculateDepthIncreases());
        System.out.println();
        System.out.println("------------");
        System.out.println();

        System.out.println();
        System.out.println("------ Day one: Sonar readings. Solution part 2 ------");
        System.out.println();
        System.out.println();
        System.out.println("Answer: " + calculateWindowedDepthIncreases(windowSize));
        System.out.println();
        System.out.println("------------");
        System.out.println();
    }

    public static ArrayList<Integer> readInputs() {

        ArrayList<Integer> readings = new ArrayList<Integer>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\dayone\\src\\main\\java\\com\\adventofcode\\input.txt"));
            boolean halt = false;
            String line = "";
            do{
                line = reader.readLine();
                try {
                    int parsedReading = Integer.parseInt(line);
                    readings.add(parsedReading);
                } catch (NumberFormatException e) {
                    halt = true;
                    reader.close();
                }
            }while(!halt);

        } catch (IOException e) {

            System.out.println("Failed to read file");
            readings = new ArrayList<Integer>(
                Arrays.asList(
                    199,
                    200,
                    208,
                    210,
                    200,
                    207,
                    240,
                    269,
                    260,  
                    263
                )
            );
        }

        return readings;
    }

    public static int calculateDepthIncreases() {

        int output = 0;
        ArrayList<Integer> readings = readInputs();

        System.out.println(readings.get(0) + " (N/A - No previous measurement");
        for(int i = 1; i < readings.size(); i++) {

            if(readings.get(i-1) < readings.get(i)) {
                System.out.println(readings.get(i) + " (increased)");
                output++;
            } else if(readings.get(i-1) > readings.get(i)) {
                System.out.println(readings.get(i) + " (decreased)");
            } else {
                System.out.println(readings.get(i) + " (no change)");
            }
        }

        return output;
    }

    public static int calculateWindowedReading(int windowsize, int startingIndex) {

        int output = 0;
        ArrayList<Integer> readings = readInputs();
        
        for(int i = windowsize; i > 0; i--) {
            output += readings.get(startingIndex + (windowsize-i));
        }

        return output;
    }

    public static int calculateWindowedDepthIncreases(int windowSize) {

        int output = 0;
        int currentWindow = 0;
        int firstWindow = calculateWindowedReading(windowSize, 0);
        int previousWindow = firstWindow;
        ArrayList<Integer> readings = readInputs();

        System.out.println(firstWindow + " (N/A - No previous sum)");
        for(int i = 1; i < readings.size(); i++) {

            if(i+2 < readings.size()) {
                currentWindow = calculateWindowedReading(windowSize, i);
                if(previousWindow < currentWindow) {
                    System.out.println(currentWindow + " (increased)");
                    output++;
                } else if(previousWindow > currentWindow) {
                    System.out.println(currentWindow + " (decreased)");
                } else {
                    System.out.println(currentWindow + " (no change)");
                }

                previousWindow = currentWindow;
            }
        }

        return output;
    }
}
