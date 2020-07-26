package com.github.cc3002.citricjuice.model.units;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public interface IUnitTest {

    @Test
    void winsTest();

    @Test
    void starsTest();

    @Test
    void normaClearTest();

    @Test
    void currentHPTest();

    @Test
    void getTest();

    @Test
    void defendTest();

    @RepeatedTest(100)
    void avoidTest();

    @RepeatedTest(100)
    void rollConsistencyTest();

    @Test
    void constructorTest();

    @Test
    void copyTest();

    @Test
    void defeatedByPlayerTest();

    @Test
    void defeatedByWildTest();
}
