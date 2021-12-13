package com.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

class App {
    public static void main( String[] args ) {
        ArrayList<ArrayList<Integer>> binaryMatrix = createMatrix(readInputs());

        HashMap<String, String> gammaAndEpsilon = calculateGammaAndEpsilon(binaryMatrix);
        int powerConsumption = calculatePowerConsumption(gammaAndEpsilon.get("gamma"), gammaAndEpsilon.get("epsilon"));

        HashMap<String, String> lifeSupportRatings = calculateLifeSupportRatings(binaryMatrix);
        int lifeSupportRating = calculateLifeSupportRating(lifeSupportRatings.get("Oxygen generator rating"), lifeSupportRatings.get("CO2 scrubber rating"));

        System.out.println();
        System.out.println("--- Day 3: Binary Diagnostic Solution part 1.---");
        System.out.println();
        System.out.println("Answer: " + powerConsumption);
        System.out.println();
        System.out.println("------------");
        System.out.println();

        System.out.println();
        System.out.println("--- Day 3: Binary Diagnostic Solution part 2.---");
        System.out.println();
        System.out.println("Answer: " + lifeSupportRating);
        System.out.println();
        System.out.println("------------");
        System.out.println();
    }

    public static ArrayList<String> readInputs() {

        ArrayList<String> readings = new ArrayList<String>();

        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\daythree\\src\\main\\java\\com\\adventofcode\\input.txt"));
            String line = reader.readLine();
            while(line != null){
                readings.add(line);
                line = reader.readLine();
            };
            reader.close();
            

        } catch (IOException e) {

            System.out.println("Failed to read file");
            readings = new ArrayList<String>(
                Arrays.asList(
                    "00100",
                    "11110",
                    "10110",
                    "10111",
                    "10101",
                    "01111",
                    "00111",
                    "11100",
                    "10000",
                    "11001",
                    "00010",
                    "01010"
                )
            );
        }

        return readings;
    }

    public static ArrayList<ArrayList<Integer>> createMatrix(ArrayList<String> array) {

        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>(array.size());
        ArrayList<Integer> bitRow;

        for(int i = 0; i < array.size(); i++) {
            String line = array.get(i);
            bitRow = new ArrayList<Integer>(line.length());
            for(int j = 0; j < line.length(); j++) {
                bitRow.add(Integer.parseInt(Character.toString(line.charAt(j))));
            }
            output.add(bitRow);
        }

        System.out.println(output);

        return output;
    }

    //swaps rows and columns because the assignment requires vertical reading otherwise
    public static ArrayList<ArrayList<Integer>> transposeMatrix(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < matrix.get(0).size(); i++) {
            ArrayList<Integer> col = new ArrayList<Integer>();
            for (ArrayList<Integer> row : matrix) {
                col.add(row.get(i));
            }
            output.add(col);
        }

        return output;
    }

    //I will note that if the amount of 0's and 1's in ANY row is EQUAL this part of the problem is impossible.
    //I'm writing it down because I thought I had to solve that as well.
    public static HashMap<String, String> calculateGammaAndEpsilon(ArrayList<ArrayList<Integer>> binaryMatrix) {
        ArrayList<ArrayList<Integer>> transposedMatrix = transposeMatrix(binaryMatrix);
        HashMap<String, String> output = new HashMap<String, String>();
        output.put("gamma", "");
        output.put("epsilon", "");
        int intermediateCalculation;

        for (int i = 0; i < transposedMatrix.size(); i++) {
            intermediateCalculation = 0;
            for (int j = 0; j < transposedMatrix.get(0).size(); j++) {
                intermediateCalculation += transposedMatrix.get(i).get(j);
            }

            if(intermediateCalculation > Math.ceil((double)transposedMatrix.get(0).size() / 2)) {
                output.put("gamma", output.get("gamma") + 1);
                output.put("epsilon", output.get("epsilon") + 0);
            } else {
                output.put("gamma", output.get("gamma") + 0);
                output.put("epsilon", output.get("epsilon") + 1);
            }
        }

        return output;
    }

    public static int calculatePowerConsumption(String gamma, String epsilon) {
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }

    public static HashMap<String, Integer> determineMostAndLeastCommonBit(ArrayList<Integer> binaryArray) {
        HashMap<String, Integer> output = new HashMap<String, Integer>();
        int intermediateCalculation = 0;
        for (int i : binaryArray) {
            intermediateCalculation += i;
        }

        if(intermediateCalculation >= Math.ceil((double)binaryArray.size() / 2)) {
            output.put("Most common bit", 1);
            output.put("Least common bit", 0);
        } else {
            output.put("Most common bit", 0);
            output.put("Least common bit", 1);
        }

        return output;
    }

    public static HashMap<String, String> calculateLifeSupportRatings(ArrayList<ArrayList<Integer>> binaryMatrix) {
        HashMap<String, String> output = new HashMap<String, String>();
        ArrayList<ArrayList<Integer>> oxygenGeneratorCopy = (ArrayList<ArrayList<Integer>>)binaryMatrix.clone();
        ArrayList<ArrayList<Integer>> co2ScrubberCopy = (ArrayList<ArrayList<Integer>>)binaryMatrix.clone();
        int i = 0;
        int j = 0;
        HashMap<String, Integer> consideredBitsOxygenGenerator = determineMostAndLeastCommonBit(transposeMatrix(binaryMatrix).get(i));
        HashMap<String, Integer> consideredBitsCo2Scrubber = determineMostAndLeastCommonBit(transposeMatrix(binaryMatrix).get(j));

        while(oxygenGeneratorCopy.size() > 1){

            for(Iterator<ArrayList<Integer>> iterator = oxygenGeneratorCopy.iterator(); iterator.hasNext(); ) {
                ArrayList<Integer> value = iterator.next(); 
                if(value.get(i) != consideredBitsOxygenGenerator.get("Most common bit")) {
                    iterator.remove();
                }
            }

            i++;
            if(oxygenGeneratorCopy.size() > 1) {
                consideredBitsOxygenGenerator = determineMostAndLeastCommonBit(transposeMatrix(oxygenGeneratorCopy).get(i));
            } else {
                output.put("Oxygen generator rating", oxygenGeneratorCopy.get(0).toString().replaceAll("\\[|\\]", "").replaceAll(", ", ""));
            }
        }

        while(co2ScrubberCopy.size() > 1){

            for (Iterator<ArrayList<Integer>> iterator = co2ScrubberCopy.iterator(); iterator.hasNext(); ) {
                ArrayList<Integer> value = iterator.next(); 
                if(value.get(j) != consideredBitsCo2Scrubber.get("Least common bit")) {
                    iterator.remove();
                }
            }
            j++;
            if(co2ScrubberCopy.size() > 1) {
                consideredBitsCo2Scrubber = determineMostAndLeastCommonBit(transposeMatrix(co2ScrubberCopy).get(j));
            } else {
                output.put("CO2 scrubber rating", co2ScrubberCopy.get(0).toString().replaceAll("\\[|\\]", "").replaceAll(", ", ""));
            }
        }

        return output;
    }

    public static int calculateLifeSupportRating(String oxygenGeneratorRating, String co2ScrubberRating) {
        return Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2);
    }
}
