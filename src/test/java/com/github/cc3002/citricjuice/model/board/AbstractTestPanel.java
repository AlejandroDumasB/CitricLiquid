package java.com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.com.github.cc3002.citricjuice.model.units.Player;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractTestPanel implements IPanelTest{

    private final static String PLAYER_NAME = "Suguri";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;
    protected Player suguri;
    protected long testSeed;
    protected IPanel testPanel;
    protected PanelType expectedPanelType;

    @BeforeEach
    public void setUp(){
        setTestPanel();
        suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
        testSeed = new Random().nextLong();
    }

    public abstract void setTestPanel();

    @Test
    public void typePanelTest(){
        assertEquals(expectedPanelType, testPanel.getType());
    }

    @Test
    public void nextPanelTest() {
        assertTrue(testPanel.getNextPanels().isEmpty());
        final var expectedPanel1 = new PanelNeutral();
        final var expectedPanel2 = new PanelNeutral();

        testPanel.addNextPanel(expectedPanel1);
        assertEquals(1, testPanel.getNextPanels().size());

        testPanel.addNextPanel(expectedPanel2);
        assertEquals(2, testPanel.getNextPanels().size());

        testPanel.addNextPanel(expectedPanel2);
        assertEquals(2, testPanel.getNextPanels().size());

        assertEquals(Set.of(expectedPanel1, expectedPanel2),
                testPanel.getNextPanels());
    }
}