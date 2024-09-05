import java.awt.Graphics;
import java.util.ArrayList;

public class Bricks extends ArrayList<Brick>
{
    public Bricks()
    {
        super();
    }
     
    //Checks if any brick in this set has collided with the ball.
    //If any brick has collided then removes that brick. It also updates
    //the speed of the ball to reflect the collision.
    //Returns true if any brick collided with the ball and false otherwise.
    public boolean handleCollisionWithBall(Ball ball)
    {
        boolean thereIsCollision = false;
        for (int i = 0; i < size(); i++)
        {
            Brick currentBrick = get(i);
            if (currentBrick.handleCollisionWithBall(ball))
            {
                remove(currentBrick);
                thereIsCollision = true;
                break;
            }   
        }
        return thereIsCollision;
    }
    
    public void draw(Graphics g)
    {
        //Iterate through brick array and draw all bricks
        for (int i = 0; i < size(); i++)
        {
            Brick currentBrick = get(i);
            currentBrick.draw(g);
        }
        
        //Here is an alternate shortcut way to iterate and draw all bricks
        //in the array by the way
//        for (Brick currentBrick : this)
//            currentBrick.draw(g);
        
    }
}
