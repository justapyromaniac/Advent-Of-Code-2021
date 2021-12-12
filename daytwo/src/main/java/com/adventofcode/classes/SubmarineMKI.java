package com.adventofcode.classes;

import java.util.ArrayList;

public class SubmarineMKI {

    protected int depth;
    protected int horizontalPosition;
    private ArrayList<Command> commands;

    public SubmarineMKI() {
        depth = 0;
        horizontalPosition = 0;
        commands = new ArrayList<Command>();
    }

    public int getDepth() {
        return depth;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public boolean addCommand(Command command) {
        if(command != null) {
            commands.add(command);
            executeCommand(command);
            return true;
        } else {
            return false;
        }
    }

    public void executeCommand(Command command) {
        System.out.println(command);
        switch (command.getDirection()) {
            case "forward":
                this.horizontalPosition += command.getUnits();
                System.out.println("Current horizontal position: " + this.horizontalPosition);
                break;
            case "down":
                this.depth += command.getUnits();
                System.out.println("Current depth: " + this.depth);
                break;
            case "up":
                this.depth -= command.getUnits();
                System.out.println("Current depth: " + this.depth);
                break;
            default:
                System.out.println("Unhandled command");
                break;
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
