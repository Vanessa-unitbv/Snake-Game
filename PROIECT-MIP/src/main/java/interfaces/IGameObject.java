package interfaces;

import java.awt.*;

public interface IGameObject {
    public default int getX(){return 0;}
    public default int getY(){return 0;}
}
