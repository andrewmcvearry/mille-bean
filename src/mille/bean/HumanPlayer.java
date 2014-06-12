package mille.bean;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class HumanPlayer extends Player
{
    public HumanPlayer(String n)
    {
        super(n);
    }
    
    @Override
    public void makePlay(ArrayList<Player> playerList)
    {
        int cardNumber = -1;
        int playerNumber = -1;

        while (cardNumber == -1)
        {
            cardNumber = getNumberInput();
        }

        while (playerNumber == -1)
        {
            playerNumber = getNumberInput();
        }

        try
        {
            playerList.get(playerNumber - 1).receiveCard(getHand().get(cardNumber - 1));
            hand.remove(cardNumber - 1);
        }
        catch (IllegalPlayException ex)
        {
            Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
    }
    
    private int getNumberInput()
    {
        Display.processMessages();
        
        // if an event exists
        if (Keyboard.next())
        {
            // if event is a keypress (i.e., not a key release)
            if (Keyboard.getEventKeyState())
            {
                char eventCharacter = Keyboard.getEventCharacter();

                // if character has a numeric representation
                if (Character.getNumericValue(eventCharacter) > 0)
                {
                    // if number corresponds to a player
                    if (Character.getNumericValue(eventCharacter) < 5)
                    {
                        return Character.getNumericValue(eventCharacter);
                    }
                }
            }
        }
        
        // no character
        return -1;
    }
}
