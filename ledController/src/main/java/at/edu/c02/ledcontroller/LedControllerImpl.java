package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public void groupStatus() throws IOException {
        JSONObject json = apiService.getLights();

        for (Object obj : json.getJSONArray("lights")) {
            var led = (JSONObject) obj;
            int id = led.getInt("id");
            boolean on = led.getBoolean("on");
            String color = led.getString("color");

            System.out.println("LED " + id + " is currently " + (on ? "on" : "off") +
                    ". Color: " + color + ".");
        }
    }

    @Override
    public void status(int id) throws IOException {
        JSONObject led = apiService.getLight(id);

        boolean on = led.getBoolean("on");
        String color = led.getString("color");

        System.out.println("LED " + id + " is currently " + (on ? "on" : "off")
                + ". Color: " + color + ".");
    }
}
