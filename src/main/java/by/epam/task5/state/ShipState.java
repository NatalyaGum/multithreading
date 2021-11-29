package by.epam.task5.state;

import by.epam.task5.entity.Ship;
import by.epam.task5.exception.PortException;

import java.io.IOException;

public interface ShipState {
    void nextAction(Ship ship) throws PortException, IOException;

}