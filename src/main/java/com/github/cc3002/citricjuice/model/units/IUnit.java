package com.github.cc3002.citricjuice.model.units;

import com.github.cc3002.citricjuice.model.board.IPanel;
import java.util.List;

/**
 * This interface represents all the units that can interact with each other inside the game.
 * <p>
 * The signature for all the common methods of the units are defined here. The only unit that
 * could be used by the user is the Player, but isn't developed yet
 */

public interface IUnit {

    void increaseWinsBy(int amount);

    int getWins();

    void increaseStarsBy(int amount);

    int getStars();

    void setSeed(long seed);

    int roll();

    String getName();

    int getMaxHP();

    int getAtk();

    int getDef();

    int getEvd();

    int getNormaLevel();

    void normaClear();

    int getCurrentHP();

    void setCurrentHP(int newHP);

    void reduceStarsBy(int amount);

    int attackDamage();

    void defend(int baseDamage);

    void avoid(int baseDamage);

    IUnit copy();

    void attack(IUnit enemy);

    void receiveAttack(int baseDamage);

    void counter(IUnit enemy);

    List<Integer> defeatedByPlayer();

    List<Integer> defeatedByWild();
}
