package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void testGetGroupLeds() throws Exception {

        ApiServiceImpl apiService = mock(ApiServiceImpl.class);
        JSONObject fake = new JSONObject("""
        { "lights": [ {"id": 13, "color":"#fff", "on":true} ] }
    """);

        when(apiService.getLights()).thenReturn(fake);

        LedController controller = new LedControllerImpl(apiService);
        JSONArray result = controller.getGroupLeds();

        verify(apiService).getLights();
        assertEquals(1, result.length());
    }
}
