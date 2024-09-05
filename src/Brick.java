import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Brick
{
    private Image brickImage;
    private Rectangle bounds;
    
    public Brick(Rectangle initBounds, Image initBrickImage) 
    {
        bounds = initBounds;
        brickImage = initBrickImage;
    }
    
    public Image getImage()
    {
        return brickImage;
    }
    
    //Check if this brick has collided with the ball (Returns true or false).
    //The method also internally updates the speed of the ball to make it
    //bounce from the brick, this depends on whether the ball hit the brick
    //horizontally or vertically.
    public boolean handleCollisionWithBall(Ball ball)
    {
        boolean thereIsCollision = false;
        
        Rectangle ballRectangle = ball.getRectangle();
        Rectangle intersection = bounds.intersection(ballRectangle);
        thereIsCollision = !(intersection.isEmpty());
        
        if (thereIsCollision)
        {
            Point ballVelocity = ball.getVelocity();

            //check for horizontal collision
            if ( bounds.x == intersection.x ||
                 (bounds.x + bounds.width) == (intersection.x + intersection.width))
                ballVelocity.x *= -1;

            //check for vertical collision
            if ( bounds.y == intersection.y ||
                 (bounds.y + bounds.height) == (intersection.y + intersection.height) )
                ballVelocity.y *= -1;
        }
        
        return thereIsCollision;
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(brickImage, bounds.x, bounds.y, bounds.width, bounds.height, null);
    }
    
}
