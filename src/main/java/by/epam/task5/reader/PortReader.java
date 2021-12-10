package by.epam.task5.reader;

import by.epam.task5.exception.PortException;

public interface PortReader {
     int[] readFromFile(String filename) throws PortException;
}
