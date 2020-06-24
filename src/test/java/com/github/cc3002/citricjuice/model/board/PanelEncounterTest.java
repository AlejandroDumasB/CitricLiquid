package java.com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PanelEncounterTest extends AbstractTestPanel{

    @Override
    public void setTestPanel() {
        testPanel = new PanelEncounter(0);
        expectedPanelType = PanelType.ENCOUNTER;
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
        assertEquals(testPanel.getEnemy(1).getMaxHP(), testPanel.getEnemy(1).getCurrentHP());
        assertEquals(testPanel.getEnemy(2).getMaxHP(), testPanel.getEnemy(2).getCurrentHP());
        assertEquals(testPanel.getEnemy(35).getMaxHP(), testPanel.getEnemy(42).getCurrentHP());
    }
}
