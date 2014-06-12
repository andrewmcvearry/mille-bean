package mille.bean;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mille.bean.CardTypes.*;

public class ComputerPlayer extends Player
{
    public ComputerPlayer(String n)
    {
        super(n);
    }
    
    @Override
    public void makePlay(ArrayList<Player> playerList)
    {
        int cardNumber = -1;
        int playerNumber = -1;
        
        outerloop:
        for (int i = 0; i < hand.size(); i++)
        {
            // only consider playing on self for positive/good cards
            if (hand.get(i) instanceof DistanceCard || hand.get(i) instanceof RemedyCard || hand.get(i) instanceof SafetyCard)
            {
                if (this.playIsLegalMove(hand.get(i)))
                {
                    // huge hack
                    for (int j = 0; j < playerList.size(); j++)
                    {
                        if (playerList.get(j) == this)
                        {
                            cardNumber = i + 1;
                            playerNumber = j + 1;
                            break outerloop;
                        }
                    }
                }
            }
            
            else
            {
                for (int j = 0; j < playerList.size(); j++)
                {
                    if (playerList.get(j).playIsLegalMove(hand.get(i)))
                    {
                        cardNumber = i + 1;
                        playerNumber = j + 1;
                        break outerloop;
                    }
                }
            }
        }
        
//            if (hand.get(i) instanceof SpeedLimitCard)
//            {
//                
//                    if (playerList.get(j).playIsLegalMove
//                    {
//                        cardNumber = i + 1;
//                        playerNumber = j + 1;
//                        break outerloop;
//                    }
//                }
//            }
//            
//            else if (hand.get(i) instanceof HazardCard && !(hand.get(i) instanceof SpeedLimitCard))
//            {
//                for (int j = 0; j < playerList.size(); j++)
//                {
//                    if (playerList.get(j).getLastBattleAreaCard() instanceof RollCard)
//                    {
//                        if (playerList.get(j).playIsLegalMove(null))
//                        cardNumber = i + 1;
//                        playerNumber = j + 1;
//                        break outerloop;
//                    }
//                }
//            }
//
//            else if (hand.get(i) instanceof RemedyCard || hand.get(i) instanceof DistanceCard
//                     || hand.get(i) instanceof SafetyCard)
//            {
//                if (playIsLegalMove(hand.get(i)))
//                {

//                }
        
        // if no other plays, discard the first card
        if (cardNumber == -1)
        {
            cardNumber = 1;
            playerNumber = 5;
        }
        
        try
        {
            if (playerNumber == 5)
            {
                // discard
                hand.remove(cardNumber - 1);
            }
            
            else
            {
                playerList.get(playerNumber - 1).receiveCard(getHand().get(cardNumber - 1));
                hand.remove(cardNumber - 1);
            }
        }
        catch (IllegalPlayException ex)
        {
            Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
}
