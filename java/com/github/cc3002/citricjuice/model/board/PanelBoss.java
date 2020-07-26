package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Boss;
import com.github.cc3002.citricjuice.model.units.IUnit;
import com.github.cc3002.citricjuice.model.units.Player;

import java.util.List;

public class PanelBoss extends AbstractPanel{
    private final Boss boss1 = new Boss("Storage Manager", 8, 3, 2, -1);
    private final Boss boss2 = new Boss("Shifu Robot", 7, 2, 3, -2);
    private final Boss boss3 = new Boss("Flying Castle", 10, 2, 1, -3);

    /**
     * Creates a Boss panel.
     * @param id
     */
    public PanelBoss(int id) {
        super(PanelType.BOSS, id);
    }

    @Override
    public void activatedBy(Player player) {
        int choose = player.roll();
        if (choose < 3) {
            boss1.receiveAttack(player.attackDamage());
            if (boss1.getCurrentHP()>0){
                enemyAlive = boss1;
                boss1.increaseStarsBy(5);
            } else {
                List<Integer> data = boss1.defeatedByPlayer();
                player.increaseStarsBy(data.get(0));
                player.increaseWinsBy(data.get(1));
                boss1.setCurrentHP(boss1.getMaxHP());
            }
        } else if (choose > 4) {
            boss3.receiveAttack(player.attackDamage());
            if (boss3.getCurrentHP()>0){
                enemyAlive = boss3;
                boss3.increaseStarsBy(5);
            } else {
                List<Integer> data = boss3.defeatedByPlayer();
                player.increaseStarsBy(data.get(0));
                player.increaseWinsBy(data.get(1));
                boss3.setCurrentHP(boss3.getMaxHP());
            }
        } else {
            boss2.receiveAttack(player.attackDamage());
            if (boss2.getCurrentHP()>0){
                enemyAlive = boss2;
                boss2.increaseStarsBy(5);
            } else {
                List<Integer> data = boss2.defeatedByPlayer();
                player.increaseStarsBy(data.get(0));
                player.increaseWinsBy(data.get(1));
                boss2.setCurrentHP(boss2.getMaxHP());
            }
        }
    }

    @Override
    public IUnit getEnemy(int id){
        if (id == 1){
            return boss1;
        } else if (id == 2){
            return boss2;
        } else {
            return boss3;
        }
    }
}
