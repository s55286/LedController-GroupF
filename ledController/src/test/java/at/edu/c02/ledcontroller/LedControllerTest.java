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
        {"lights":[{"id":1,"color":"#0f0","on":true,"groupByGroup":{"name":"X"}}, {"id":2,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":3,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":4,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":5,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":6,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":7,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":8,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":9,"color":"#f00","on":false,"groupByGroup":{"name":"X"}}, {"id":10,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":11,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":12,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":13,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":14,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":15,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":16,"color":"#000000","on":false,"groupByGroup":{"name":"D"}}, {"id":20,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":21,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":22,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":23,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":24,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":25,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":26,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":27,"color":"#000","on":false,"groupByGroup":{"name":"B"}}, {"id":28,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":29,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":30,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":31,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":32,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":33,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":34,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":35,"color":"#FF5733","on":false,"groupByGroup":{"name":"Y"}}, {"id":36,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":37,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":38,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":39,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":40,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":41,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":42,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":43,"color":"#f00","on":false,"groupByGroup":{"name":"F"}}, {"id":46,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":47,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":48,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":49,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":50,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":51,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":52,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":53,"color":"red","on":false,"groupByGroup":{"name":"G"}}, {"id":54,"color":"#f00","on":false,"groupByGroup":{"name":"A"}}, {"id":55,"color":"#03a5fc","on":false,"groupByGroup":{"name":"A"}}, {"id":56,"color":"#f00","on":false,"groupByGroup":{"name":"A"}}, {"id":57,"color":"#f00","on":false,"groupByGroup":{"name":"A"}}, {"id":58,"color":"#f00","on":false,"groupByGroup":{"name":"A"}}, {"id":59,"color":"#f00","on":false,"groupByGroup":{"name":"A"}}, {"id":60,"color":"#0f0","on":false,"groupByGroup":{"name":"A"}}, {"id":61,"color":"#f00","on":false,"groupByGroup":{"name":"A"}}]}
    """);

        when(apiService.getLights()).thenReturn(fake);

        LedController controller = new LedControllerImpl(apiService);
        JSONArray result = controller.getGroupLeds();

        verify(apiService).getLights();
    }
}
