package by.epam.task5.state;

import by.epam.task5.entity.Port;
import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoadingUnloadingState implements ShipState {
    private static Logger logger = LogManager.getLogger();
    /*private static ReceptionState instance;

    public static ReceptionState getInstance() {
        while (instance == null) {
            instance = new ReceptionState();
        }
        return instance;
    }*/

 /*   @Override
    public void waitingState(Ship ship) {
        throw new UnsupportedOperationException();
    }*/

    @Override
    public void nextAction(Ship ship) throws PortException, IOException {
        Port port = Port.getInstance();
        if (ship.isUnloaded()) {
            logger.log(Level.INFO, "Ship " + ship.getShipId() + " is unloading.");
            port.unload(ship);
        }
        if (ship.isLoaded()) {
            logger.log(Level.INFO, "Ship " + ship.getShipId() + " is loading.");
            port.load(ship);
        }
        ship.setShipState(new EndState());//.getInstance());
    }

   /* @Override
    public void endState(Ship ship) {
        throw new UnsupportedOperationException();
    }*/
}
