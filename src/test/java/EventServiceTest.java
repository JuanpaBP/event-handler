import entity.Event;
import org.mockito.Mockito;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.EventService;

import java.time.LocalDate;

import static org.testng.AssertJUnit.assertTrue;

public class EventServiceTest {

    private EventService eventService;

    private Event event;
    private LocalDate startDate;
    private LocalDate endDate;
    @BeforeClass
    public void setup() {
        startDate = LocalDate.parse("09/09/2024");
        endDate = LocalDate.parse("12/09/2024");
        event = new Event(1,"loolapaloza", "córdoba", "evento", startDate, endDate, 999.0);
    }

    @AfterClass
    public void tearDown() {

    }

   /* @Test
    public void givenNumber_whenEven_thenTrue() {
        assertTrue(number % 2 == 0);
    }*/

    /*@Test
    public void createEventTest(){
        Mockito.when()
    }*/

}
