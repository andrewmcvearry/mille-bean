package mille.bean;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class MilleBean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck milleDeck = new Deck();
        milleDeck.shuffle();
        
        // TODO: get player name
        String playerName = "";
        String computerPlayer1Name = "";
        String computerPlayer2Name = "";
        String computerPlayer3Name = "";
        
        HumanPlayer humanPlayer = new HumanPlayer(playerName);
        ComputerPlayer computerPlayer1 = new ComputerPlayer(computerPlayer1Name);
        ComputerPlayer computerPlayer2 = new ComputerPlayer(computerPlayer2Name);
        ComputerPlayer computerPlayer3 = new ComputerPlayer(computerPlayer3Name);
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(humanPlayer);
        players.add(computerPlayer1);
        players.add(computerPlayer2);
        players.add(computerPlayer3);
        
        createDisplay();
        
        // MAIN LOOP
        while (!Display.isCloseRequested())
        {
            updateDisplay();
            computerPlayer1.makePlay();
            updateDisplay();
            computerPlayer2.makePlay();
            updateDisplay();
            computerPlayer3.makePlay();
            updateDisplay();
            humanPlayer.makePlay();
        }
        Display.destroy();
    }
    
    public static boolean isGameOver(Deck deck, ArrayList<Player> players)
    {
        if (deck.isEmpty())
        {
            return true;
        }      
        else if(/*game conditions*/ false)
        {
            return true;
        }
        
        return true;
    }
     
    public static void createDisplay()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public static void updateDisplay()
    {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(100, 100);
        GL11.glVertex2f(100 + 200, 100);
        GL11.glVertex2f(100 + 200, 100 + 200);
        GL11.glVertex2f(100, 100 + 200);
        GL11.glEnd();
    }
}