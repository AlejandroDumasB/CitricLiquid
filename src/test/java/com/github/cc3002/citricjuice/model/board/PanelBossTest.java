package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PanelBossTest extends AbstractTestPanel{

    @Override
    public void setTestPanel() {
        testPanel = new PanelBoss(0);
        expectedPanelType = PanelType.BOSS;
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

    @Override
    public void dropPanelConsistencyTest() {}

    @Override
    public void homePanelTest() {}

    @Override
    public void neutralPanelTest() {}

    @RepeatedTest(100)
    public void bossPanelConsistencyTest(){
        testPanel.activatedBy(suguri);
        assertTrue(testPanel.getEnemy(1).getCurrentHP()>=0);
        assertTrue(testPanel.getEnemy(2).getCurrentHP()<=testPanel.getEnemy(2).getMaxHP());
        assertEquals(testPanel.getEnemy(35).getCurrentHP(), testPanel.getEnemy(42).getCurrentHP());
    }
}
