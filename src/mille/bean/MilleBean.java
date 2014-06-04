package mille.bean;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
        
        while (isGameOver(milleDeck, players))
        {
            for (Player player : players)
            {
                player.makePlay();
                displayBoard();
            }
        }
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
    
    public static void createBoard()
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
    
    public static void displayBoard()
    {
        //graphics code
    }
}