package mille.bean;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
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
        
        createDisplay();
        
        // MAIN LOOP
        while (!Display.isCloseRequested())
        { 
            Display.update();
            
            computerPlayer1.makePlay();
            Display.update();
            computerPlayer2.makePlay();
            Display.update();
            computerPlayer3.makePlay();

            int cardNumber = -1;
            int playerNumber = -1;
            
            boolean cardNumberObtained = false;
            boolean bothNumbersObtained = false;
            
            cardNumber = getNumberInput();
            cardNumberObtained = true;
            
            playerNumber = getNumberInput();
            bothNumbersObtained = true;
  
            System.out.println("Card number:" + cardNumber);
            System.out.println("Player number: " + playerNumber);
        }
        
        // end
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
    
    public static int getNumberInput()
    {
        // Keyboard.next() belongs somewhere in here
        int numberValue = -1;
        while (numberValue < 0)
        {
            char eventCharacter = Keyboard.getEventCharacter();

            // if character has a numeric representation
            if (Character.getNumericValue(eventCharacter) > 0)
            {
                numberValue = Character.getNumericValue(eventCharacter);
            }
        }
        
        return numberValue;
    }
}