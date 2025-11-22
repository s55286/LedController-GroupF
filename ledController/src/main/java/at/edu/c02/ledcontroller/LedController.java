package at.edu.c02.ledcontroller;

import org.json.JSONArray;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    JSONArray getGroupLeds() throws IOException;
    void groupStatus() throws IOException;
    void status(int id) throws IOException;
    void turnOffAllLeds() throws IOException;
    void set(int id, String color, boolean status) throws IOException;
    void runningLight(String color, int turns) throws IOException;
}
