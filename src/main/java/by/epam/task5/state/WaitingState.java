package by.epam.task5.state;

import by.epam.task5.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitingState implements ShipState {
    private static Logger logger = LogManager.getLogger();
  /*  private static WaitingState instance;

    public static WaitingState getInstance() {
        while (instance == null) {
            instance = new WaitingState();
        }
        return instance;
    }*/

    @Override
    public void nextAction(Ship ship) {
        StringBuilder builder = new StringBuilder();
        builder.append("Ship " + ship.getShipId() + " with " + ship.getContainerNumber() + " containers is in queue.");
        if (ship.isLoaded()) {
            if (ship.isUnloaded()) {
                builder.append(" Ship status is - unloading and loading required.");
            }else{
            builder.append(" Ship status - loading required.");
            }
        } else if (ship.isUnloaded()) {
            builder.append(" Ship status - unloading required.");
        }
        logger.log(Level.INFO, builder);
        ship.setShipState(new LoadingUnloadingState());//.getInstance());
    }

   /* @Override
    public void receptionState(Ship ship) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void endState(Ship ship) {
        throw new UnsupportedOperationException();
    }*/
}
