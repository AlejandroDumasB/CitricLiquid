package java.com.github.cc3002.citricjuice.model.units;

import java.util.ArrayList;
import java.util.List;

public class Wild extends AbstractUnit{

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
     * This method represent the combat system of the game. For now will be
     * unnecessary, because a wild unit can't initiate a combat.
     */
    @Override
    public void attack(IUnit enemy) {
        int dice = roll();
        int baseDamage = dice + atk;
        enemy.receiveAttack(baseDamage);
        if(enemy.getCurrentHP() > 0){
            enemy.counter(this);
        } else {
            List<Integer> data = enemy.defeatedByWild();
            increaseStarsBy(data.get(0));
            enemy.recoveryPhase();
        }
        if(currentHP == 0){
            recoveryPhase();
        }
    }

    /**
     * This method randomly choose if the player will defend or avoid an incoming attack.
     */
    @Override
    public void receiveAttack(int baseDamage) {
        int choiceDice = roll();
        if(choiceDice > 3){
            defend(baseDamage);
        }
        else{
            avoid(baseDamage);
        }
    }

    /**
     * This method is define to attack back after survive an enemy attack.
     */
    @Override
    public void counter(IUnit enemy) {
        int dice = roll();
        int baseDamage = dice + atk;
        enemy.receiveAttack(baseDamage);
        if(enemy.getCurrentHP() == 0){
            List<Integer> data = enemy.defeatedByWild();
            increaseStarsBy(data.get(0));
        }
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

    /**
     * Defines the state of the wild unit after get K.O.
     * Not implement yet.
     */
    @Override
    public void recoveryPhase() {
        //nothing for now
    }
}
