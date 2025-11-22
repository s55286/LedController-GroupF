package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    void groupStatus() throws IOException;
    void status(int id) throws IOException;
}
