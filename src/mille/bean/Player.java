package mille.bean;

import java.util.ArrayList;
import java.util.HashMap;
import mille.bean.CardTypes.*;

public class Player {
    private final String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> milePile;
    private ArrayList<Card> battleAreaCards;
    private ArrayList<Card> speedLimitAreaCards;
    private HashMap<Card, Boolean> safetyCards;
    
    // needed to check for coup fourrees
    private Card lastCardPlayedUpon;
    
    public Player(String n)
    {
        name = n;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void playCardUpon(Card card) throws IllegalPlayException
    {
        if (playCardUponIsLegalMove(card))
        {
            if (card instanceof SpeedLimitCard || card instanceof EndOfLimitCard)
            {
                speedLimitAreaCards.add(card);
            }
            
            // SpeedLimitCard and EndOfLimitCard can't be included here because
            // they go in a separate pile.
            if (card instanceof HazardCard || card instanceof RemedyCard &&
                !(card instanceof SpeedLimitCard || card instanceof EndOfLimitCard))
            {
                battleAreaCards.add(card);
            }
            
            if (card instanceof DistanceCard)
            {
                milePile.add(card);
            }
            
            if (card instanceof SafetyCard)
            {
                if (lastCardPlayedUpon instanceof HazardCard)
                {
                    if (lastCardPlayedUpon instanceof StopCard || lastCardPlayedUpon instanceof SpeedLimitCard &&
                        card instanceof RightOfWayCard)
                    {
                        safetyCards.put(card, true);
                    }
                    
                    else if (lastCardPlayedUpon instanceof AccidentCard && card instanceof DrivingAceCard)
                    {
                        safetyCards.put(card,true);
                    }
                    
                    else if (lastCardPlayedUpon instanceof OutOfGasCard && card instanceof ExtraTankCard)
                    {
                        safetyCards.put(card, true);
                    }
                    
                    else if (lastCardPlayedUpon instanceof FlatTireCard && card instanceof PunctureProofCard)
                    {
                        safetyCards.put(card, true);
                    }
                }
                
                else
                {
                    safetyCards.put(card, false);
                }
            }
        }
        
        else
        {
            throw new IllegalPlayException(card, "that is not a legal play");
        }
        
        lastCardPlayedUpon = card;
    }
    
    public boolean playCardUponIsLegalMove(Card card)
    {
        if (card instanceof SpeedLimitCard)
        {
            if ()
        }
    }
    
    public int getTotalPoints()
    {
        // adds up all miles in milePile
        // and other things that would give you points
        return 0;
    }
    
    
}