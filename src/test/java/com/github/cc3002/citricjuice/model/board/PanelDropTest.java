package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PanelDropTest extends AbstractTestPanel {

    @Override
    public void setTestPanel() {
        testPanel = new PanelDrop(0);
        expectedPanelType = PanelType.DROP;
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
    public void bonusPanelConsistencyTest() {}

    @RepeatedTest(100)
    @Override
    public void dropPanelConsistencyTest() {
        int expectedStars = 30;
        suguri.increaseStarsBy(30);
        assertEquals(expectedStars, suguri.getStars());
        final var testRandom = new Random(testSeed);
        suguri.setSeed(testSeed);
        for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
            final int roll = testRandom.nextInt(6) + 1;
            testPanel.activatedBy(suguri);
            expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
            assertEquals(expectedStars, suguri.getStars(),
                    "Test failed with seed: " + testSeed);
            suguri.normaClear();
        }
    }

    @Override
    public void homePanelTest() {}

    @Override
    public void neutralPanelTest() {}

}