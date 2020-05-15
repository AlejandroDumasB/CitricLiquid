package java.com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PanelNeutralTest extends AbstractTestPanel {

    @Override
    public void setTestPanel() {
        testPanel = new PanelNeutral();
        expectedPanelType = PanelType.NEUTRAL;
    }

    @Override
    public void bonusPanelConsistencyTest() {}

    @Override
    public void dropPanelConsistencyTest() {}

    @Override
    public void homePanelTest() {}

    @Test
    @Override
    public void neutralPanelTest() {
        final var expectedSuguri = suguri.copy();
        testPanel.activatedBy(suguri);
        assertEquals(expectedSuguri, suguri);
    }

}