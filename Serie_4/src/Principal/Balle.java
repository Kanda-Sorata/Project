package Principal;

import java.awt.*;

public class Balle {
    private Rectangle rect;
    private Billard billard;
    private int x;
    private  int y;
    private int deltaX;
    private int deltaY;

    public Balle(Billard billard, int x0, int y0, int height, int width){
        x = x0;
        y = y0;
        this.billard = billard;
        rect = new Rectangle(x0, y0, height, width);
    }

    public void dessine(Graphics g){
        g.fillArc(rect.x,rect.y, rect.height, rect.width, 0,360);
        g.setColor(Color.red);
    }

    public void bouge(){
        x += deltaX;
        x += deltaY;
    }
}
