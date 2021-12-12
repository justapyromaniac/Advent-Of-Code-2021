package com.adventofcode.classes;

public class SubmarineMKII extends SubmarineMKI{

    private int aim;

    public SubmarineMKII() {
        super();
        this.aim = 0;
    }

    public int getAim() {
        return aim;
    }

    @Override
    public void executeCommand(Command command) {
        System.out.println(command);
        switch (command.getDirection()) {
            case "forward":
                this.horizontalPosition += command.getUnits();
                this.depth += this.aim * command.getUnits();
                System.out.println("Current depth: " + this.depth);
                System.out.println("Current horizontal position: " + this.horizontalPosition);
                break;
            case "down":
                this.aim += command.getUnits();
                System.out.println("Current aim: " + this.aim);
                break;
            case "up":
                this.aim -= command.getUnits();
                System.out.println("Current aim: " + this.aim);
                break;
            default:
                System.out.println("Unhandled command");
                break;
        }
    }
}
