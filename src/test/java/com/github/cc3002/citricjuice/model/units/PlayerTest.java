package com.github.cc3002.citricjuice.model.units;

import org.junit.jupiter.api.Test;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.board.PanelHome;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends AbstractTestUnit{

    @Test
    @Override
    public void constructorTest(){
        IUnit expectedPLayer1 = new Player("playerTest1", 3, 1, 2, 2);
        assertEquals(expectedPLayer1, playerTest1);
    }

    @Test
    @Override
    public void copyTest(){
        IUnit playerCopy = playerTest2.copy();
        assertEquals(playerCopy, playerTest2);
    }

    @Test
    @Override
    public void defeatedByPlayerTest(){
        playerTest1.increaseStarsBy(100);
        List<Integer> dataTest = playerTest1.defeatedByPlayer();
        assertEquals(50, playerTest1.getStars());
        assertTrue(dataTest.get(0) == 50 && dataTest.get(1) == 2);
    }

    @Test
    @Override
    public void defeatedByWildTest(){
        playerTest2.increaseStarsBy(100);
        List<Integer> dataTest = playerTest2.defeatedByWild();
        assertEquals(50, playerTest2.getStars());
        assertTrue(dataTest.get(0) == 50 && dataTest.get(1) == 2);
    }

    @Test
    public void setAndGetTest(){
        IPanel home = new PanelHome(0);
        playerTest1.setHome(home);
        playerTest2.setNormaGoal(NormaGoal.WINS);
        assertEquals(playerTest1.getHome_id(), home.getId());
        assertEquals(NormaGoal.WINS, playerTest2.getNormaGoal());
    }

    @Test
    public void attackTest(){
        Player player1 = new Player("playerTest1",10,2,1,1);
        Player player2 = new Player("playerTest2",10,2,1,1);
        player1.attack(player2);
        assertTrue(player2.getCurrentHP()>=2);
        assertTrue(player1.getCurrentHP()>=2);
        Player player3 = new Player("playerTest3",1,1,1,-6);
        player1.attack(player3);
        assertEquals(0, player3.getCurrentHP());
    }

    @Test
    public void recoveryTest(){
        Player playerTest = new Player("playerTest",10,2,1,1);
        List<Boolean> test = new ArrayList<>();
        for(int i=0; i<6; i++){
            test.add(playerTest.recovery());
        }
        assertTrue(test.contains(false));
    }
}
