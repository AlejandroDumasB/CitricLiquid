package java.com.github.cc3002.citricjuice.model.units;

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
