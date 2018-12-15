import java.awt.*;

public class Ball extends Circle{
    private double vx;
    private double vy;

    public void setSpeedX(double vx){
        this.vx = vx;
    }

    public void setSpeedY(double vy){
        this.vy = vy;
    }

    public double getSpeedX(){
        return vx;
    }

    public double getSpeedY(){
        return vy;
    }

    public void updatePosition(double t){
       x += t * vx;
       y += t * vy;
    }

    public boolean intersect(Ball ball){
        double a = Math.pow((x - ball.getXPos()), 2);
        double b = Math.pow((y - ball.getYPos()), 2);
        double d = Math.pow(a + b, 0.5);
        if(d <= r + ball.getRadius()){
            return true;
        }else{
            return false;
        }
    }

    public void collide(Ball newball){
        double a = Math.pow((x - newball.getXPos()), 2);
        double b = Math.pow((y - newball.getYPos()), 2);
        double d = Math.pow(a + b, 0.5);
        boolean res = intersect(newball);
        if(res == true){
            double x1 = (x - newball.getXPos())/d;
            double y1 = (y - newball.getYPos())/d;
            double newColV1 = vx * x1 + vy * y1;
            double newNormV1 = (-1) * vx * y1 + vy * x1;
            double newColV2 = newball.getSpeedX()*x1 + newball.getSpeedY()*y1;
            double newNormV2 = (-1)*newball.getSpeedX()*y1 + newball.getSpeedY()*x1;
            vx = newColV2 * x1 - newNormV1 * y1;
            vy= newColV2 * y1 + newNormV1 * x1;
            newball.vx = newColV1 * x1 - newNormV2 * y1;
            newball.vy = newColV1 * y1 + newNormV2 * x1;
            if(color == Color.RED || newball.color == Color.RED){
                color = Color.RED;
                newball.color = Color.RED;
            }
        }

    }

    public Ball(double x, double y, double r, Color color){
        super(x, y, r);
        this.color = color;
    }
}
