package Principal;

import java.awt.*;

public class Piege {
    private Rectangle rectangle;

    public Piege(int x, int y, int width, int height){
        rectangle = new Rectangle(x, y, width, height);
    }

    public void dessine(Graphics g){
        g.setColor(Color.BLACK);
        g.fillArc(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 0, 360);
    }

    public boolean disparition(Balle b){
        return  b.getBalleRect().intersects(rectangle);
    }

}
