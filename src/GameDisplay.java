import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameDisplay extends JPanel
{
    private GameData gameData;
    private Image background;
    private MouseHandler mouseHandler;
    
    public GameDisplay(int width, int height, GameData gameData) 
    {
        super();
        mouseHandler = new MouseHandler(gameData);
        
        this.gameData = gameData;
        setLayout(null);
        setBounds(0, 0, width, height);
        setFocusable(true);
        setVisible(true);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        
        try 
        {
            InputStream is = getClass().getResourceAsStream("/resources/background.png");
            background = ImageIO.read(is);
        } 
        catch (IOException ex) 
        {
            System.out.println("Failed to load background!!!");
        }
       
    }
        
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        if(gameData.getGameState() == GameData.GAMESTATE_RUN) 
        {    
            //Draw Background
            g.drawImage(background, 0, 0, getBounds().width, getBounds().height, this);
            g.setFont( new Font("TimesRoman", Font.PLAIN, 40));
            g.drawString("Lives: " + gameData.getLives(), (int)(getBounds().width / 6) , (int)(getBounds().height / 2));
            
            //Draw Paddle
            gameData.getPaddle().draw(g);
            
            //Draw Ball
            gameData.getBall().draw(g);
            
            //Draw Bricks
            gameData.getBricks().draw(g);
            
        } 
        else 
        {
            String message = "";
            g.setFont( new Font("TimesRoman", Font.PLAIN, 20));
            
            if(gameData.getGameState() == GameData.GAMESTATE_LOSE) 
            {
                message = "You Lost..";
            } 
            else if(gameData.getGameState() == GameData.GAMESTATE_WIN) 
            {
                message = "You Win,\n congrats!";
            } 
            else if(gameData.getGameState() == GameData.GAMESTATE_INTRO) 
            {
                 message = "Click to start";
            }
            g.drawString(message, (int)(getBounds().width / 3), (int)(getBounds().height / 2));
        }
    }
    
    //Handle the different cases when the ball has gone off screen.
    public boolean handleBallOffLimits(Ball ball)
    {  
        Point ballPosition = ball.getPosition();
        Point ballVelocity = ball.getVelocity();
        int ballRadius = ball.getRadius();
    
        //Handle case when ball exceeds left or right bound of the display
        if (ballPosition.x < 0 || ballPosition.x + 2 * ballRadius > getBounds().width) 
            ballVelocity.x *= -1;
        else if(ballPosition.y < 0) //handles case when ball goes off top of screen
            ballVelocity.y *= -1;
        else if(ballPosition.y > getBounds().height) 
        {
            //If ball went off the bottom of the screen then reset ball position
            //back to center and decrement lives by 1
            ballPosition.x = getBounds().width  / 2;
            ballPosition.y = getBounds().height / 2;
            getGameData().setLives(getGameData().getLives() - 1);
        }
        else
            return false;
        
        return true;
    }

    public GameData getGameData()
    {
        return gameData;
    }

    public void setGameData(GameData gameData)
    {
        if (gameData != null)
        {
            this.gameData = gameData;
            mouseHandler.setGameData(gameData);
        }
    }

}
