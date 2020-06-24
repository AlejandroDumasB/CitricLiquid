package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.Boss;
import java.com.github.cc3002.citricjuice.model.units.IUnit;
import java.com.github.cc3002.citricjuice.model.units.Player;

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
            player.attack(boss1);
            boss1.setCurrentHP(boss1.getMaxHP());
        } else if (choose > 4) {
            player.attack(boss3);
            boss3.setCurrentHP(boss3.getMaxHP());
        } else {
            player.attack(boss2);
            boss2.setCurrentHP(boss2.getMaxHP());
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
