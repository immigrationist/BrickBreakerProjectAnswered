import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class MouseHandler implements MouseInputListener
{
    private GameData gameData;
    
    public MouseHandler(GameData gameData)
    {
        this.gameData = gameData;
    }
    
    //getters/setters
    public GameData getGameData()
    {
        return gameData;
    }

    public void setGameData(GameData gameData)
    {
        if (gameData != null)
            this.gameData = gameData;
    }

    //Mouse event handlers
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (gameData.getGameState() == GameData.GAMESTATE_INTRO)
            getGameData().setGameState(GameData.GAMESTATE_RUN);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        //Move the center of the paddle to where the mouse position is
        Paddle paddle = getGameData().getPaddle();
        Rectangle paddleBounds = paddle.getBounds();
        paddleBounds.x = e.getX() - paddleBounds.width / 2;
    }
    
   
    
}
