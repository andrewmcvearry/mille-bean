package mille.bean;

import java.util.ArrayList;
import java.util.HashMap;
import mille.bean.CardTypes.*;

public abstract class Player {
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
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    // Will be different for computer and human
    public abstract void makePlay();
    
    public void receiveCard(Card card) throws IllegalPlayException
    {
        if (playIsLegalMove(card))
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
    
    @SuppressWarnings("empty-statement")
    public boolean playIsLegalMove(Card card)
    {
        Card lastSpeedLimitCard = speedLimitAreaCards.get(speedLimitAreaCards.size());
        Card lastBattleAreaCard = battleAreaCards.get(battleAreaCards.size());
        
        if (card instanceof SpeedLimitCard)
        {
            // can't play one speed limit card on top of another
            if (lastSpeedLimitCard instanceof SpeedLimitCard)
            {
                return false;
            }
        }
        
        else if (card instanceof HazardCard)
        {
            // only time a hazard card can be played is when roll card is on top
            if (!(lastBattleAreaCard instanceof RollCard))
            {
                return false;
            }
        }
        
        else if (card instanceof RemedyCard)
        {
            if (card instanceof RepairsCard && !(lastBattleAreaCard instanceof AccidentCard))
            {
                return false;
            }
            
            else if (card instanceof GasolineCard && !(lastBattleAreaCard instanceof OutOfGasCard))
            {
                return false;
            }
            
            else if (card instanceof SpareTireCard && !(lastBattleAreaCard instanceof FlatTireCard))
            {
                return false;
            }
            
            else if (card instanceof RollCard && lastBattleAreaCard instanceof HazardCard)
            {
                return false;
            }
        }
        
        else if (card instanceof DistanceCard)
        {
            if (((DistanceCard)(card)).getDistanceValue() + getTotalDistance() > 1000)
            {
                return false;
            }
        }
        
        else if (card instanceof SafetyCard)
        {
            // a safety card can always be played
            ;
        }
        
        return true;
    }
    
    public int getTotalDistance()
    {
        int totalDistance = 0;
        
        for (Card mileCard : milePile)
        {
            totalDistance += ((DistanceCard)(mileCard)).getDistanceValue();
        }
        
        return totalDistance;
    }
    
    public int getTotalPoints()
    {
        // adds up all miles in milePile
        // and other things that would give you points
        
        int totalDistance = getTotalDistance();
        
        return totalDistance;
    }
}