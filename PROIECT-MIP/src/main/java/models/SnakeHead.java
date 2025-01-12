package models;

import java.awt.*;
import interfaces.ISnakeHead;

class SnakeHead extends GameObject  implements ISnakeHead {

    public SnakeHead(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
