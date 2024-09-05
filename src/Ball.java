import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball 
{
    private Point position;
    private int radius;
    private Point velocity;
    private Color ballColor;
     
    public Ball(Point initPos, int initRadius, Color initColor, Point initVelocity) 
    {
        position = initPos;
        radius = initRadius;
        velocity = initVelocity;
        ballColor = initColor; 
    }
   
    public Point getPosition()
    {
        return position;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public Point getVelocity()
    {
        return velocity;
    }
    
    public void setVelocity(Point newVelocity)
    {
        if (newVelocity != null)
            velocity = newVelocity;
    }

    /* Note that this method generates a completely new rectangle with the bounds of the ball,
    changing that rectangle will not change the position or the data of the ball object that
    generated it. */
    public Rectangle getRectangle()
    {
        Rectangle ballRectangle = new Rectangle(position.x, position.y,
                                          2 * radius, 2 * radius);
        return ballRectangle;
    }
    
    //Update function
    public void update() 
    {
        //Update ball position based on velocity
        position.x += velocity.x;
        position.y += velocity.y;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(ballColor);
        g.fillOval(position.x, position.y, 2 * radius, 2 * radius);
    }
    
    
      
    
}
