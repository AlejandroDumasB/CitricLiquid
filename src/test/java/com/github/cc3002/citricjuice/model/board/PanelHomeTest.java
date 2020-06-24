package java.com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PanelHomeTest extends AbstractTestPanel {

    public void setTestPanel() {
        testPanel = new PanelHome(0);
        expectedPanelType = PanelType.HOME;
    }

    @Override
    public void bonusPanelConsistencyTest() {}

    @Override
    public void dropPanelConsistencyTest() {}

    @Test
    @Override
    public void homePanelTest() {
        assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
        testPanel.activatedBy(suguri);
        assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());

        suguri.setCurrentHP(1);
        testPanel.activatedBy(suguri);
        assertEquals(2, suguri.getCurrentHP());
    }

    @Override
    public void neutralPanelTest() {}

}