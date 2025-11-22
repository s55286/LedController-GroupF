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
}
