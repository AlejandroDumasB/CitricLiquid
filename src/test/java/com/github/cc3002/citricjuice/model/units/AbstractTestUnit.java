package java.com.github.cc3002.citricjuice.model.units;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTestUnit implements IUnitTest{

    protected Player playerTest1;
    protected Player playerTest2;
    protected Wild wildUnitTest;
    protected Boss bossUnitTest;

    @BeforeEach
    public void setUp(){
        playerTest1 = new Player("playerTest1", 3, 1, 2, 2);
        playerTest2 = new Player("playerTest2", 5, 2, 0, 1);
        wildUnitTest = new Wild("Chicken", 3, -1, -1, 1);
        bossUnitTest = new Boss("Store Manager", 8, 3, 3, -1);
    }

    @Test
    @Override
    public void winsTest(){
        int testWins = 2;
        playerTest1.increaseWinsBy(testWins);
        assertEquals(2, playerTest1.getWins());
    }

    @Test
    @Override
    public void starsTest(){
        int testStars = 20;
        playerTest1.increaseStarsBy(testStars);
        playerTest2.increaseStarsBy(testStars);
        assertEquals(20, playerTest2.getStars());
        playerTest1.reduceStarsBy(10);
        playerTest2.reduceStarsBy(30);
        assertEquals(10, playerTest1.getStars());
        assertEquals(0, playerTest2.getStars());
    }

    @Test
    @Override
    public void normaClearTest() {
        playerTest1.normaClear();
        assertEquals(2, playerTest1.getNormaLevel());
    }

    @Test
    @Override
    public void currentHPTest(){
        wildUnitTest.setCurrentHP(10);
        assertEquals(3, wildUnitTest.getCurrentHP());
        wildUnitTest.setCurrentHP(-5);
        assertEquals(0, wildUnitTest.getCurrentHP());
        wildUnitTest.setCurrentHP(1);
        assertEquals(1, wildUnitTest.getCurrentHP());
    }

    @Test
    @Override
    public void getTest(){
        assertEquals(8, bossUnitTest.getMaxHP());
        assertEquals(3, bossUnitTest.getAtk());
        assertEquals(3, bossUnitTest.getDef());
        assertEquals(-1, bossUnitTest.getEvd());
        assertEquals("Store Manager", bossUnitTest.getName());
    }

    @Test
    @Override
    public void defendTest(){
        final long testSeed1 = new Random().nextLong();
        playerTest1.setSeed(testSeed1);
        int P1DamageToP2 = playerTest1.attackDamage();
        final long testSeed2 = new Random().nextLong();
        playerTest2.setSeed(testSeed2);
        playerTest2.defend(P1DamageToP2);
        assertNotEquals(playerTest2.getMaxHP(), playerTest2.getCurrentHP());
    }

    @RepeatedTest(100)
    @Override
    public void avoidTest(){
        final long testSeed1 = new Random().nextLong();
        playerTest1.setSeed(testSeed1);
        int P1DamageToP2 = playerTest1.attackDamage();
        final long testSeed2 = new Random().nextLong();
        playerTest2.setSeed(testSeed2);
        playerTest2.avoid(P1DamageToP2);
        assertTrue(playerTest2.getCurrentHP() == playerTest2.getMaxHP() ||
                playerTest2.getCurrentHP() == (playerTest2.getMaxHP() - P1DamageToP2) ||
                playerTest2.getCurrentHP() == 0);
    }

    @RepeatedTest(100)
    @Override
    public void rollConsistencyTest() {
        final long testSeed = new Random().nextLong();
        playerTest2.setSeed(testSeed);
        final int roll = playerTest2.roll();
        assertTrue(roll >= 1 && roll <= 6,
                roll + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed);
    }
}