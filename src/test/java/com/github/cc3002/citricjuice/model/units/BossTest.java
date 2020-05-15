package java.com.github.cc3002.citricjuice.model.units;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossTest extends AbstractTestUnit{

    @Test
    @Override
    public void constructorTest(){
        IUnit expectedBossUnit = new Boss("Store Manager", 8, 3, 3, -1);
        assertEquals(expectedBossUnit, bossUnitTest);
    }

    @Test
    @Override
    public void copyTest(){
        IUnit bossUnitCopy = bossUnitTest.copy();
        assertEquals(bossUnitCopy, bossUnitTest);
    }

    @Test
    @Override
    public void defeatedByPlayerTest(){
        bossUnitTest.increaseStarsBy(100);
        List<Integer> dataTest = bossUnitTest.defeatedByPlayer();
        assertEquals(0, bossUnitTest.getStars());
        assertTrue(dataTest.get(0) == 100 && dataTest.get(1) == 3);
    }

    @Test
    @Override
    public void defeatedByWildTest(){
        bossUnitTest.increaseStarsBy(100);
        List<Integer> dataTest = bossUnitTest.defeatedByWild();
        assertEquals(50, bossUnitTest.getStars());
        assertTrue(dataTest.get(0) == 50 && dataTest.get(1) == 3);
    }

}
