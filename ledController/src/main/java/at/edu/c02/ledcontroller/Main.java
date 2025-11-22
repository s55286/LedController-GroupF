package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());

        String input = "";
        String idStr = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!input.equalsIgnoreCase("exit")) {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'groupstatus' to show the status of all LEDs in the group");
            System.out.println("Enter 'status' to show the status of a single LED");
            System.out.println("Enter 'setled' to modify a LED");
            System.out.println("Enter 'runningled' to show spinning LEDs");
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();

            if (input.equalsIgnoreCase("demo")) {
                ledController.demo();

            } else if (input.equalsIgnoreCase("groupstatus")) {
                ledController.groupStatus();

            } else if (input.equalsIgnoreCase("status")) {
                System.out.println("Please specify LED ID:");
                idStr = reader.readLine();

                try {
                    int id = Integer.parseInt(idStr);
                    ledController.status(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID");
                }

            } else if (input.equalsIgnoreCase("setled")) {
                System.out.println("Please specify LED ID:");
                int id = Integer.parseInt(reader.readLine());

                System.out.println("Please specify color:");
                String color = reader.readLine();

                System.out.println("Please specify status (true/false):");
                boolean status = Boolean.parseBoolean(reader.readLine());

                try {
                    ledController.set(id, color, status);
                    System.out.println("LED updated.");
                } catch (Exception e) {
                    System.out.println("Error updating LED: " + e.getMessage());
                }
            } else if (input.equalsIgnoreCase("runningled")) {
                System.out.println("Which color?");
                String color = reader.readLine();
                System.out.println("How many turns?");
                int turns = Integer.parseInt(reader.readLine());
                System.out.println("Starting SpinningLed effect...");
                ledController.runningLight(color, turns);
                System.out.println("Finished SpinningLed effect...");
            }
        }
    }
}

