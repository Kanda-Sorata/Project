package Principal;

import java.awt.*;

public class Paroi {
    private Rectangle rectangle;

    public Paroi(int x0, int y0, int height, int width){ //height hauteur width largeur
        rectangle = new Rectangle(x0, y0, height, width);
    }

    public void dessine(Graphics g){
        g.fillRect(rectangle.x, rectangle.y, rectangle.height, rectangle.width);
    }
}
