package mille.bean;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class HumanPlayer extends Player
{
    public HumanPlayer(String n)
    {
        super(n);
    }
    
    @Override
    public void makePlay()
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

        System.out.println("Card number:" + cardNumber);
        System.out.println("Player number: " + playerNumber); 
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
                    return Character.getNumericValue(eventCharacter);
                }
            }
        }
        
        // no character
        return -1;
    }
}
