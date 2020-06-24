package java.com.github.cc3002.citricjuice.model.units;

import java.com.github.cc3002.citricjuice.model.NormaGoal;
import java.com.github.cc3002.citricjuice.model.board.IPanel;
import java.util.ArrayList;
import java.util.List;

public class Player extends AbstractUnit{
    private int home_id;
    private NormaGoal normaGoal = NormaGoal.STARS;
    private int timeInRecovery = 0;

    /**
     * Constructor for a PLayer unit.
     *
     * @param name
     *     the name of the pLayer
     * @param maxHP
     *     the max hit-points of the player
     * @param atk
     *     the feature that define damage output
     * @param def
     *     the feature that define damage reduction
     * @param evd
     *     the feature that allows to avoid an attack
     */
    public Player(final String name, final int maxHP, final int atk, final int def, final int evd) {
        super(name, maxHP, atk, def, evd);
    }

    /**
     * Method that allows to compare if two players are equals.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        final IUnit player = (Player) o;
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
        return new Player(name, maxHP, atk, def, evd);
    }

    /**
     * This method represent the combat system of the game. The player attack
     * other unit, and if this one survives then attack back.
     */
    @Override
    public void attack(IUnit enemy){
        int baseDamage = attackDamage();
        enemy.receiveAttack(baseDamage);
        if(enemy.getCurrentHP() > 0){
            enemy.counter(this);
        } else {
            List<Integer> data = enemy.defeatedByPlayer();
            increaseStarsBy(data.get(0));
            increaseWinsBy(data.get(1));
        }
    }

    /**
     * This method randomly choose if the player will defend or avoid an incoming attack.
     * This function is temporary, until the develop of the user interactions.
     */
    @Override
    public void receiveAttack(int baseDamage){
        //random for now
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
    public void counter(IUnit enemy){
        int dice = roll();
        int baseDamage = dice + atk;
        enemy.receiveAttack(baseDamage);
        if(enemy.getCurrentHP() == 0){
            List<Integer> data = enemy.defeatedByPlayer();
            increaseStarsBy(data.get(0));
            increaseWinsBy(data.get(1));
        }
    }

    /**
     * Return the amount of stars and wins that gets the player after defeating us.
     */
    @Override
    public List<Integer> defeatedByPlayer(){
        int starsLoses = stars / 2;
        reduceStarsBy(starsLoses);
        List <Integer> list = new ArrayList<>();
        list.add(starsLoses);
        list.add(2);
        return list;
    }

    /**
     * Return the amount of stars and wins that gets the wild unit after defeating us.
     * Although, the wild unit doesn't increase his win count.
     */
    @Override
    public List<Integer> defeatedByWild(){
        int starsLoses = stars / 2;
        reduceStarsBy(starsLoses);
        List <Integer> list = new ArrayList<>();
        list.add(starsLoses);
        list.add(2);
        return list;
    }

    /**
     * Defines the state of the player after get K.O.
     * Not implement yet.
     */
    public boolean recovery() {
        int dice = roll();
        if(dice > 5 - timeInRecovery){
            timeInRecovery = 0;
            return false;
        } else {
            timeInRecovery++;
            return true;
        }
    }

    public void setHome(IPanel home){
        this.home_id = home.getId();
    }

    public int getHome_id(){
        return home_id;
    }

    public void setNormaGoal(NormaGoal normaGoal){
        this.normaGoal = normaGoal;
    }

    public NormaGoal getNormaGoal(){
        return normaGoal;
    }
}
