package by.epam.task5.state;

import by.epam.task5.entity.Port;
import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EndState implements ShipState {
    private static Logger logger = LogManager.getLogger();
   /* private static EndState instance;

    public static EndState getInstance() {
        while (instance == null) {
            instance = new EndState();
        }
        return instance;
    }*/

  /*  @Override
    public void waitingState(Ship ship) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void receptionState(Ship ship) {
        throw new UnsupportedOperationException();
    }
*/
    @Override
    public void nextAction(Ship ship) throws PortException, IOException {
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " has completed work.");
        Port port = Port.getInstance();
        port.removeShip(ship);
    }
}
