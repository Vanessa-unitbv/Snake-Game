package models;

import interfaces.ITAdapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter implements ITAdapter {
    private final Board board;

    public TAdapter(Board board) {
        this.board = board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!board.isRightDirection())) {
            board.setLeftDirection(true);
            board.setUpDirection(false);
            board.setDownDirection(false);
        }
        if ((key == KeyEvent.VK_RIGHT) && (!board.isLeftDirection())) {
            board.setRightDirection(true);
            board.setUpDirection(false);
            board.setDownDirection(false);
        }
        if ((key == KeyEvent.VK_UP) && (!board.isDownDirection())) {
            board.setUpDirection(true);
            board.setRightDirection(false);
            board.setLeftDirection(false);
        }
        if ((key == KeyEvent.VK_DOWN) && (!board.isUpDirection())) {
            board.setDownDirection(true);
            board.setRightDirection(false);
            board.setLeftDirection(false);
        }

        if (key == KeyEvent.VK_R) {
            board.resetGame();
        }

        if (key == KeyEvent.VK_M) {
            board.startGameFromMaxScore();
        }

        if (key == KeyEvent.VK_T) {
            board.showScores();
        }
    }
}
