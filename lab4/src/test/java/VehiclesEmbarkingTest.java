import com.nikitasutulov.lab4.passengers.*;
import com.nikitasutulov.lab4.vehicles.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VehiclesEmbarkingTest {

    @Test
    public void testDifferentProfessionsTaxiEmbarking() {
        Human human = new Human("Martin Luther");
        Firefighter firefighter = new Firefighter("Eric Firehater");
        PoliceOfficer policeOfficer = new PoliceOfficer("John Lawlover");
        List<Human> expectedHumans = List.of(new Human[]{human, firefighter, policeOfficer});

        Taxi<Human> taxi = new Taxi<>("Test taxi", 3);
        taxi.embark(human);
        taxi.embark(firefighter);
        taxi.embark(policeOfficer);

        assertArrayEquals(new List[]{taxi.getPassengers()}, new List[]{expectedHumans});
    }

    @Test
    public void testDifferentProfessionsBusEmbarking() {
        Human human = new Human("Martin Luther");
        Firefighter firefighter = new Firefighter("Eric Firehater");
        PoliceOfficer policeOfficer = new PoliceOfficer("John Lawlover");
        List<Human> expectedHumans = List.of(new Human[]{human, firefighter, policeOfficer});

        Bus<Human> bus = new Bus<>("Test bus", 5);
        bus.embark(human);
        bus.embark(firefighter);
        bus.embark(policeOfficer);

        assertArrayEquals(new List[]{bus.getPassengers()}, new List[]{expectedHumans});
    }

    @Test
    public void testTaxiOverloadThrows() {
        Human human = new Human("Martin Luther");
        Firefighter firefighter = new Firefighter("Eric Firehater");
        PoliceOfficer policeOfficer = new PoliceOfficer("John Lawlover");

        Taxi<Human> taxi = new Taxi<>("Test taxi", 2);
        taxi.embark(human);
        taxi.embark(firefighter);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> taxi.embark(policeOfficer));
        String expectedMessage = "All seats are occupied.";
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    public void testBusOverloadThrows() {
        Human human = new Human("Martin Luther");
        Firefighter firefighter = new Firefighter("Eric Firehater");
        PoliceOfficer policeOfficer = new PoliceOfficer("John Lawlover");

        Bus<Human> bus = new Bus<>("Test bus", 2);
        bus.embark(human);
        bus.embark(firefighter);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bus.embark(policeOfficer));
        String expectedMessage = "All seats are occupied.";
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    public void testPoliceCarOverloadThrows() {

        PoliceOfficer policeOfficer1 = new PoliceOfficer("John Lawlover");
        PoliceOfficer policeOfficer2 = new PoliceOfficer("Matthew Crimehater");
        PoliceOfficer policeOfficer3 = new PoliceOfficer("Mark the Righteous");

        PoliceCar<PoliceOfficer> policeCar = new PoliceCar<>("Test police car", 2);
        policeCar.embark(policeOfficer1);
        policeCar.embark(policeOfficer2);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> policeCar.embark(policeOfficer3));
        String expectedMessage = "All seats are occupied.";
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    public void testFireTruckOverloadThrows() {

        Firefighter fireFighter1 = new Firefighter("John Waterlover");
        Firefighter firefighter2 = new Firefighter("Matthew Flamehater");
        Firefighter firefighter3 = new Firefighter("Mark the Brave");

        FireTruck<Firefighter> fireTruck = new FireTruck<>("Test fire truck", 2);
        fireTruck.embark(fireFighter1);
        fireTruck.embark(firefighter2);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fireTruck.embark(firefighter3));
        String expectedMessage = "All seats are occupied.";
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    public void testDisembarkPassengerNotInVehicleThrows() {
        Human human = new Human("John");
        Firefighter firefighter = new Firefighter("Andrew");
        PoliceOfficer policeOfficer = new PoliceOfficer("George");

        Taxi<Human> taxi = new Taxi<>("Test Taxi", 4);
        Bus<Human> bus = new Bus<>("Test Bus", 10);
        FireTruck<Firefighter> fireTruck = new FireTruck<>("Test fire truck", 2);
        PoliceCar<PoliceOfficer> policeCar = new PoliceCar<>("Test police car", 3);

        String expectedMessage = "Passenger is not on board.";

        IllegalArgumentException exceptionTaxi = assertThrows(IllegalArgumentException.class, () -> taxi.disembark(human));
        assertEquals(expectedMessage, exceptionTaxi.getMessage());

        IllegalArgumentException exceptionBus = assertThrows(IllegalArgumentException.class, () -> bus.disembark(human));
        assertEquals(expectedMessage, exceptionBus.getMessage());

        IllegalArgumentException exceptionFireTruck = assertThrows(IllegalArgumentException.class, () -> fireTruck.disembark(firefighter));
        assertEquals(expectedMessage, exceptionFireTruck.getMessage());

        IllegalArgumentException exceptionPoliceCar = assertThrows(IllegalArgumentException.class, () -> policeCar.disembark(policeOfficer));
        assertEquals(expectedMessage, exceptionPoliceCar.getMessage());
    }

    @Test
    public void testPassengerAlreadyEmbarkedThrows() {
        Human human = new Human("John");
        Firefighter firefighter = new Firefighter("Andrew");
        PoliceOfficer policeOfficer = new PoliceOfficer("George");

        Taxi<Human> taxi = new Taxi<>("Test Taxi", 4);
        Bus<Human> bus = new Bus<>("Test Bus", 10);
        FireTruck<Firefighter> fireTruck1 = new FireTruck<>("Test fire truck 1", 2);
        FireTruck<Firefighter> fireTruck2 = new FireTruck<>("Test fire truck 2", 2);
        PoliceCar<PoliceOfficer> policeCar1 = new PoliceCar<>("Test police car 1", 3);
        PoliceCar<PoliceOfficer> policeCar2 = new PoliceCar<>("Test police car 2", 3);

        String expectedMessage = "Passenger is already embarked.";

        taxi.embark(human);
        IllegalArgumentException exceptionAlreadyInTaxi = assertThrows(IllegalArgumentException.class, () -> bus.embark(human));
        assertEquals(expectedMessage, exceptionAlreadyInTaxi.getMessage());
        taxi.disembark(human);

        bus.embark(human);
        IllegalArgumentException exceptionAlreadyInBus = assertThrows(IllegalArgumentException.class, () -> taxi.embark(human));
        assertEquals(expectedMessage, exceptionAlreadyInBus.getMessage());
        bus.disembark(human);

        fireTruck1.embark(firefighter);
        IllegalArgumentException exceptionAlreadyInFireTruck = assertThrows(IllegalArgumentException.class, () -> fireTruck2.embark(firefighter));
        assertEquals(expectedMessage, exceptionAlreadyInFireTruck.getMessage());
        fireTruck1.disembark(firefighter);

        policeCar1.embark(policeOfficer);
        IllegalArgumentException exceptionAlreadyInPoliceCar = assertThrows(IllegalArgumentException.class, () -> policeCar2.embark(policeOfficer));
        assertEquals(expectedMessage, exceptionAlreadyInPoliceCar.getMessage());
        policeCar1.disembark(policeOfficer);
    }
}
