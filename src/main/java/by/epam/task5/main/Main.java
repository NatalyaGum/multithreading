package by.epam.task5.main;

import by.epam.task5.entity.Port;
import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Port port;
        try {
            port = Port.getInstance();
            ArrayList<Ship> ships = port.getShips();
            ExecutorService service = Executors.newFixedThreadPool(port.getShips().size());
            ships.forEach(service::submit);
            service.shutdown();
            service.shutdown();
        } catch (PortException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
