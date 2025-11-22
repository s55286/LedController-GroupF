package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;
    public static final int[] GROUP_LED_IDS = { 36, 37, 38, 39,40,41,42,43 };

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
        JSONObject response = apiService.getLight(id);
        JSONArray lights = response.getJSONArray("lights");
        JSONObject led = lights.getJSONObject(0);
        boolean on = led.getBoolean("on");
        String color = led.getString("color");

        System.out.println("LED " + id + " is currently " + (on ? "on" : "off")
                + ". Color: " + color + ".");
    }

    @Override
    public void set(int id, String color, boolean status) throws IOException {
        apiService.setLight(id, color, status);
        System.out.println("updated");
    }

    @Override
    public JSONArray getGroupLeds() throws IOException {
        JSONObject response = apiService.getLights();
        System.out.println(response);
        JSONArray all = response.getJSONArray("lights");

        JSONArray group = new JSONArray();

        for (int i = 0; i < all.length(); i++) {
            JSONObject led = all.getJSONObject(i);
            String groupName = led.getJSONObject("groupByGroup").getString("name");

            if (groupName.equals("F")) {
                group.put(led);
            }
        }

        return group;
    }

    @Override
    public void turnOffAllLeds() throws IOException{
        for (int id : GROUP_LED_IDS) {
            set(id, "#000000", false);
        }
    }
}
