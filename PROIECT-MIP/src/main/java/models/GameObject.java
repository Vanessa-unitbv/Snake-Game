package models;

import interfaces.IGameObject;
import java.awt.*;

public abstract class GameObject implements IGameObject {
    protected int x;
    protected int y;
    protected Image image;

    public GameObject(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public abstract void draw(Graphics g);

    @Override
    public int getX() {return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
