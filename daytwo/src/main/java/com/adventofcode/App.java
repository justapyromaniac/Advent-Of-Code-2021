package com.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.adventofcode.classes.*;

public class App {
    public static void main( String[] args ) {
        SubmarineMKI santaDiverMKI = new SubmarineMKI();
        readInputs(santaDiverMKI);

        SubmarineMKII santaDiverMKII = new SubmarineMKII();
        readInputs(santaDiverMKII);

        System.out.println();
        System.out.println("--- Day 2: Dive! Solution part 1.---");
        System.out.println();
        System.out.println("Answer: " + santaDiverMKI.getDepth() * santaDiverMKI.getHorizontalPosition());
        System.out.println();
        System.out.println("------------");
        System.out.println();

        System.out.println();
        System.out.println("--- Day 2: Dive! Solution part 2.---");
        System.out.println();
        System.out.println("Answer: " + santaDiverMKII.getDepth() * santaDiverMKII.getHorizontalPosition());
        System.out.println();
        System.out.println("------------");
        System.out.println();
        
    }

    public static void readInputs(SubmarineMKI submarine) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\daytwo\\src\\main\\java\\com\\adventofcode\\input.txt"));
            String line = reader.readLine();
            while(line != null){
                submarine.addCommand(Command.parseCommand(line));
                line = reader.readLine();
            };
            reader.close();

        } catch (IOException e) {

            System.out.println("Failed to read file");
            ArrayList<String> readings = new ArrayList<String>(
                Arrays.asList(
                    "forward 5",
                    "down 5",
                    "forward 8",
                    "up 3",
                    "down 8",
                    "forward 2"
                )
            );

            for (String line : readings) {
                submarine.addCommand(Command.parseCommand(line));
            }
        }
    }
}
