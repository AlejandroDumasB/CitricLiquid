package java.com.github.cc3002.citricjuice.model.board;

import java.com.github.cc3002.citricjuice.model.units.IUnit;
import java.com.github.cc3002.citricjuice.model.units.Player;
import java.com.github.cc3002.citricjuice.model.units.Wild;

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
            player.attack(enemy1);
            enemy1.setCurrentHP(enemy1.getMaxHP());
        } else if (choose > 4) {
            player.attack(enemy3);
            enemy3.setCurrentHP(enemy3.getMaxHP());
        } else {
            player.attack(enemy2);
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
