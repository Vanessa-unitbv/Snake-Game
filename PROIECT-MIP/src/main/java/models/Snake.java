package models;

import java.awt.EventQueue;
import javax.swing.JFrame;

import interfaces.ISnake;

public class Snake extends JFrame implements ISnake {

    public Snake() {
        initUI();
    }

    @Override
    public void initUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
