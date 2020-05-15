package java.com.github.cc3002.citricjuice.model.units;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WildTest extends AbstractTestUnit{

    @Test
    @Override
    public void constructorTest(){
        IUnit expectedWildUnit = new Wild("Chicken", 3, -1, -1, 1);
        assertEquals(expectedWildUnit, wildUnitTest);
    }

    @Test
    @Override
    public void copyTest(){
        IUnit wildUnitCopy = wildUnitTest.copy();
        assertEquals(wildUnitCopy, wildUnitTest);
    }

    @Test
    @Override
    public void defeatedByPlayerTest(){
        wildUnitTest.increaseStarsBy(100);
        List<Integer> dataTest = wildUnitTest.defeatedByPlayer();
        assertEquals(0, wildUnitTest.getStars());
        assertTrue(dataTest.get(0) == 100 && dataTest.get(1) == 1);
    }

    @Test
    @Override
    public void defeatedByWildTest(){
        wildUnitTest.increaseStarsBy(100);
        List<Integer> dataTest = wildUnitTest.defeatedByWild();
        assertEquals(50, wildUnitTest.getStars());
        assertTrue(dataTest.get(0) == 50 && dataTest.get(1) == 1);
    }

}
