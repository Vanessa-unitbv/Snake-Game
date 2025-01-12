package interfaces;

import java.awt.event.KeyEvent;

public interface ITAdapter {
    public default void keyPressed(KeyEvent e){}
}
