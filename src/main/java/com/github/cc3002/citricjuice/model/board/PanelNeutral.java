package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.Player;

public class PanelNeutral extends AbstractPanel {

    /**
     * Creates a neutral panel.
     */
    public PanelNeutral(){
        super(PanelType.NEUTRAL);
    }

    /**
     * No penalization or benefit for the player dropping here.
     * @param player
     */
    public void activatedBy(final Player player){}

}