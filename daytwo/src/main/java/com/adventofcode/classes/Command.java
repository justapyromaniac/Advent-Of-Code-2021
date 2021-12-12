package com.adventofcode.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Command {
    final static ArrayList<String> validCommands = new ArrayList<String>(
        Arrays.asList(
            "forward",
            "down",
            "up"  
        )
    );

    private String direction;
    private int units;
    
    public Command(String direction, int units) {
        this.direction = direction;
        this.units = units;
    }

    public String getDirection() {
        return direction;
    }

    public int getUnits() {
        return units;
    }

    public static Command parseCommand(String input) {
        Command output = null;

        String[] splitString = input.split(" ");
        if(validCommands.contains(splitString[0])) {
            output = new Command(splitString[0], Integer.parseInt(splitString[1]));
        } else {
            System.out.println("Invalid command");
        }

        return output;
    }

    @Override
    public String toString() {
        return this.direction + " " + this.units;
    }
}
