package Principal;

import java.awt.*;

public class Balle {
    private Rectangle rect;
    private Billard billard;
    private int x;
    private  int y;
    private int deltaX = 3;
    private int deltaY = 2;
    private int posColors;
    private Color color;
    private static Color [] colors = {Color.RED, Color.GREEN, Color.CYAN, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.BLUE };

    public Balle(Billard billard, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.billard = billard;
        rect = new Rectangle(x, y, width, height);
        posColors = 0;
    }

    public void dessine(Graphics g){
        g.setColor(color);
        g.fillArc(rect.x, rect.y, rect.width, rect.height, 0,360);
    }

    public void bouge(){
       for(Paroi p: billard.getParoisVerticales()) {
            if(p.collision(this)){
                deltaX = -deltaX;
                billard.setTotalPoint(p.getPoints());
            }
        }

        for(Paroi p: billard.getParoisHorizontales()){
            if(p.collision(this)){
                deltaY = -deltaY;
                billard.setTotalPoint(p.getPoints());
            }
        }

        rect.x -= deltaX;
        rect.y -= deltaY;

    }


    public Rectangle getBalleRect(){
        return rect;
    }

    public void changeColor(){
        if(posColors > 7){
            posColors = 0;
        }

        color = colors[posColors];
        posColors += 1;
    }
}
