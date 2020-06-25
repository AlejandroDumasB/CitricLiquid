package java.com.github.cc3002.citricjuice.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.com.github.cc3002.citricjuice.model.NormaGoal;
import java.com.github.cc3002.citricjuice.model.board.IPanel;
import java.com.github.cc3002.citricjuice.model.units.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    private final GameController gameController = new GameController();

    @BeforeEach
    public void setUp(){
        IPanel homePanel1 = gameController.createHomePanel(0);
        IPanel homePanel2 = gameController.createHomePanel(1);
        IPanel homePanel3 = gameController.createHomePanel(2);
        IPanel homePanel4 = gameController.createHomePanel(3);
        gameController.createPlayer("Player1", 100, 1, 1, 1, homePanel1);
        gameController.createPlayer("Player2", 100, 1, 1, 1, homePanel2);
        gameController.createPlayer("Player3", 100, 1, 1, 1, homePanel3);
        gameController.createPlayer("Player4", 100, 1, 1, 1, homePanel4);
        gameController.setCurrentPlayer(gameController.getPlayers().get(0));
        IPanel bossPanel = gameController.createBossPanel(4);
        IPanel wildPanel = gameController.createEncounterPanel(5);
        IPanel bonusPanel1 = gameController.createBonusPanel(6);
        IPanel bonusPanel2 = gameController.createBonusPanel(7);
        IPanel dropPanel = gameController.createDropPanel(8);
        IPanel neutralPanel1 = gameController.createNeutralPanel(9);
        IPanel neutralPanel2 = gameController.createNeutralPanel(10);
        IPanel neutralPanel3 = gameController.createNeutralPanel(11);
        IPanel neutralPanel4 = gameController.createNeutralPanel(12);
        homePanel1.addNextPanel(neutralPanel1);
        neutralPanel1.addNextPanel(bossPanel);
        bossPanel.addNextPanel(homePanel2);
        homePanel2.addNextPanel(neutralPanel2);
        neutralPanel2.addNextPanel(wildPanel);
        wildPanel.addNextPanel(homePanel3);
        homePanel3.addNextPanel(neutralPanel3);
        homePanel3.addNextPanel(neutralPanel4);
        neutralPanel3.addNextPanel(bonusPanel1);
        neutralPanel4.addNextPanel(bonusPanel1);
        bonusPanel1.addNextPanel(homePanel4);
        homePanel4.addNextPanel(bonusPanel2);
        bonusPanel2.addNextPanel(dropPanel);
        dropPanel.addNextPanel(homePanel1);
    }

    @Test
    public void createPanelTest(){
        assertSame(0, gameController.getPanels().get(0).getId());
        assertSame(4, gameController.getPanels().get(4).getId());
        assertSame(5, gameController.getPanels().get(5).getId());
        assertSame(6, gameController.getPanels().get(6).getId());
        assertSame(8, gameController.getPanels().get(8).getId());
        assertSame(9, gameController.getPanels().get(9).getId());
    }

    @Test
    public void createPlayerTest(){
        Player player = gameController.getPlayers().get(0);
        assertEquals(player.getHome_id(), gameController.getPanels().get(0).getId());
        assertEquals(player, gameController.getPanels().get(0).getPlayers().get(0));
    }

    @Test
    public void createNonPlayerTest(){
        Wild wildTest = gameController.createWildUnit("Chicken", 1, 1, 1, 1);
        Boss bossTest = gameController.createBossUnit("Robot", 2,2, 2, 2);
        assertEquals(wildTest, new Wild("Chicken", 1, 1, 1, 1));
        assertEquals(bossTest, new Boss("Robot", 2,2, 2, 2));
    }

    @Test
    public void nextPanelTest(){
        IPanel panelTest = gameController.getPanels().get(0);
        assertEquals(List.copyOf(panelTest.getNextPanels()).get(0), gameController.getPanels().get(4));
    }

    @Test
    public void normaTest(){
        List<Integer> normaTest = new ArrayList<>();
        normaTest.add(30);
        normaTest.add(2);
        assertEquals(normaTest, gameController.norma(2));
        normaTest.clear();
        normaTest.add(120);
        normaTest.add(9);
        assertEquals(normaTest, gameController.norma(4));
    }

    @Test
    public void normaCheckTest(){
        Player playerTest = gameController.getTurnOwner();
        assertFalse(gameController.normaCheck(playerTest));
        playerTest.increaseStarsBy(30);
        assertTrue(gameController.normaCheck(playerTest));
    }

    @Test
    public void playerPanelTest(){
        Player currentPlayer = gameController.getTurnOwner();
        assertEquals(gameController.getPanels().get(0), gameController.getPlayerPanel(currentPlayer));
    }

    @Test
    public void setPlayerNormaGoalTest(){
        gameController.setCurrPlayerNormaGoal(NormaGoal.WINS);
        assertEquals(NormaGoal.WINS, gameController.getTurnOwner().getNormaGoal());
    }

    @Test
    public void turnTest(){
        gameController.initPhase();
        assertEquals(gameController.getPlayers().get(1), gameController.getTurnOwner());
        gameController.initPhase();
        assertEquals(gameController.getPlayers().get(2), gameController.getTurnOwner());
        gameController.initPhase();
        assertEquals(gameController.getPlayers().get(3), gameController.getTurnOwner());
        gameController.initPhase();
        assertEquals(gameController.getPlayers().get(0), gameController.getTurnOwner());
        assertEquals(2, gameController.getChapter());
    }
}
