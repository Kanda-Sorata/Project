package Principal;

import java.awt.*;
import java.util.Collections;

public class Balle {
    private Rectangle rect;
    private Billard billard;
    private int x;
    private  int y;
    private int deltaX = 3;
    private int deltaY = 2;

    public Balle(Billard billard, int x0, int y0, int width, int height){
        x = x0;
        y = y0;
        this.billard = billard;
        rect = new Rectangle(x0, y0, width, height);
    }

    public void dessine(Graphics g){
        g.setColor(Color.BLACK);
        g.fillArc(rect.x,rect.y, rect.width, rect.height, 0,360);
    }

    public void bouge(){
       for(Paroi p: billard.getParoisVerticales()) {
            if(p.collision(this)){
                deltaX = -deltaX;
            }
        }

        for(Paroi p: billard.getParoisHorizontales()){
            if(p.collision(this)){
                deltaY = -deltaY;
            }
        }
        rect.x -= deltaX;
        rect.y -= deltaY;

    }

    public Rectangle getBalleRect(){
        return rect;
    }
}
