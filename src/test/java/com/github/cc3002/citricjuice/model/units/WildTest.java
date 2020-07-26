package com.github.cc3002.citricjuice.model.units;

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

    @Test
    public void attackTest(){
        Wild wildTest1 = new Wild("wild1",1,1,-6,-6);
        Wild wildTest2 = new Wild("wild2",1,1,-6,-6);
        Wild wildTest3 = new Wild("wild3",10,10,10,10);
        wildTest1.attack(wildTest2);
        wildTest1.attack(wildTest3);
        assertEquals(0,wildTest2.getCurrentHP());
        assertEquals(0,wildTest1.getCurrentHP());
        assertTrue(wildTest3.getCurrentHP()>8);
    }

}
