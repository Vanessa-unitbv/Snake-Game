package interfaces;

import java.awt.*;

public interface IBoard {
    public default void initBoard(){}
    public default void loadImages(){}
    public default void initGame(){}
    public  default void gameLoop(){}
    public default void checkApple(){}
    public default void move(){}
    public default void checkCollision(){}
    public default void locateApple(){}
    public default void paintComponent(Graphics g){}
    public default void doDrawing(Graphics g){}
    public default void gameOver(Graphics g){}
    public default void resetGame(){}
    public default void addPlayerScore(String playerName, int score){}
    public default void saveScoresToFile(){}
    public default void loadScoresFromFile(){}
    public default void startGameFromMaxScore(){}
    public default void showScores(){}
    public boolean isLeftDirection();
    public default void setLeftDirection(boolean leftDirection){}
    public boolean isRightDirection();
    public default void setRightDirection(boolean rightDirection){}
    public boolean isUpDirection();
    public default void setUpDirection(boolean upDirection){}
    public boolean isDownDirection();
    public default void setDownDirection(boolean downDirection){}
}
