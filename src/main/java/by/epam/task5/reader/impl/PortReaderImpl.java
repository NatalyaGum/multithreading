package by.epam.task5.reader.impl;

import by.epam.task5.exception.PortException;
import by.epam.task5.reader.PortReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class PortReaderImpl implements PortReader {
    private static Logger logger = LogManager.getLogger();

    public int[] readFromFile(String filename) throws PortException {
        Properties properties = new Properties();
        try {
            String filePath = URLDecoder.decode(Objects.requireNonNull(getClass().getClassLoader().getResource(filename)).getPath(),
                    StandardCharsets.UTF_8);
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } catch (IOException | NullPointerException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new PortException("IOException in read from file method with path " + filename);
        }
        int[] value = new int[4];
        value[0] = Integer.parseInt(properties.getProperty("countOfPiers"));
        value[1] = Integer.parseInt(properties.getProperty("countOfShips"));
        value[2] = Integer.parseInt(properties.getProperty("containerCapacity"));
        value[3] = Integer.parseInt(properties.getProperty("containerNumber"));
        return value;
    }

}