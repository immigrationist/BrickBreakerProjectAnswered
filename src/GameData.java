import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class GameData
{
    
    //Constants related to the bricks in the game
    private final int BRICK_WIDTH  = 160;
    private final int BRICK_HEIGHT = 60;
    private final int NUM_BRICKS_ROWS = 5;
    private final int NUM_BRICKS_COLS = 3;
    //Constants related to the ball
    private final int BALL_RADIUS = 25;
    private final int BALL_INITIAL_SPEED = 3;
    private final Color BALL_COLOR = new Color(132, 35, 35);
    //Constants related to the paddle
    private final int PADDLE_WIDTH = 300;
    private final int PADDLE_HEIGHT = 60;
    //Constants for the possible game states
    public static final int GAMESTATE_INTRO = 0; 
    public static final int GAMESTATE_RUN = 1; 
    public static final int GAMESTATE_WIN = 2; 
    public static final int GAMESTATE_LOSE = 3; 
    
    private Paddle player;
    private Bricks bricks;
    private Ball ball;
    private int gameState;
    private int lives;
    private GameDisplay gameDisplay;
    
    public GameData(GameDisplay gameDisplay) 
    {
        Image brickImage = null, paddleImage = null;
        this.gameDisplay = gameDisplay;
        
        try 
        {
            //Loading all image resources from jar file
            InputStream is = getClass().getResourceAsStream("resources/brick.png");
            brickImage = ImageIO.read(is);
            is = getClass().getResourceAsStream("/resources/paddle.png");
            paddleImage = ImageIO.read(is);
        } 
        catch (IOException ex) 
        {
            System.out.println("Failed to load images!!!");
        }
        
        Point displayCenter = new Point(gameDisplay.getBounds().width / 2, 
                                        gameDisplay.getBounds().height / 2);
        ball = new Ball(displayCenter, BALL_RADIUS, BALL_COLOR,
                        new Point(BALL_INITIAL_SPEED, BALL_INITIAL_SPEED) );
        
        Rectangle paddleBounds = new Rectangle(0, gameDisplay.getBounds().height - 2 * PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        player = new Paddle(paddleBounds, paddleImage);
        
        gameState = GAMESTATE_INTRO;
        lives = 9;
        
        //Generate array of bricks
        bricks = new Bricks();
        for (int y = 0; y < NUM_BRICKS_ROWS; y++) 
        {
            for (int x = 0; x < NUM_BRICKS_COLS; x++) 
            {
                Rectangle brickBounds = new Rectangle(x * BRICK_WIDTH, y * BRICK_HEIGHT, 
                                                      BRICK_WIDTH, BRICK_HEIGHT);
                bricks.add(new Brick(brickBounds, brickImage));
            }
        }
    }
    
    //Getters/Setters
    public int getGameState()
    {
        return gameState;
    }
    
    public void setGameState(int newState)
    {
        switch (newState)
        {
            case GAMESTATE_INTRO:
            case GAMESTATE_RUN:
            case GAMESTATE_WIN:
            case GAMESTATE_LOSE:
                 gameState = newState;
                 break;
        }
    }
    
    public Ball getBall() 
    {
        return ball;
    }
    public void setBall(Ball newBall) 
    {
        if (newBall != null)
            ball = newBall;
    }
    public Bricks getBricks() 
    {
        return bricks;
    }
    public Paddle getPaddle() 
    {
        return player;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public void setLives(int newLives)
    {
        if (newLives >= 0)
            lives = newLives;
    }
    
}
