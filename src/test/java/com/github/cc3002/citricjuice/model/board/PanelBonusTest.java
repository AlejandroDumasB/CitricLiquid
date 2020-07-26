package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.units.Player;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PanelBonusTest extends AbstractTestPanel{

    @Override
    public void setTestPanel(){
        testPanel = new PanelBonus(0);
        expectedPanelType = PanelType.BONUS;
    }

    @RepeatedTest(100)
    @Override
    public void bonusPanelConsistencyTest() {
        int expectedStars = 0;
        assertEquals(expectedStars, suguri.getStars());
        final var testRandom = new Random(testSeed);
        suguri.setSeed(testSeed);
        for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
            final int roll = testRandom.nextInt(6) + 1;
            testPanel.activatedBy(suguri);
            expectedStars += roll * Math.min(3, normaLvl);
            assertEquals(expectedStars, suguri.getStars(),
                    "Test failed with seed: " + testSeed);
            suguri.normaClear();
        }
    }

    @Override
    @Test
    public void setAndGetPlayersTest() {
        super.setAndGetPlayersTest();
    }

    @Test
    @Override
    public void searchTest(){
        super.searchTest();
    }

    @Test
    @Override
    public void nextPanelTest(){
        super.nextPanelTest();
    }

    @Override
    public void dropPanelConsistencyTest() {}

    @Override
    public void homePanelTest() {}

    @Override
    public void neutralPanelTest() {}

}