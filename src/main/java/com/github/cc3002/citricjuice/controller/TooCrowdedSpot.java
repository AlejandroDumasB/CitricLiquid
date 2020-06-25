package java.com.github.cc3002.citricjuice.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TooCrowdedSpot implements PropertyChangeListener {

    private GameController gameController;

    public TooCrowdedSpot(GameController controller){
        this.gameController = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameController.spotTooCrowded();
    }
}
