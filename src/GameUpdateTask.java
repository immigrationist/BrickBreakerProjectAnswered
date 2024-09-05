import java.util.TimerTask;

public class GameUpdateTask extends TimerTask 
{

    private GameData gameData;
    private GameDisplay gameDisplay;
    
    public GameUpdateTask(GameDisplay gameDisplay, GameData gameData) 
    {
        this.gameDisplay = gameDisplay;
        this.gameData = gameData;
    }

    /* Whenever we are extending the abstract class TimerTask, we are forced to override
     * the run method. This method will be called repeatedly every time a certain time
     * interval ellapses, once the task is associated with a given timer. */
    @Override
    public void run() 
    {
        if(gameData.getGameState() == GameData.GAMESTATE_RUN) 
        {
            Bricks bricks = gameData.getBricks();
            Ball ball = gameData.getBall();
            Paddle paddle = gameData.getPaddle();
            
            //Update ball position
            ball.update();
            
            //Handle the situation when the ball goes off screen
            gameDisplay.handleBallOffLimits(ball);
            
            //Handle ball collision with bricks
            bricks.handleCollisionWithBall(ball);
                        
            //Handle ball collision with player paddle
            paddle.handleCollisionWithBall(ball);
            
            //Check if the game state has changed
             
            //If there are no more bricks then user has won the game
            if (bricks.isEmpty())
                gameData.setGameState(GameData.GAMESTATE_WIN);
            
            //If player has no more lives then its game over :(
            if (gameData.getLives() == 0)
                gameData.setGameState(GameData.GAMESTATE_LOSE);
   
        }
        //refresh the screen with the new updates
        gameDisplay.repaint();
    }
}
