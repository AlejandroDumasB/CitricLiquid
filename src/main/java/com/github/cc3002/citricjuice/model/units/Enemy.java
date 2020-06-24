package java.com.github.cc3002.citricjuice.model.units;

import java.util.List;

public abstract class Enemy extends AbstractUnit{
    /**
     * Constructor for a default enemy unit.
     *
     * @param name
     *      the name of the unit
     * @param maxHP
     *      the max hit-points of the unit
     * @param atk
     *      feature that define damage output
     * @param def
     *      feature that define damage reduction
     * @param evd
     *      feature that allows to avoid an attack
     */
    protected Enemy(String name, int maxHP, int atk, int def, int evd) {
        super(name, maxHP, atk, def, evd);
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
        }
    }

    /**
     * This method randomly choose if the enemy unit will defend or avoid an incoming attack.
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

}
