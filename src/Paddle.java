import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Paddle 
{
    private Image paddleImage;
    private Rectangle bounds;
    
    public Paddle(Rectangle initBounds, Image initPaddleImage) 
    {
        bounds = initBounds;
        paddleImage = initPaddleImage;
    }
     
    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        if (bounds != null)
            this.bounds = bounds;
    }
      
    //Check if the paddle has collided with the ball (Returns true or false).
    //The method also internally updates the velocity of the ball to make it bounce.
    public boolean handleCollisionWithBall(Ball ball)
    {
        boolean thereIsCollision = false;
        
        Rectangle ballRectangle = ball.getRectangle();
        Rectangle intersection = bounds.intersection(ballRectangle);
        thereIsCollision = !(intersection.isEmpty());
        
        if (thereIsCollision)
            ball.getVelocity().y *= -1;
        
        return thereIsCollision;
    }
     
    public void draw(Graphics g)
    {
         g.drawImage(paddleImage, getBounds().x, getBounds().y, getBounds().width, getBounds().height, null);
    }
    
}
