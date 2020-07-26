package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

public class PanelHome extends AbstractPanel {

    /**
     * Creates a home panel.
     */
    public PanelHome(int id){
        super(PanelType.HOME, id);
    }

    /**
     * After the player drop in the panel, activate the heal benefit.
     * @param player
     */
    public void activatedBy(final Player player){
        applyHealTo(player);
    }

}