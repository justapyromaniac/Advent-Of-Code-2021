package com.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        //#region test readings
        /*
        ArrayList<Integer> readings = new ArrayList<Integer>(
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
        */
        //#endregion

        //#region Input reader
        BufferedReader reader = new BufferedReader(new FileReader("D:\\Jonas\\code projects\\Advent of code\\adventofcode\\src\\main\\java\\com\\adventofcode\\input.txt"));
        ArrayList<Integer> readings = new ArrayList<Integer>();
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
        //#endregion Input reader
        
        //#region solution part 1
        System.out.println();
        System.out.println("------ Day one: Sonar readings. Solution part 1 ------");
        int largerThanPrevious = 0;
        System.out.println(readings.get(0) + " (N/A - No previous measurement");
        for(int i = 1; i < readings.size(); i++) {
            if(readings.get(i-1) < readings.get(i)) {
                System.out.println(readings.get(i) + " (increased)");
                largerThanPrevious++;
            } else if(readings.get(i-1) > readings.get(i)) {
                System.out.println(readings.get(i) + " (decreased)");
            } else {
                System.out.println(readings.get(i) + " (no change)");
            }
        }
        System.out.println();
        System.out.println("Answer: " + largerThanPrevious);
        System.out.println();
        System.out.println("------------");
        System.out.println();
        //#endregion solution part 1
        
        //#region solution part 2
        System.out.println();
        System.out.println("------ Day one: Sonar readings. Solution part 2 ------");
        System.out.println();
        int largerThanPreviousWindow = 0;
        int firstWindow = readings.get(0) + readings.get(1) + readings.get(2);
        int currentWindow = 0;
        int previousWindow = firstWindow;
        System.out.println(firstWindow + " (N/A - No previous sum)");
        for(int i = 1; i < readings.size(); i++) {
            if(i+2 < readings.size()) {
                currentWindow = readings.get(i) + readings.get(i+1) + readings.get(i+2);
                if(previousWindow < currentWindow) {
                    System.out.println(currentWindow + " (increased)");
                    largerThanPreviousWindow++;
                } else if(previousWindow > currentWindow) {
                    System.out.println(currentWindow + " (decreased)");
                } else {
                    System.out.println(currentWindow + " (no change)");
                }
                previousWindow = currentWindow;
            }
        }
        System.out.println();
        System.out.println("Answer: " + largerThanPreviousWindow);
        System.out.println();
        System.out.println("------------");
        System.out.println();
        //#endregion solution part 2
    }
}
