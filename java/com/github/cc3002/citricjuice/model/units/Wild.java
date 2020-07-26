package com.github.cc3002.citricjuice.model.units;

import java.util.ArrayList;
import java.util.List;

public class Wild extends Enemy{

    /**
     * Constructor for a wild unit.
     *
     * @param name
     *     the name of the wild unit.
     * @param maxHP
     *     the max hit-points of the wild unit.
     * @param atk
     *     the feature that define damage output.
     * @param def
     *     the feature that define damage reduction.
     * @param evd
     *     the feature that allows to avoid an attack.
     */
    public Wild(final String name, final int maxHP, final int atk, final int def, final int evd) {
        super(name, maxHP, atk, def, evd);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wild)) {
            return false;
        }
        final IUnit player = (Wild) o;
        return getMaxHP() == player.getMaxHP() &&
                getAtk() == player.getAtk() &&
                getDef() == player.getDef() &&
                getEvd() == player.getEvd() &&
                getNormaLevel() == player.getNormaLevel() &&
                getStars() == player.getStars() &&
                getCurrentHP() == player.getCurrentHP() &&
                getName().equals(player.getName());
    }

    /**
     * Returns a copy of this character.
     */
    @Override
    public IUnit copy() {
        return new Wild(name, maxHP, atk, def, evd);
    }

    /**
     * Return the amount of stars and wins that gets the player after defeating us.
     */
    @Override
    public List<Integer> defeatedByPlayer() {
        int starsLoses = stars;
        reduceStarsBy(starsLoses);
        List <Integer> list = new ArrayList<>();
        list.add(starsLoses);
        list.add(1);
        return list;
    }

    /**
     * Return the amount of stars and wins that gets the wild unit after defeating us.
     * Although, the wild unit doesn't increase his win count.
     */
    @Override
    public List<Integer> defeatedByWild() {
        int starsLoses = stars / 2;
        reduceStarsBy(starsLoses);
        List <Integer> list = new ArrayList<>();
        list.add(starsLoses);
        list.add(1);
        return list;
    }

}
