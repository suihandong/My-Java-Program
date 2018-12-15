import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class BallScreenSaver extends AnimationFrame{
    public double ballX, ballY, ballXSpeed, ballYSpeed;
    public int ballSize=18;
    public final int START_SPEED=300;
    public final int BORDER=30;
    int numBalls;
    Ball[] ballArray;

    public CollisionLogger collisionLogger;
    public static final int COLLISION_BUCKET_WIDTH = 20;
    public int saveCounter=0;

    public BallScreenSaver(int windowFrameWidth, int windowFrameHeight, String windowName, int numBalls){
        super(windowFrameWidth, windowFrameHeight, windowName);
        this.numBalls = numBalls;
        ballX=randdouble(BORDER,getWidth()-BORDER);
        ballY=randdouble(BORDER,getHeight()-BORDER);
        setRandDir(START_SPEED);
        setFPS(20);
        ballArray = new Ball[numBalls];
        for (int i = 0; i < numBalls; i++) {
            ballX=randdouble(BORDER,getWidth()-BORDER);
            ballY=randdouble(BORDER,getHeight()-BORDER);
            setRandDir(START_SPEED);
            //random x
            ballArray[i]= new Ball(ballX,ballY,ballSize,Color.GREEN);
            //random x speed
            ballArray[i].setSpeedX(ballXSpeed);
            //random y speed
            ballArray[i].setSpeedY(ballYSpeed);
        }
        ballArray[numBalls-1].color = Color.RED;
        collisionLogger = new CollisionLogger(this.getWidth(), this.getHeight(), COLLISION_BUCKET_WIDTH);
    }

    public double randdouble(double min, double max){
        //a utility method to get a random double between min and max.
        return (Math.random()*(max-min)+min);
    }

    public void setRandDir(double speed){
        double dir=randdouble(0,Math.PI*2);
        ballXSpeed=Math.cos(dir)*speed;
        ballYSpeed=Math.sin(dir)*speed;
    }

    public void action(){
        int fps = 50;
        for(int i = 0; i < numBalls; i++){
            ballArray[i].x += ballArray[i].getSpeedX()/getFPS();
            ballArray[i].y += ballArray[i].getSpeedY()/getFPS();

            if(ballArray[i].x < BORDER || ballArray[i].x+ballArray[i].r > getHeight()-BORDER){
                ballArray[i].setSpeedX(ballArray[i].getSpeedX()* -1);
            }
            if(ballArray[i].y < BORDER || ballArray[i].y+ballArray[i].r > getWidth()-BORDER){
                ballArray[i].setSpeedY(ballArray[i].getSpeedY() * -1);
            }
        }
        for(int h = 0; h < numBalls; h++){
            for(int k = h+1; k < numBalls; k++){
                if (ballArray[h].intersect(ballArray[k])) {
                    ballArray[h].collide(ballArray[k]);
                    collisionLogger.collide(ballArray[h],ballArray[k]);
                }
            }
        }
    }

    public void draw(Graphics g){
        //This method is called once every frame to draw the Frame.

        //This is how you use the graphics object to draw

        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.white);
        g.drawRect(BORDER,BORDER,getWidth()-BORDER*2,getHeight()-BORDER*2);
        for(int i = 0; i < numBalls; i++){
            g.setColor(ballArray[i].getColor());
            g.fillOval((int)ballArray[i].x, (int)ballArray[i].y, ballSize, ballSize);
        }
    }

    public void processKeyEvent(KeyEvent key){
        int keyCode = key.getKeyCode();

        for(int p = 0; p < numBalls; p++){
            if (key.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_RIGHT) {
                ballArray[p].setSpeedX(ballArray[p].getSpeedX()* 1.1);
                ballArray[p].setSpeedY(ballArray[p].getSpeedY()* 1.1);
            }else if(key.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_LEFT){
                ballArray[p].setSpeedX(ballArray[p].getSpeedX()* 0.9);
                ballArray[p].setSpeedY(ballArray[p].getSpeedY()* 0.9);
            }
        }
    }

    public static void main(String[] args){
        BallScreenSaver bss = new BallScreenSaver(800,800,"Ball", 20);
        bss.start();
    }
}
