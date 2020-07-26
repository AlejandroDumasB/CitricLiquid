package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.cc3002.citricjuice.model.units.Player;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class AbstractTestPanel implements IPanelTest{

    private final static String PLAYER_NAME = "Suguri";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;
    protected Player suguri;
    protected long testSeed;
    protected IPanel testPanel;
    protected IPanel testAbsPanel;
    protected PanelType expectedPanelType;

    @BeforeEach
    public void setUp(){
        setTestPanel();
        suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
        testSeed = new Random().nextLong();
    }

    public abstract void setTestPanel();

    @Test
    @Override
    public void typePanelTest(){
        assertEquals(expectedPanelType, testPanel.getType());
    }

    @Test
    @Override
    public void nextPanelTest() {
        assertTrue(testPanel.getNextPanels().isEmpty());
        final var expectedPanel1 = new PanelNeutral(1);
        final var expectedPanel2 = new PanelNeutral(2);

        testPanel.addNextPanel(expectedPanel1);
        assertEquals(1, testPanel.getNextPanels().size());

        testPanel.addNextPanel(expectedPanel2);
        assertEquals(2, testPanel.getNextPanels().size());

        testPanel.addNextPanel(expectedPanel2);
        assertEquals(2, testPanel.getNextPanels().size());

        assertEquals(Set.of(expectedPanel1, expectedPanel2),
                testPanel.getNextPanels());
    }

    @Test
    public void setAndGetPlayersTest(){
        assertEquals(0, testPanel.getId());
        Player playerTest = new Player("test", 10, 1, 1, 1);
        testPanel.setPlayer(playerTest);
        assertEquals(List.of(playerTest), testPanel.getPlayers());
        testPanel.setPlayer(suguri);
        assertEquals(List.of(playerTest, suguri), testPanel.getPlayers());
        testPanel.popPlayer(playerTest);
        assertEquals(List.of(suguri), testPanel.getPlayers());
    }

    @Test
    @Override
    public void searchTest(){
        Player playerTest = new Player("test", 10, 1, 1, 1);
        testPanel.setPlayer(playerTest);
        assertTrue(testPanel.search(playerTest));
        assertFalse(testPanel.search(suguri));
        testPanel.setPlayer(suguri);
        assertTrue(testPanel.search(suguri));
    }
}