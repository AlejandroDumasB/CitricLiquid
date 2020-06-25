package java.com.github.cc3002.citricjuice.controller;

import java.com.github.cc3002.citricjuice.model.NormaGoal;
import java.com.github.cc3002.citricjuice.model.board.*;
import java.com.github.cc3002.citricjuice.model.units.Boss;
import java.com.github.cc3002.citricjuice.model.units.Player;
import java.com.github.cc3002.citricjuice.model.units.Wild;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final List<IPanel> panels = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player winner;
    private int chapter;
    private TooCrowdedSpot tooCrowded = new TooCrowdedSpot(this);
    private boolean isTooCrowded = false;

    public GameController(){
        this.chapter = 1;
        this.winner = null;
    }

    /**
     * Method used to identify if there is two players in the same panel, or if
     * the a player finds its own homePanel.
     */
    public void spotTooCrowded(){
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
            endTurn();
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
            mainPhase();
        } else if (isTooCrowded){
            int decision = decisionPhase();
            isTooCrowded = false;
            if(decision == 0){
                moveNextPanel(player, counter);
            } else {
                mainPhase();
            }
        } else {
            IPanel currentPanel = getPlayerPanel(player);
            List<IPanel> nextPanels = List.copyOf(currentPanel.getNextPanels());
            if (nextPanels.size() > 1) {
                int decision = decisionPhase();
                IPanel nextPanel = nextPanels.get(decision);
                currentPanel.popPlayer(player);
                nextPanel.setPlayer(player);
            } else {
                IPanel nextPanel = nextPanels.get(0);
                currentPanel.popPlayer(player);
                nextPanel.setPlayer(player);
            }
            moveNextPanel(player, counter - 1);
        }
    }

    /**
     * A method that is not useful yet, but represent the decision that has to make
     * the user when is controlling the player.
     */
    public int decisionPhase(){
        return 0;
    }

    /**
     * After the player's move, this phase is in charge of what happen in the current panel.
     * The user can decided if will attack another player in the same spot, or if the player
     * could increase it normaLevel.
     */
    public void mainPhase(){
        IPanel currentPanel = getPlayerPanel(currentPlayer);
        if ((currentPanel.getId() == currentPlayer.getHome_id()) && normaCheck(currentPlayer)){
            currentPlayer.normaClear();
            if (currentPlayer.getNormaLevel() > 5){
                winner = currentPlayer;
            }
            int decision = decisionPhase();
            if (decision == 0){
                currentPlayer.setNormaGoal(NormaGoal.STARS);
            } else {
                currentPlayer.setNormaGoal(NormaGoal.WINS);
            }
        }
        if (currentPanel.getPlayers().size() > 1){
            int decision = decisionPhase();
            if (decision == 0){
                currentPlayer.attack(currentPanel.getPlayers().get(0));
            }
        }
        currentPanel.activatedBy(currentPlayer);
        endTurn();
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
    public Player getWinner(){
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
    }
}