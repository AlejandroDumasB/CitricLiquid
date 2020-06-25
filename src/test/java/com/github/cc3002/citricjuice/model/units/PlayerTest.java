package java.com.github.cc3002.citricjuice.model.units;

import org.junit.jupiter.api.Test;

import java.com.github.cc3002.citricjuice.model.NormaGoal;
import java.com.github.cc3002.citricjuice.model.board.IPanel;
import java.com.github.cc3002.citricjuice.model.board.PanelHome;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends AbstractTestUnit{

    @Test
    @Override
    public void constructorTest(){
        IUnit expectedPLayer1 = new Player("playerTest1", 3, 1, 2, 2);
        assertEquals(expectedPLayer1, playerTest1);
    }

    @Test
    @Override
    public void copyTest(){
        IUnit playerCopy = playerTest2.copy();
        assertEquals(playerCopy, playerTest2);
    }

    @Test
    @Override
    public void defeatedByPlayerTest(){
        playerTest1.increaseStarsBy(100);
        List<Integer> dataTest = playerTest1.defeatedByPlayer();
        assertEquals(50, playerTest1.getStars());
        assertTrue(dataTest.get(0) == 50 && dataTest.get(1) == 2);
    }

    @Test
    @Override
    public void defeatedByWildTest(){
        playerTest2.increaseStarsBy(100);
        List<Integer> dataTest = playerTest2.defeatedByWild();
        assertEquals(50, playerTest2.getStars());
        assertTrue(dataTest.get(0) == 50 && dataTest.get(1) == 2);
    }

    @Test
    public void setAndGetTest(){
        IPanel home = new PanelHome(0);
        playerTest1.setHome(home);
        playerTest2.setNormaGoal(NormaGoal.WINS);
        assertEquals(playerTest1.getHome_id(), home.getId());
        assertEquals(NormaGoal.WINS, playerTest2.getNormaGoal());
    }
}
