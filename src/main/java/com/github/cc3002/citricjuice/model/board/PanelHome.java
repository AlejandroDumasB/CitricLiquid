package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.Player;

public class PanelHome extends AbstractPanel {

    /**
     * Creates a home panel.
     */
    public PanelHome(){
        super(PanelType.HOME);
    }

    /**
     * After the player drop in the panel, activate the heal benefit.
     * @param player
     */
    public void activatedBy(final Player player){
        applyHealTo(player);
    }

}