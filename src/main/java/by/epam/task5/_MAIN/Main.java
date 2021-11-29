package by.epam.task5._MAIN;

import by.epam.task5.entity.Port;
import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Port port;
        try {
            port = Port.getInstance();
            ArrayList<Ship> ships = port.getShips();
            ExecutorService service = Executors.newFixedThreadPool(port.getShips().size());
            ships.forEach(service::submit);
            service.shutdown();
            service.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PortException e) {
            e.printStackTrace();
        }
    }
}
