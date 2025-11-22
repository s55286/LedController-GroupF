package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class LedControllerE2EIT {

    @Test
    public void testSetAndGetLightE2E() throws Exception {
        ApiService api = new ApiServiceImpl();
        LedController controller = new LedControllerImpl(api);

        int ledId = 1;
        String newColor = "#0f0";
        boolean newStatus = true;

        api.setLight(ledId, newColor, newStatus);

        JSONObject response = api.getLight(ledId);
        JSONArray lights = response.getJSONArray("lights");
        JSONObject led = lights.getJSONObject(0);

        boolean on = led.getBoolean("on");
        String color = led.getString("color");

        assertEquals("LED status did not match!", newStatus, on);
        assertEquals("LED color did not match!", newColor, color);
    }
}
