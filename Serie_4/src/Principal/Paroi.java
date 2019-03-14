package Principal;

import java.awt.*;

public class Paroi {
    private Rectangle rectangle;
    private int points;

    public Paroi(int x0, int y0, int width, int height, int points){ //height hauteur width largeur
        rectangle = new Rectangle(x0, y0, width, height);
       	this.points = points;
    }

    public void dessine(Graphics g){
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean collision(Balle balle){
        return rectangle.intersects(balle.getBalleRect());
    }

    public int getPoints(){
        return points;
    }
}
