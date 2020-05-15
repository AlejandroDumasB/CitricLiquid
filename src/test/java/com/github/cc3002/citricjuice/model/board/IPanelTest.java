package java.com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public interface IPanelTest {

    void setTestPanel();

    @Test
    void typePanelTest();

    @Test
    void nextPanelTest();

    @RepeatedTest(100)
    void bonusPanelConsistencyTest();

    @RepeatedTest(100)
    void dropPanelConsistencyTest();

    @Test
    void homePanelTest();

    @Test
    void neutralPanelTest();

}
