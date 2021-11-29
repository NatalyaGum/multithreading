
import by.epam.task5.entity.Port;
import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Port port;
        try {
            port = Port.getInstance();
            ArrayList<Ship> ships=port.getShips();
          //  Iterator<Ship> iterator = port.getShips().iterator();
            ExecutorService service = Executors.newFixedThreadPool(port.getShips().size());
            ships.forEach(service::submit);
            service.shutdown();
            /*List<Future> futures = new ArrayList<>();
            while (iterator.hasNext()) {
                Ship ship = iterator.next();
               Future future = service.submit(ship);
               futures.add(future);*/
            //}
            service.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PortException e) {
            e.printStackTrace();
        }
    }
}
