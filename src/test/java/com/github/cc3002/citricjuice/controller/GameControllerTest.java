package com.github.cc3002.citricjuice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.units.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        assertEquals(List.copyOf(panelTest.getNextPanels()).get(0), gameController.getPanels().get(9));
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
        assertEquals(gameController.getPlayers().get(0), gameController.getTurnOwner());
        gameController.endTurn();
        assertEquals(gameController.getPlayers().get(1), gameController.getTurnOwner());
        gameController.endTurn();
        assertEquals(gameController.getPlayers().get(2), gameController.getTurnOwner());
        gameController.endTurn();
        assertEquals(gameController.getPlayers().get(3), gameController.getTurnOwner());
        gameController.endTurn();
        assertEquals(2, gameController.getChapter());
    }

    @Test
    public void setNextPanelTest(){
        IPanel panelTest = gameController.createNeutralPanel(13);
        gameController.setNextPanel(gameController.getPanels().get(0),panelTest);
        assertEquals(Set.of(gameController.getPanels().get(9),panelTest),
                gameController.getPanels().get(0).getNextPanels());
    }

    @Test
    public void simpleSetUpTest(){
        GameController controllerTest = new GameController();
        IPanel homeTest = controllerTest.createHomePanel(0);
        controllerTest.createPlayer("test",10,1,1,1,homeTest);
        controllerTest.setCurrentPlayer(controllerTest.getPlayers().get(0));
        IPanel panelTest1 = controllerTest.createNeutralPanel(1);
        IPanel panelTest2 = controllerTest.createNeutralPanel(2);
        IPanel panelTest3 = controllerTest.createNeutralPanel(3);
        IPanel panelTest4 = controllerTest.createNeutralPanel(4);
        IPanel panelTest5 = controllerTest.createNeutralPanel(5);
        IPanel panelTest6 = controllerTest.createNeutralPanel(6);
        homeTest.addNextPanel(panelTest1);
        panelTest1.addNextPanel(panelTest2);
        panelTest2.addNextPanel(panelTest3);
        panelTest3.addNextPanel(panelTest4);
        panelTest4.addNextPanel(panelTest5);
        panelTest5.addNextPanel(panelTest6);
        controllerTest.getTurnOwner().setCurrentHP(0);
        while(homeTest.getPlayers().size()>0){
            controllerTest.initPhase();
        }
        assertEquals(0,homeTest.getPlayers().size());
    }

    @Test
    public void twoPathsTest(){
        gameController.endTurn();
        gameController.endTurn();
        gameController.moveNextPanel(gameController.getTurnOwner(), 2);
        gameController.decisionPhase(0);
        assertEquals(gameController.getPanels().get(6),
                gameController.getPlayerPanel(gameController.getTurnOwner()));
    }

    @Test
    public void handlerTest(){
        gameController.moveNextPanel(gameController.getTurnOwner(),4);
        assertEquals("Fight or Keep Moving?",gameController.getState());
        gameController.fightPhase(1);
        assertEquals("Opponent Choose Avoid or Defend",gameController.getState());
        gameController.fightPvp1(1);
        assertTrue(gameController.getPlayers().get(1).getCurrentHP()<100);
        assertEquals("CurrentPlayer Choose Avoid or Defend",gameController.getState());
        gameController.fightPvp2(1);
        assertEquals("End Your Turn",gameController.getState());
    }

    @Test
    public void setFieldTest(){
        GameController controllerTest = new GameController();
        controllerTest.setField();
        controllerTest.initPhase();
        assertNotEquals(0,controllerTest.getDice());
    }
}
