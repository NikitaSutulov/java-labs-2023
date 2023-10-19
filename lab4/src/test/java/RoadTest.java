import com.nikitasutulov.lab4.passengers.Firefighter;
import com.nikitasutulov.lab4.passengers.Human;
import com.nikitasutulov.lab4.passengers.PoliceOfficer;
import com.nikitasutulov.lab4.roads.Road;
import com.nikitasutulov.lab4.vehicles.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoadTest {

    @Test
    public void testAddVehicleToRoad() {
        Bus<Human> bus = new Bus<Human>("Test bus", 4);
        Road road = new Road();
        road.addVehicleToRoad(bus);
        assertArrayEquals(new Vehicle[]{bus}, road.vehiclesInRoad.toArray());
    }

    @Test
    public void testRemoveVehicleFromRoad() {
        Taxi<Human> taxi = new Taxi<Human>("Test bus", 4);
        Road road = new Road();
        road.addVehicleToRoad(taxi);
        road.removeVehicleFromRoad(taxi);
        assertArrayEquals(new Vehicle[]{}, road.vehiclesInRoad.toArray());
    }

    @Test
    public void testAddingAlreadyAddedVehicleThrows() {
        Bus<Human> bus = new Bus<Human>("Test bus", 4);
        Road road = new Road();
        road.addVehicleToRoad(bus);
        String expectedMessage = "Vehicle is already on a road.";

        IllegalArgumentException exceptionAlreadyAdded = assertThrows(IllegalArgumentException.class, () -> road.addVehicleToRoad(bus));
        assertEquals(expectedMessage, exceptionAlreadyAdded.getMessage());
    }

    @Test
    public void testRemovingAlreadyRemovedVehicleThrows() {
        Taxi<Human> taxi = new Taxi<Human>("Test bus", 4);
        Road road = new Road();
        road.addVehicleToRoad(taxi);
        road.removeVehicleFromRoad(taxi);
        String expectedMessage = "Vehicle is not on the road.";

        IllegalArgumentException exceptionAlreadyAdded = assertThrows(IllegalArgumentException.class, () -> road.removeVehicleFromRoad(taxi));
        assertEquals(expectedMessage, exceptionAlreadyAdded.getMessage());
    }

    @Test
    public void testEmptyRoadHasNoHumans() {
        Road road = new Road();
        assertEquals(0, road.getCountOfHumans());
    }

    @Test
    public void testNumberOfHumansOnRoadIncreasesWithAddingVehiclesWithHumans() {
        Road road = new Road();
        Taxi<Human> taxi = new Taxi<Human>("Test taxi", 3);
        Bus<Human> bus = new Bus<Human>("Test bus", 10);
        taxi.embark(new Human("John"));
        taxi.embark(new Human("Mary"));
        bus.embark(new Human("James"));
        road.addVehicleToRoad(taxi);
        assertEquals(2, road.getCountOfHumans());
        road.addVehicleToRoad(bus);
        assertEquals(3, road.getCountOfHumans());
    }

    @Test
    public void testNumberOfHumansOnRoadDecreasesWithRemovingVehiclesWithHumans() {
        Road road = new Road();
        FireTruck<Firefighter> fireTruck = new FireTruck<Firefighter>("Test fire truck", 2);
        PoliceCar<PoliceOfficer> policeCar = new PoliceCar<PoliceOfficer>("Test police car", 3);
        fireTruck.embark(new Firefighter("Stan"));
        fireTruck.embark(new Firefighter("Ashley"));
        policeCar.embark(new PoliceOfficer("Harry"));
        road.addVehicleToRoad(fireTruck);
        road.addVehicleToRoad(policeCar);
        assertEquals(3, road.getCountOfHumans());
        road.removeVehicleFromRoad(policeCar);
        assertEquals(2, road.getCountOfHumans());
    }
}
