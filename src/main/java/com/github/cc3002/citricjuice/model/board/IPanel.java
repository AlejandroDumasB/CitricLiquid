package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * This interface represents all the different panels that can shape the board of the game.
 * <p>
 * The signature for all the common methods of the panels are defined here.
 */
public interface IPanel{

    void applyHealTo(final @NotNull Player player);

    void applyDropTo(final @NotNull Player player);

    void applyBonusTo(final @NotNull Player player);

    PanelType getType();

    Set<IPanel> getNextPanels();

    void addNextPanel(final IPanel panel);

    void activatedBy(final Player player);
}