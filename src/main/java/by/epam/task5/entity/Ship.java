package by.epam.task5.entity;

import by.epam.task5.exception.PortException;
import by.epam.task5.state.ShipState;
import by.epam.task5.state.impl.WaitingState;
import by.epam.task5.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Ship implements Runnable {
    private static Logger logger = LogManager.getLogger();
    private int shipId;
    private int containerCapacity;
    private int containerNumber;
    private ShipState shipState;
    private boolean isLoaded;
    private boolean isUnloaded;

    public Ship() {
        this.shipId = IdGenerator.generateId();
        this.containerCapacity = (int) (900 * Math.random()) + 100;
        this.containerNumber = (int) (this.containerCapacity * Math.random());
        if (Math.random() > 0.5) {
            isLoaded = true;
            isUnloaded = Math.random() > 0.5;
        } else {
            isLoaded = false;
            isUnloaded = true;
        }
    }

    public int getShipId() {
        return shipId;
    }

    public int getContainerCapacity() {
        return containerCapacity;
    }

    public int getContainerNumber() {
        return containerNumber;
    }

    public int getDifference() {
        return containerCapacity - containerNumber;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isUnloaded() {
        return isUnloaded;
    }


    public void setContainerNumber(int containerNumber) {
        this.containerNumber = containerNumber;
    }

    public void setShipState(ShipState shipState) {
        this.shipState = shipState;
    }

    @Override
    public void run() {
        Port port;
        Semaphore semaphore;
        try {
            port = Port.getInstance();
            semaphore = port.getSemaphore();
            try {
                this.shipState = new WaitingState();
                shipState.nextAction(this);//waiting
                semaphore.acquire();
                logger.log(Level.INFO, "Ship " + this.getShipId() + " is in processing");
                shipState.nextAction(this);//processing
                TimeUnit.MILLISECONDS.sleep(1000);
                shipState.nextAction(this);//end
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException | IOException | PortException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}