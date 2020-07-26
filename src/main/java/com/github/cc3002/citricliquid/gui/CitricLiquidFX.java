package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.units.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ignacio Slater Muñoz.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class CitricLiquidFX extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private final GameController gameController = new GameController();
    private final Group root = new Group();
    private final Button leftButton = new Button("GoLeft");
    private final Button rightButton = new Button("GoRight");
    private final Button avoidButton = new Button("Avoid");
    private final Button defendButton = new Button("Defend");
    private final Button rollButton = new Button("Roll");
    private final Button stopButton = new Button("Stop");
    private final Button dontStopButton = new Button("DontStop");
    private final Button fightButton = new Button("Fight");
    private final Button dontFightButton = new Button("DontFight");
    private final Button starsButton = new Button("STARS");
    private final Button winsButton = new Button("WINS");
    private final Button endTurnButton = new Button("EndTurn");
    private final Map<Integer, List<Integer>> field = new HashMap<>();
    private int fieldCounter = 0;
    private final ImageView player0 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player1.png")));
    private final ImageView player1 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player2.png")));
    private final ImageView player2 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player3.png")));
    private final ImageView player3 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player4.png")));
    private final List<ImageView> players = List.of(player0, player1, player2, player3);
    private final ImageView playerSim0 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player1.png")));
    private final ImageView playerSim1 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player2.png")));
    private final ImageView playerSim2 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player3.png")));
    private final ImageView playerSim3 = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "player4.png")));
    private final List<ImageView> playersSim = List.of(playerSim0, playerSim1, playerSim2, playerSim3);
    private final Label player0Label = new Label();
    private final Label player1Label = new Label();
    private final Label player2Label = new Label();
    private final Label player3Label = new Label();
    private final List<Label> playersLabel = List.of(player0Label, player1Label, player2Label, player3Label);
    private final Label chapterLabel = new Label();
    private final ImageView dice = new ImageView();
    private final Image dice0 = new Image(new FileInputStream(RESOURCE_PATH + "dice0.png"));
    private final Image dice1 = new Image(new FileInputStream(RESOURCE_PATH + "dice1.png"));
    private final Image dice2 = new Image(new FileInputStream(RESOURCE_PATH + "dice2.png"));
    private final Image dice3 = new Image(new FileInputStream(RESOURCE_PATH + "dice3.png"));
    private final Image dice4 = new Image(new FileInputStream(RESOURCE_PATH + "dice4.png"));
    private final Image dice5 = new Image(new FileInputStream(RESOURCE_PATH + "dice5.png"));
    private final Image dice6 = new Image(new FileInputStream(RESOURCE_PATH + "dice6.png"));
    private final List<Image> dices = List.of(dice0,dice1,dice2,dice3,dice4,dice5,dice6);
    private final Label state = new Label();
    private final ImageView enemy = new ImageView();
    private final Label enemyLabel = new Label();

    public CitricLiquidFX() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Citric Liquid");
        primaryStage.setResizable(false);
        Scene scene = createScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private @NotNull Scene createScene() throws FileNotFoundException {
        gameController.setField();
        Scene scene = new Scene(root, 1280, 720);
        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
        root.getChildren().add(background);
        setButton(rollButton,50,30);
        rollButton.setOnAction(event -> gameController.initPhase());
        setButton(leftButton,100,30);
        leftButton.setOnAction(event -> gameController.decisionPhase(0));
        setButton(rightButton,155,30);
        rightButton.setOnAction(event -> gameController.decisionPhase(1));
        setButton(avoidButton,230,30);
        avoidButton.setOnAction(event -> avoid());
        setButton(defendButton,280,30);
        defendButton.setOnAction(event -> defend());
        setButton(stopButton,350, 30);
        stopButton.setOnAction(event -> gameController.stopMove());
        setButton(dontStopButton,395,30);
        dontStopButton.setOnAction(event -> gameController.dontStopMove());
        setButton(endTurnButton, 500, 30);
        endTurnButton.setOnAction(event -> gameController.endTurn());
        setButton(fightButton,600,30);
        fightButton.setOnAction(event -> gameController.fightPhase(1));
        setButton(dontFightButton,650,30);
        dontFightButton.setOnAction(event -> gameController.fightPhase(0));
        setButton(starsButton,750,30);
        starsButton.setOnAction(event -> gameController.normaPhase(0));
        setButton(winsButton,800,30);
        winsButton.setOnAction(event -> gameController.normaPhase(1));
        setFieldGame();
        chapterLabel.setLayoutX(1000);
        chapterLabel.setLayoutY(30);
        root.getChildren().add(chapterLabel);
        dice.setX(1000);
        dice.setY(100);
        dice.setFitHeight(50);
        dice.setFitWidth(50);
        root.getChildren().add(dice);
        state.setLayoutX(1000);
        state.setLayoutY(60);
        root.getChildren().add(state);
        enemy.setFitWidth(150);
        enemy.setFitHeight(150);
        enemy.setX(750);
        enemy.setY(350);
        enemyLabel.setLayoutX(750);
        enemyLabel.setLayoutY(510);
        for(int j=0; j<4; j++){
            playersSim.get(j).setFitHeight(40);
            playersSim.get(j).setFitWidth(40);
            playersSim.get(j).setX(950);
            playersSim.get(j).setY(200 + (j*100));
            root.getChildren().add(playersSim.get(j));
        }
        for(int i=0; i<4; i++){
            root.getChildren().add(players.get(i));
            root.getChildren().add(playersLabel.get(i));
        }
        startAnimator();
        return scene;
    }

    private void setFieldGame() throws FileNotFoundException {
        createImage("home.png",100,625);
        createImage("home.png",625,625);
        createImage("home.png",625,100);
        createImage("home.png",100,100);
        createImage("yellow.png",175,625);
        createImage("yellow.png",250,625);
        createImage("yellow.png",550,625);
        createImage("yellow.png",625,550);
        createImage("yellow.png",625,475);
        createImage("yellow.png",625,175);
        createImage("yellow.png",550,100);
        createImage("yellow.png",475,100);
        createImage("yellow.png",550,250);
        createImage("yellow.png",475,175);
        createImage("yellow.png",175,100);
        createImage("yellow.png",100,175);
        createImage("yellow.png",100,250);
        createImage("yellow.png",100,550);
        createImage("yellow.png",175,475);
        createImage("yellow.png",250,550);
        createImage("wild.png",325,625);
        createImage("wild.png",625,400);
        createImage("wild.png",400,100);
        createImage("wild.png",100,325);
        createImage("boss.png",475,250);//boss
        createImage("boss.png",250,475);//boss
        createImage("bonus.png",475,625);
        createImage("bonus.png",625,250);
        createImage("bonus.png",250,100);
        createImage("bonus.png",100,475);
        createImage("drop.png",400,625);
        createImage("drop.png",625,325);
        createImage("drop.png",325,100);
        createImage("drop.png",100,400);
    }

    private void createImage(String resource, int xPos, int yPos) throws FileNotFoundException {
        var sprite = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + resource)));
        sprite.setFitWidth(75);
        sprite.setFitHeight(75);
        sprite.setY(yPos);
        sprite.setX(xPos);
        field.put(fieldCounter,List.of(xPos, yPos));
        fieldCounter++;
        root.getChildren().add(sprite);
    }

    private void startAnimator() {
        List<Player> playersGC = gameController.getPlayers();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                chapterLabel.setText("CHAPTER = "+ gameController.getChapter());
                state.setText(gameController.getState());
                if(gameController.getWinner().size()>0){
                    setWinner();
                }
                try {
                    setEnemy();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for(int i=0; i<playersGC.size(); i++){
                    int id = gameController.getPlayerPanel(playersGC.get(i)).getId();
                    int xplus = 0;
                    int yplus = 0;
                    if (i==1){
                        xplus = 37;
                    } else if (i==2){
                        yplus = 37;
                    } else if (i==3){
                        xplus = 37;
                        yplus = 37;
                    }
                    players.get(i).setFitWidth(37);
                    players.get(i).setFitHeight(37);
                    players.get(i).setX(field.get(id).get(0) + xplus);
                    players.get(i).setY(field.get(id).get(1) + yplus);
                    String label = "Player " + (i+1) + ": " + playersGC.get(i).getCurrentHP() + " HP ; "
                            + playersGC.get(i).getStars() + " stars ; " + playersGC.get(i).getWins() + " wins ; "
                            + playersGC.get(i).getNormaLevel() + "norma";
                    if (playersGC.get(i)==gameController.getTurnOwner()){
                        label += " <-";
                    }
                    playersLabel.get(i).setLayoutX(1000);
                    playersLabel.get(i).setLayoutY(200 + (i*100));
                    playersLabel.get(i).setText(label);
                    dice.setImage(dices.get(gameController.getDice()));
                }
            }
        };
        timer.start();
    }

    private void setButton(Button button, int xPos, int yPos){
        button.setLayoutX(xPos);
        button.setLayoutY(yPos);
        root.getChildren().add(button);
    }

    private void setEnemy() throws FileNotFoundException {
        IPanel currentPanel = gameController.getPlayerPanel(gameController.getTurnOwner());
        if (currentPanel.getEnemyAlive()!=null && !root.getChildren().contains(enemy)){
            String enemyName = currentPanel.getEnemyAlive().getName();
            enemy.setImage(new Image(new FileInputStream(RESOURCE_PATH+enemyName+".png")));
            enemyLabel.setText(enemyName+ " - atk:" + currentPanel.getEnemyAlive().getAtk() + " - HP:"
                    + currentPanel.getEnemyAlive().getCurrentHP());
            root.getChildren().add(enemyLabel);
            root.getChildren().add(enemy);
        } else if(currentPanel.getEnemyAlive()==null) {
            root.getChildren().remove(enemy);
            root.getChildren().remove(enemyLabel);
        }
    }

    public void avoid(){
        if (gameController.getState().equals("Opponent Choose Avoid or Defend")){
            gameController.fightPvp1(0);
        } else if (gameController.getState().equals("CurrentPlayer Choose Avoid or Defend")){
            gameController.fightPvp2(0);
        } else {
            gameController.avoidAttack();
        }
    }

    public void defend(){
        if (gameController.getState().equals("Opponent Choose Avoid or Defend")){
            gameController.fightPvp1(1);
        } else if (gameController.getState().equals("CurrentPlayer Choose Avoid or Defend")){
            gameController.fightPvp2(1);
        } else {
            gameController.defendAttack();
        }
    }

    public void setWinner(){
        state.setText("THE WINNER IS: " + gameController.getWinner().get(0).getName());
    }
}
