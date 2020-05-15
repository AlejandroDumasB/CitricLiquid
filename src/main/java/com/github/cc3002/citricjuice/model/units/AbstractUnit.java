package java.com.github.cc3002.citricjuice.model.units;

import java.util.Random;

/**
 * Abstract class that defines some common information and behaviour between all units.
 **/

public abstract class AbstractUnit implements IUnit {

    protected final Random random;
    protected final String name;
    protected final int maxHP;
    protected final int atk;
    protected final int def;
    protected final int evd;
    protected int normaLevel;
    protected int stars;
    protected int currentHP;
    protected int wins;

    /**
     * Constructor for a default unit without any special behaviour.
     *
     * @param name
     *     the name of the unit
     * @param maxHP
     *     the max hit-points of the unit
     * @param atk
     *     feature that define damage output
     * @param def
     *     feature that define damage reduction
     * @param evd
     *     feature that allows to avoid an attack
     */
    protected AbstractUnit(final String name, final int maxHP, final int atk, final int def, final int evd) {
        this.name = name;
        this.maxHP = currentHP = maxHP;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        normaLevel = 1;
        random = new Random();
    }

    /**
     * Increases this player's wins count by an amount.
     */
    public void increaseWinsBy(final int amount){
        wins += amount;
    }

    /**
     * Returns this player's wins count.
     */
    public int getWins(){
        return wins;
    }

    /**
     * Increases this player's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Returns this player's star count.
     */
    public int getStars() {
        return stars;
    }

    /**
     * Set's the seed for this player's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the current character's attack points.
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Returns the current character's defense points.
     */
    public int getDef() {
        return def;
    }

    /**
     * Returns the current character's evasion points.
     */
    public int getEvd() {
        return evd;
    }

    /**
     * Returns the current norma level
     */
    public int getNormaLevel() {
        return normaLevel;
    }

    /**
     * Performs a norma clear action; the {@code norma} counter increases in 1.
     */
    public void normaClear() {
        normaLevel++;
    }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(final int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    /**
     * Reduces this player's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Define with a dice roll the damage output of the next attack.
     */
    public int attackDamage(){
        int dice = roll();
        return dice + atk;
    }

    /**
     * Define with a dice roll how much of the base damage will be prevent
     * and then reduce the current hit-points.
     * <p>
     * the damage taken is at least 1 hit-point.
     */
    public void defend(int baseDamage) {
        int dice = roll();
        int defense = dice + getDef();
        int finalDamage = Math.max(1, baseDamage - defense);
        int newHP = currentHP - finalDamage;
        setCurrentHP(newHP);
    }

    /**
     * Define with a dice roll if the unit can prevent all the damage
     * and then reduce, or not, the current hit-points.
     */
    public void avoid(int baseDamage) {
        int dice = roll();
        int evasion = dice + getEvd();
        if(evasion <= baseDamage){
            int newHP = currentHP - baseDamage;
            setCurrentHP(newHP);
        }
    }
}
