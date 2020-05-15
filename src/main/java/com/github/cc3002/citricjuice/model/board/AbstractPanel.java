package java.com.github.cc3002.citricjuice.model.board;

import org.jetbrains.annotations.NotNull;

import java.com.github.cc3002.citricjuice.model.units.Player;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class that defines some common information and behaviour between all panels.
 **/

public abstract class AbstractPanel implements IPanel {

    private final Set<IPanel> nextPanels = new HashSet<>();
    private final PanelType type;

    /**
     * Creates a default panel.
     */
    public AbstractPanel(PanelType type){
        this.type = type;
    }

    /**
     * Restores a player's HP in 1.
     */
    public void applyHealTo(final @NotNull Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    public void applyDropTo(final @NotNull Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }

    /**
     * Increase the player's star count by the D6 roll multiplied by the maximum between the player's
     * norma level and three.
     */
    public void applyBonusTo(final @NotNull Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }

    /**
     * Returns the type of this panel
     */
    public PanelType getType() {
        return type;
    }

    /**
     * Returns a copy of this panel's next ones.
     */
    public Set<IPanel> getNextPanels() {
        return Set.copyOf(nextPanels);
    }

    /**
     * Adds a new adjacent panel to this one.
     *
     * @param panel the panel to be added.
     */
    public void addNextPanel(final IPanel panel) {
        nextPanels.add(panel);
    }
}