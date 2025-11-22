package at.edu.c02.ledcontroller;

import org.json.JSONArray;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    JSONArray getGroupLeds() throws IOException;
    void groupStatus() throws IOException;
    void status(int id) throws IOException;
}
