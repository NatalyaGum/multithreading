package by.epam.task5.state.impl;

import by.epam.task5.entity.Port;
import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;
import by.epam.task5.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EndState implements ShipState {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void nextAction(Ship ship) throws PortException, IOException {
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " has completed work.");
        Port port = Port.getInstance();
        port.removeShip(ship);
    }
}
