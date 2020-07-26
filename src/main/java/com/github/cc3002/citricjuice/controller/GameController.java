package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.units.Boss;
import com.github.cc3002.citricjuice.model.units.Player;
import com.github.cc3002.citricjuice.model.units.Wild;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final List<IPanel> panels = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private List<Player> winner = new ArrayList<>();
    private int chapter;
    private TooCrowdedSpot tooCrowded = new TooCrowdedSpot(this);
    private boolean isTooCrowded = false;
    private int lastDice = 0;
    private int playerChoice = 3;
    private int movesLeft = 0;
    private String state = "Roll to Move";

    public GameController(){
        this.chapter = 1;
        this.winner = null;
    }

    /**
     * Method used to identify if there is two players in the same panel, or if
     * the a player finds its own homePanel.
     */
    public void spotTooCrowded(){
        System.out.println("workOut?");
        isTooCrowded = true;
    }

    /**
    * Create a BonusPanel with the correspondent id tag
    * and then is added to the panels list
     */
    public IPanel createBonusPanel(int id) {
        IPanel panel = new PanelBonus(id);
        panel.addTooCrowdedListener(tooCrowded);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a BossPanel with the correspondent id tag
     * and then is added to the panels list
     */
    public IPanel createBossPanel(int id) {
        IPanel panel = new PanelBoss(id);
        panel.addTooCrowdedListener(tooCrowded);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a DropPanel with the correspondent id tag
     * and then is added to the panels list
     */
    public IPanel createDropPanel(int id) {
        IPanel panel = new PanelDrop(id);
        panel.addTooCrowdedListener(tooCrowded);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a EncounterPanel with the correspondent id tag
     * and then is added to the panels list
     */
    public IPanel createEncounterPanel(int id) {
        IPanel panel = new PanelEncounter(id);
        panel.addTooCrowdedListener(tooCrowded);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a HomePanel with the correspondent id tag
     * and then is added to the panels list
     */
    public IPanel createHomePanel(int id) {
        IPanel panel = new PanelHome(id);
        panel.addTooCrowdedListener(tooCrowded);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a BossPanel with the correspondent id tag
     * and then is added to the panels list
     */
    public IPanel createNeutralPanel(int id) {
        IPanel panel = new PanelNeutral(id);
        panel.addTooCrowdedListener(tooCrowded);
        panels.add(panel);
        return panel;
    }

    /**
     * Create a game's player, with its corresponding homePanel
     * Then, the player is added to the players list
     */
    public Player createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
        Player player = new Player(name, hitPoints, attack, defense, evasion);
        player.setHome(panel);
        panel.setPlayer(player);
        players.add(player);
        return player;
    }

    /**
     * Create a non player character Wild, that will be found in the encounterPanels
     */
    public Wild createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new Wild(name, hitPoints, attack, defense, evasion);
    }

    /**
     * Create a non player character Boss, that will be found in the bossPanels
     */
    public Boss createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new Boss(name, hitPoints, attack, defense, evasion);
    }

    /**
     * Add the 2nd panel to the 1st panel Set of NextPanels
     */
    public void setNextPanel(IPanel panel, IPanel panel1) {
        panel.addNextPanel(panel1);
    }

    /**
     * Return all the panels in the game organized in a list
     */
    public List<IPanel> getPanels() {
        return panels;
    }

    /**
     * This is the 1st phase of a player's turn.
     * If the player has no hit-points, enters in a recoveryPhase.
     */
    public void initPhase(){
        if (currentPlayer.getCurrentHP() == 0){
            recoveryPhase();
        } else {
            currentPlayer.increaseStarsBy((chapter/5) + 1);
            movePlayer();
        }
    }

    /**
     * When a player has no hitpoints, should make a roll to become healthy again.
     * If the dice failed, the player lose the whole turn (going to endTurn)
     */
    public void recoveryPhase(){
        if (currentPlayer.recovery()){
            state = "You are in Recovery, End Your Turn";
        } else {
            currentPlayer.setCurrentHP(currentPlayer.getMaxHP());
            initPhase();
        }
    }

    /**
     * The player roll the dice and enters the movePhase
     */
    public void movePlayer() {
        int moves = currentPlayer.roll();
        lastDice = moves;
        moveNextPanel(currentPlayer, moves);
    }

    /**
     * The player move through the adjacent panels, and choose the way if
     * it gets to a panel with more than one path.
     * If the current player finds another player, or its own homePanel, then decides
     * if it wants to continue or not.
     */
    public void moveNextPanel(Player player, int counter){
        if (counter == 0){
            normaPhase(10);
        } else if (isTooCrowded){
            isTooCrowded = false;
            state = "Fight or Keep Moving?";
            movesLeft = counter;
        } else {
            IPanel currentPanel = getPlayerPanel(player);
            List<IPanel> nextPanels = List.copyOf(currentPanel.getNextPanels());
            if (nextPanels.size() > 1) {
                if (playerChoice==3){
                    movesLeft = counter;
                    state = "Choose Right or Left";
                } else {
                    IPanel nextPanel = nextPanels.get(playerChoice);
                    currentPanel.popPlayer(player);
                    nextPanel.setPlayer(player);
                    playerChoice = 3;
                    if(nextPanel.getId()==player.getHome_id()){
                        movesLeft = counter - 1;
                        state = "Do You Want to Stop?";
                    } else {
                        moveNextPanel(player, counter - 1);
                    }
                }
            } else {
                IPanel nextPanel = nextPanels.get(0);
                currentPanel.popPlayer(player);
                nextPanel.setPlayer(player);
                if(nextPanel.getId()==player.getHome_id()){
                    movesLeft = counter - 1;
                    state = "Do You Want to Stop?";
                } else {
                    moveNextPanel(player, counter - 1);
                }
            }
        }
    }

    /**
     * A method that is not useful yet, but represent the decision that has to make
     * the user when is controlling the player.
     */
    public void decisionPhase(int choice){
        playerChoice = choice;
        moveNextPanel(currentPlayer, movesLeft);
    }

    /**
     * This phase is next to the movement of the current player.
     * Checks if the player has reach the norma goal to get to the next norma and
     * allows to choose the next norma goal after a norma clear.
     */
    public void normaPhase(int decision){
        if (decision==0){
            currentPlayer.setNormaGoal(NormaGoal.STARS);
            fightPhase(10);
        } else if (decision==1){
            currentPlayer.setNormaGoal(NormaGoal.WINS);
            fightPhase(10);
        } else {
            IPanel currentPanel = getPlayerPanel(currentPlayer);
            if ((currentPanel.getId() == currentPlayer.getHome_id()) && normaCheck(currentPlayer)){
                currentPlayer.normaClear();
                state = "Choose NormaGoal";
                if (currentPlayer.getNormaLevel() > 5){
                    winner.add(currentPlayer);

                }
            } else {
                fightPhase(10);
            }
        }
    }

    /**
     * This method gets triggered when the current player start a battle against another
     * player or an Enemy unit (Wild or Boss).
     */
    public void fightPhase(int decision){
        IPanel currentPanel = getPlayerPanel(currentPlayer);
        if (decision==0){
            activationPhase();
        } else if (decision==1){
            state = "Opponent Choose Avoid or Defend";
        } else {
            if (currentPanel.getPlayers().size() > 1){
                isTooCrowded = false;
                state = "Do You Want to Fight?";
            } else {
                activationPhase();
            }
        }
    }

    /**
     * When a Player vs Player battle start, this method allows to the Opponent to
     * choose if it wants to avoid o defend the incoming attack (form the current player).
     */
    public void fightPvp1(int decision){
        Player player2 = getPlayerPanel(currentPlayer).getPlayers().get(0);
        int damage = currentPlayer.attackDamage();
        if(decision==0){
            player2.avoid(damage);
        } else {
            player2.defend(damage);
        }
        if(player2.getCurrentHP()>0){
            state = "CurrentPlayer Choose Avoid or Defend";
        } else {
            List<Integer> data = player2.defeatedByPlayer();
            currentPlayer.increaseStarsBy(data.get(0));
            currentPlayer.increaseWinsBy(data.get(1));
            activationPhase();
        }
    }

    /**
     * If the opponent survives the current player attack, it attack back. This method
     * allows to the current player to avoid or defend the incoming attack.
     */
    public void fightPvp2(int decision){
        Player player2 = getPlayerPanel(currentPlayer).getPlayers().get(0);
        int damage = player2.attackDamage();
        if(decision==0){
            currentPlayer.avoid(damage);
        } else {
            currentPlayer.defend(damage);
        }
        if (currentPlayer.getCurrentHP()>0){
            activationPhase();
        } else {
            List<Integer> data = currentPlayer.defeatedByPlayer();
            player2.increaseWinsBy(data.get(0));
            player2.increaseWinsBy(data.get(1));
            state = "End Your Turn";
        }
    }

    /**
     * This is the last phase before the player's end turn. Trigger the effect of the
     * panel under the current player.
     */
    public void activationPhase(){
        IPanel currentPanel = getPlayerPanel(currentPlayer);
        currentPanel.activatedBy(currentPlayer);
        if (currentPanel.getEnemyAlive()!=null){
            state = "Choose Avoid or Defend";
        } else {
            state = "End Your Turn";
        }
    }

    /**
     * This method is in charge of make the current player defend an incoming attack.
     */
    public void defendAttack(){
        IPanel currentPanel = getPlayerPanel(currentPlayer);
        currentPlayer.defend(currentPanel.getEnemyAlive().attackDamage());
        currentPanel.setEnemyAlive(null);
        state = "End Your Turn";
    }

    /**
     * This method is in charge of make the current player avoid an incoming attack.
     */
    public void avoidAttack(){
        IPanel currentPanel = getPlayerPanel(currentPlayer);
        currentPlayer.avoid(currentPanel.getEnemyAlive().attackDamage());
        currentPanel.setEnemyAlive(null);
        state = "End Your Turn";
    }

    /**
     * This method it causes the current player stop his movement.
     */
    public void stopMove(){
        normaPhase(10);
    }

    /**
     * This method allows the current player to don't stop his movement.
     */
    public void dontStopMove(){
        moveNextPanel(currentPlayer, movesLeft);
    }

    /**
     * This method define the requirements to increase the normaLevel of the player, currently.
     */
    public List<Integer> norma(int currentNorma){
        List<Integer> data = new ArrayList<>();
        if (currentNorma == 1){
            data.add(10);
            data.add(null);
        } else if (currentNorma == 2){
            data.add(30);
            data.add(2);
        } else if (currentNorma == 3){
            data.add(70);
            data.add(5);
        } else if (currentNorma == 4){
            data.add(120);
            data.add(9);
        } else if (currentNorma == 5){
            data.add(200);
            data.add(14);
        }
        return data;
    }

    /**
     * This method check if the player is allowed to increase its normaLevel.
     */
    public boolean normaCheck(Player player){
        if (player.getNormaGoal() == NormaGoal.STARS){
            return (player.getStars() > norma(player.getNormaLevel()).get(0));
        } else {
            return (player.getWins() > norma(player.getNormaLevel()).get(1));
        }
    }

    /**
     * Return the current player that is being controlled.
     */
    public Player getTurnOwner() {
        return currentPlayer;
    }

    /**
     * Return the panel where is located the current player.
     */
    public IPanel getPlayerPanel(Player unit) {
        for (IPanel panel : getPanels()){
            if(panel.search(unit)){
                return panel;
            }
        }
        return null;
    }

    /**
     * In the begin of the game, should be define the 1st player to move. Usually, will be the
     * 1st player in the list players.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * This method return a list with the players of the game (in order of turns).
     */
    public List<Player> getPlayers(){
        return players;
    }

    /**
     * This method sets the normaGoal that the current player wants to
     * pursue this round. It can't be changed after chosen until the normaGoal
     * is completed.
     */
    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        currentPlayer.setNormaGoal(goal);
    }

    /**
     * Sets the players homePanel, matching the player's HOME_ID with the panel's ID
     */
    public void setPlayerHome(Player unit, PanelHome panel) {
        unit.setHome(panel);
    }

    /**
     * Return the current chapter in the game.
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * Return the winner of the game. If no player win already, return null.
     */
    public List<Player> getWinner(){
        return winner;
    }

    /**
     * After a player's turn, the game will control the next one specified in the
     * players list. If the four players already move, it circles back to the 1st, and
     * the game is moving to the nest chapter.
     */
    public void endTurn() {
        int currPlayerIndex = players.indexOf(currentPlayer);
        if (currPlayerIndex == 3){
            currentPlayer = players.get(0);
            chapter ++;
        } else {
            currentPlayer = players.get(currPlayerIndex + 1);
        }
        state = "Roll to Move";
    }

    /**
     * This method sets the field that will be use in the Graphic Interface implemented
     * in the CitricLiquidFX.
     */
    public void setField(){
        IPanel homePanel0 = createHomePanel(0);
        IPanel homePanel1 = createHomePanel(1);
        IPanel homePanel2 = createHomePanel(2);
        IPanel homePanel3 = createHomePanel(3);
        createPlayer("Player1", 5, 2, 1, 1, homePanel0);
        createPlayer("Player2", 5, 2, 1, 1, homePanel1);
        createPlayer("Player3", 5, 2, 1, 1, homePanel2);
        createPlayer("Player4", 5, 2, 1, 1, homePanel3);
        currentPlayer = players.get(0);
        IPanel neutralPanel4 = createNeutralPanel(4);
        IPanel neutralPanel5 = createNeutralPanel(5);
        IPanel neutralPanel6 = createNeutralPanel(6);
        IPanel neutralPanel7 = createNeutralPanel(7);
        IPanel neutralPanel8 = createNeutralPanel(8);
        IPanel neutralPanel9 = createNeutralPanel(9);
        IPanel neutralPanel10 = createNeutralPanel(10);
        IPanel neutralPanel11 = createNeutralPanel(11);
        IPanel neutralPanel12 = createNeutralPanel(12);
        IPanel neutralPanel13 = createNeutralPanel(13);
        IPanel neutralPanel14 = createNeutralPanel(14);
        IPanel neutralPanel15 = createNeutralPanel(15);
        IPanel neutralPanel16 = createNeutralPanel(16);
        IPanel neutralPanel17 = createNeutralPanel(17);
        IPanel neutralPanel18 = createNeutralPanel(18);
        IPanel neutralPanel19 = createNeutralPanel(19);
        IPanel wildPanel20 = createEncounterPanel(20);
        IPanel wildPanel21 = createEncounterPanel(21);
        IPanel wildPanel22 = createEncounterPanel(22);
        IPanel wildPanel23 = createEncounterPanel(23);
        IPanel bossPanel24 = createBossPanel(24);
        IPanel bossPanel25 = createBossPanel(25);
        IPanel bonusPanel26 = createBonusPanel(26);
        IPanel bonusPanel27 = createBonusPanel(27);
        IPanel bonusPanel28 = createBonusPanel(28);
        IPanel bonusPanel29 = createBonusPanel(29);
        IPanel dropPanel30 = createDropPanel(30);
        IPanel dropPanel31 = createDropPanel(31);
        IPanel dropPanel32 = createDropPanel(32);
        IPanel dropPanel33 = createDropPanel(33);

        homePanel0.addNextPanel(neutralPanel4);
        neutralPanel4.addNextPanel(neutralPanel5);
        neutralPanel5.addNextPanel(wildPanel20);
        wildPanel20.addNextPanel(dropPanel30);
        dropPanel30.addNextPanel(bonusPanel26);
        bonusPanel26.addNextPanel(neutralPanel6);
        neutralPanel6.addNextPanel(homePanel1);
        homePanel1.addNextPanel(neutralPanel7);
        neutralPanel7.addNextPanel(neutralPanel8);
        neutralPanel8.addNextPanel(wildPanel21);
        wildPanel21.addNextPanel(dropPanel31);
        dropPanel31.addNextPanel(bonusPanel27);

        bonusPanel27.addNextPanel(neutralPanel9);
        neutralPanel9.addNextPanel(homePanel2);
        homePanel2.addNextPanel(neutralPanel10);
        neutralPanel10.addNextPanel(neutralPanel11);
        bonusPanel27.addNextPanel(neutralPanel12);
        neutralPanel12.addNextPanel(bossPanel24);
        bossPanel24.addNextPanel(neutralPanel13);
        neutralPanel13.addNextPanel(neutralPanel11);

        neutralPanel11.addNextPanel(wildPanel22);
        wildPanel22.addNextPanel(dropPanel32);
        dropPanel32.addNextPanel(bonusPanel28);
        bonusPanel28.addNextPanel(neutralPanel14);
        neutralPanel14.addNextPanel(homePanel3);
        homePanel3.addNextPanel(neutralPanel15);
        neutralPanel15.addNextPanel(neutralPanel16);
        neutralPanel16.addNextPanel(wildPanel23);
        wildPanel23.addNextPanel(dropPanel33);
        dropPanel33.addNextPanel(bonusPanel29);

        bonusPanel29.addNextPanel(neutralPanel17);
        neutralPanel17.addNextPanel(homePanel0);
        bonusPanel29.addNextPanel(neutralPanel18);
        neutralPanel18.addNextPanel(bossPanel25);
        bossPanel25.addNextPanel(neutralPanel19);
        neutralPanel19.addNextPanel(neutralPanel5);
    }

    /**
     * This method return the last dice played by the current pLayer.
     */
    public int getDice() {
        return lastDice;
    }

    /**
     * This method return the current state fo the game, which is used in the
     * implementation of the Graphic Interface.
     */
    public String getState(){
        return state;
    }
}