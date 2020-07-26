package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.IUnit;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.Wild;

import java.util.List;

public class PanelEncounter extends AbstractPanel{
    private final Wild enemy1 = new Wild("Chicken", 3, -1, -1, 1);
    private final Wild enemy2 = new Wild("Robo Ball", 3, -1, 1, -1);
    private final Wild enemy3 = new Wild("Seagull", 3, 1, -1, -1);

    /**
     * Creates an Encounter panel.
     * @param id
     */
    public PanelEncounter(int id) {
        super(PanelType.ENCOUNTER, id);
    }

    @Override
    public void activatedBy(Player player) {
        int choose = player.roll();
        if (choose < 3) {
            enemy1.receiveAttack(player.attackDamage());
            if (enemy1.getCurrentHP()>0){
                enemyAlive = enemy1;
            } else {
                List<Integer> data = enemy1.defeatedByPlayer();
                player.increaseStarsBy(data.get(0));
                player.increaseWinsBy(data.get(1));
            }
            enemy1.setCurrentHP(enemy1.getMaxHP());
        } else if (choose > 4) {
            enemy3.receiveAttack(player.attackDamage());
            if (enemy3.getCurrentHP()>0){
                enemyAlive = enemy3;
            } else {
                List<Integer> data = enemy3.defeatedByPlayer();
                player.increaseStarsBy(data.get(0));
                player.increaseWinsBy(data.get(1));
            }
            enemy3.setCurrentHP(enemy3.getMaxHP());
        } else {
            enemy2.receiveAttack(player.attackDamage());
            if (enemy2.getCurrentHP()>0){
                enemyAlive = enemy2;
            } else {
                List<Integer> data = enemy2.defeatedByPlayer();
                player.increaseStarsBy(data.get(0));
                player.increaseWinsBy(data.get(1));
            }
            enemy2.setCurrentHP(enemy2.getMaxHP());
        }
    }

    @Override
    public IUnit getEnemy(int id){
        if (id == 1){
            return enemy1;
        } else if (id == 2){
            return enemy2;
        } else {
            return enemy3;
        }
    }
}
