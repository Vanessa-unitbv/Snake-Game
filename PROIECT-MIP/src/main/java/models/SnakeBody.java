package models;

import java.awt.*;
import interfaces.ISnakeBody;

class SnakeBody extends GameObject implements ISnakeBody {

    public SnakeBody(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
