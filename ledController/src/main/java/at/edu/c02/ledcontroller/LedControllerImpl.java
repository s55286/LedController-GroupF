package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

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
    public void runningLight(String color, int turns) throws IOException {
        turnOffAllLeds();
        for(int i = 0; i < turns; i++){
            for (int id : GROUP_LED_IDS) {
                set(id, color, true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   return;
                }
                set(id, "#000000", false);
            }
        }
        turnOffAllLeds();
    }

    @Override
    public void spinningWheel(int steps) throws IOException {
        // 1. aktuellen Zustand auslesen
        Map<Integer, String> currentColors = new HashMap<>();
        JSONArray leds = getGroupLeds();
        for (int i = 0; i < leds.length(); i++) {
            JSONObject led = leds.getJSONObject(i);
            int id = led.getInt("id");
            String color = led.getString("color");
            currentColors.put(id, color);
        }
        List<Integer> sortedLeds = new ArrayList<>(currentColors.keySet());
        Collections.sort(sortedLeds);

        // 3. Spinning Effekt
        for (int step = 0; step < steps; step++) {
            // Farben um einen Platz nach rechts verschieben
            String lastColor = currentColors.get(sortedLeds.get(sortedLeds.size() - 1));
            for (int i = sortedLeds.size() - 1; i > 0; i--) {
                int id = sortedLeds.get(i);
                int prevId = sortedLeds.get(i - 1);
                currentColors.put(id, currentColors.get(prevId));
            }
            currentColors.put(sortedLeds.get(0), lastColor);

            // LEDs aktualisieren
            for (int id : sortedLeds) {
                set(id, currentColors.get(id), true);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
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
