package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import interfaces.IBoard;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;

public class Board extends JPanel implements IBoard {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;

    private List<GameObject> snakeObjects = new ArrayList<>();
    private int apple_x;
    private int apple_y;
    private boolean inGame = true;
    private Timer timer;
    private Image headImage;
    private Image bodyImage;
    private Image appleImage;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private Map<String, List<Integer>> playerScores = new HashMap<>();
    private String currentPlayer;

    public Board() {
        loadScoresFromFile();
        currentPlayer = JOptionPane.showInputDialog("Introduceți numele jucătorului:");
        if (currentPlayer == null || currentPlayer.isEmpty()) {
            currentPlayer = "Anonim";
        }
        initBoard();
    }

    @Override
    public void initBoard() {
        addKeyListener(new TAdapter(this));
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    @Override
    public void loadImages() {
        ImageIcon iid = new ImageIcon("../ProiectMIP-VanessaPalatka/PROIECT-MIP/dot.png");
        bodyImage = iid.getImage();

        ImageIcon iia = new ImageIcon("../ProiectMIP-VanessaPalatka/PROIECT-MIP/apple.png");
        appleImage = iia.getImage();

        ImageIcon iih = new ImageIcon("../ProiectMIP-VanessaPalatka/PROIECT-MIP/head.png");
        headImage = iih.getImage();
    }

    @Override
    public void initGame() {
        snakeObjects.add(new SnakeHead(50, 50, headImage));
        snakeObjects.add(new SnakeBody(40, 50, bodyImage));
        snakeObjects.add(new SnakeBody(30, 50, bodyImage));

        locateApple();

        timer = new Timer(140, e -> gameLoop());
        timer.start();
    }

    @Override
    public void gameLoop() {
        if (inGame) {
            checkApple();
            move();
            checkCollision();
        }
        repaint();
    }

    @Override
    public void checkApple() {
        if (snakeObjects.get(0).getX() == apple_x && snakeObjects.get(0).getY() == apple_y) {
            snakeObjects.add(new SnakeBody(snakeObjects.get(snakeObjects.size() - 1).getX(), snakeObjects.get(snakeObjects.size() - 1).getY(), bodyImage));
            locateApple();
        }
    }

    @Override
    public void move() {
        for (int i = snakeObjects.size() - 1; i > 0; i--) {
            GameObject current = snakeObjects.get(i);
            GameObject previous = snakeObjects.get(i - 1);
            current.x = previous.getX();
            current.y = previous.getY();
        }

        SnakeHead head = (SnakeHead) snakeObjects.get(0);
        if (leftDirection) {
            head.x -= DOT_SIZE;
        }
        if (rightDirection) {
            head.x += DOT_SIZE;
        }
        if (upDirection) {
            head.y -= DOT_SIZE;
        }
        if (downDirection) {
            head.y += DOT_SIZE;
        }
    }

    @Override
    public void checkCollision() {
        SnakeHead head = (SnakeHead) snakeObjects.get(0);
        if (head.getX() >= B_WIDTH || head.getY() >= B_HEIGHT || head.getX() < 0 || head.getY() < 0) {
            inGame = false;
            timer.stop();
        }
    }

    @Override
    public void locateApple() {
        int r = (int) (Math.random() * 29);
        apple_x = r * DOT_SIZE;
        r = (int) (Math.random() * 29);
        apple_y = r * DOT_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(appleImage, apple_x, apple_y, this);
            for (GameObject obj : snakeObjects) {
                obj.draw(g);
            }
        } else {
            gameOver(g);
        }
    }

    @Override
    public void gameOver(Graphics g) {
        String msg = "Game Over!";
        Font small = new Font("Helvetica", Font.BOLD, 10);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

        int score = snakeObjects.size() - 3;
        addPlayerScore(currentPlayer, score);
        saveScoresToFile();
    }

    @Override
    public void resetGame() {
        snakeObjects.clear();
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        inGame = true;
        initGame();
    }

    @Override
    public void addPlayerScore(String playerName, int score) {
        playerScores.putIfAbsent(playerName, new ArrayList<>());
        playerScores.get(playerName).add(score);
    }

    @Override
    public void saveScoresToFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("scores.json"), playerScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadScoresFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("scores.json");
            if (file.exists()) {
                playerScores = mapper.readValue(file, new TypeReference<Map<String, List<Integer>>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startGameFromMaxScore() {
        List<Integer> scores = playerScores.get(currentPlayer);
        if (scores == null || scores.isEmpty()) {
            return;
        }

        int maxScore = Collections.max(scores);

        int initialSnakeLength = maxScore + 2;

        snakeObjects.clear();
        for (int i = 0; i < initialSnakeLength; i++) {
            snakeObjects.add(new SnakeBody(50 - i * DOT_SIZE, 50, bodyImage));
        }
        snakeObjects.add(0, new SnakeHead(50, 50, headImage));

        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
        inGame = true;
        locateApple();
        timer.start();
    }

    @Override
    public void showScores() {
        if (playerScores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu există scoruri salvate.", "Scoruri", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : playerScores.entrySet()) {
            String playerName = entry.getKey();
            List<Integer> scores = entry.getValue();
            if (scores != null && !scores.isEmpty()) {
                int maxScore = Collections.max(scores);
                sortedScores.add(new AbstractMap.SimpleEntry<>(playerName, maxScore));
            }
        }

        sortedScores.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        StringBuilder scoreMessage = new StringBuilder("TOP SCORERS:\n");
        for (Map.Entry<String, Integer> entry : sortedScores) {
            scoreMessage.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(this, scoreMessage.toString(), "Scor", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean isLeftDirection() {
        return leftDirection;
    }

    @Override
    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    @Override
    public boolean isRightDirection() {
        return rightDirection;
    }

    @Override
    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    @Override
    public boolean isUpDirection() {
        return upDirection;
    }

    @Override
    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    @Override
    public boolean isDownDirection() {
        return downDirection;
    }

    @Override
    public void setDownDirection(boolean downDirection) {
        this.downDirection = downDirection;
    }
}
