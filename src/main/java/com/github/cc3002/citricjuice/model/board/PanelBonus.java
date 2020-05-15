package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.Player;

public class PanelBonus extends AbstractPanel {

    /**
     * Creates a bonus panel.
     */
    public PanelBonus(){
        super(PanelType.BONUS);
    }

    /**
     * After the player drop in the panel, activate the bonus benefit.
     * @param player
     */
    public void activatedBy(final Player player){
        applyBonusTo(player);
    }
}