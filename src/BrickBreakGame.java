import java.util.Timer;
import javax.swing.JFrame;

public class BrickBreakGame extends JFrame 
{
    private static final int UPDATES_PER_SECOND = 60;
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 1000;
    private GameDisplay gameDisplay;
    private GameData gameData;

    public BrickBreakGame()
    {
        super();
        //Initialize the window (JFrame) where the application runs
        setLayout(null);
        setTitle("Brick Break Game");
        setFocusable(true);
        setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true); 
        setResizable(false);

        //Initialize JPanel where the actual game is rendered
        gameDisplay = new GameDisplay(WINDOW_WIDTH, WINDOW_HEIGHT, gameData);
        gameData = new GameData(gameDisplay);
        gameDisplay.setGameData(gameData);
        //We must add the JPanel to this window
        add(gameDisplay);
    }
    
    public static void main(String[] args)
    {
        BrickBreakGame brickBreakGame = new BrickBreakGame();
        GameUpdateTask task = new GameUpdateTask(brickBreakGame.gameDisplay, brickBreakGame.gameData);
        Timer timer = new Timer();
        //Set the time before starting tasks to 0 and the delay between tasks
        //to a fraction of a second that will give us as many tasks per 
        //second as updates we want to perform (the task updates the game)
        int intervalInMilliSecs = 1000 / BrickBreakGame.UPDATES_PER_SECOND;
        timer.schedule(task, 0, intervalInMilliSecs);
    }
}
