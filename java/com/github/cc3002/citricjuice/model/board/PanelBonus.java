package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;

public class PanelBonus extends AbstractPanel {

    /**
     * Creates a bonus panel.
     */
    public PanelBonus(int id){
        super(PanelType.BONUS, id);
    }

    /**
     * After the player drop in the panel, activate the bonus benefit.
     * @param player
     */
    public void activatedBy(final Player player){
        applyBonusTo(player);
    }
}