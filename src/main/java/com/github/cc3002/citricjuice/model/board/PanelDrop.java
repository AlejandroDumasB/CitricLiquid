package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.Player;

public class PanelDrop extends AbstractPanel {

    /**
     * Creates a drop panel.
     */
    public PanelDrop(){
        super(PanelType.DROP);
    }

    /**
     * After the player drop in the panel, activate the penalization.
     * @param player
     */
    public void activatedBy(final Player player){
        applyDropTo(player);
    }

}