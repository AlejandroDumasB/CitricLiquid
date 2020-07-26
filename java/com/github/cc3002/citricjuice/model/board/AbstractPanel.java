package com.github.cc3002.citricjuice.model.board;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.github.cc3002.citricjuice.model.units.IUnit;
import com.github.cc3002.citricjuice.model.units.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract class that defines some common information and behaviour between all panels.
 **/

public abstract class AbstractPanel implements IPanel {

    private final Set<IPanel> nextPanels = new HashSet<>();
    private final PanelType type;
    private final int id;
    private final List<Player> players = new ArrayList<>();
    private PropertyChangeSupport tooCrowdedNotification = new PropertyChangeSupport(this);
    protected IUnit enemyAlive = null;

    /**
     * Creates a default panel.
     * @param type
     *     feature that define panel's type.
     * @param id
     *     feature that identifies the panel.
     */
    public AbstractPanel(PanelType type, int id){
        this.type = type;
        this.id = id;
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

    /**
     * Returns the id number of the panel.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns a list of all the players on the panel.
     */
    @Override
    public List<Player> getPlayers(){
        return players;
    }

    /**
     * Sets a player on the panel. Notify to the listeners if the panel correspond to
     * the player's homePanel or if it's another player in the same spot.
     */
    @Override
    public void setPlayer(final Player player){
        players.add(player);
        if (players.size() > 1){
            tooCrowdedNotification.firePropertyChange("TooCrowded",1,2);
        }

    }

    /**
     * Remove a player from the list of players (usually, because the player move out)
     */
    @Override
    public void popPlayer(final Player player){
        players.remove(player);
    }

    /**
     * Returns if the player is located in this panel.
     */
    @Override
    public boolean search(final Player player){
        return players.contains(player);
    }

    @Override
    public IUnit getEnemy(int i){return null;}

    /**
     * Method needed to notify the listeners using Observer Pattern.
     */
    @Override
    public void addTooCrowdedListener(PropertyChangeListener listener){
        tooCrowdedNotification.addPropertyChangeListener(listener);
    }

    @Override
    public void setEnemyAlive(IUnit enemyAlive) {
        this.enemyAlive = enemyAlive;
    }

    @Override
    public IUnit getEnemyAlive() {
        return enemyAlive;
    }
}