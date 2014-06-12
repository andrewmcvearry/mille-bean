package mille.bean;

import java.util.ArrayList;
import java.util.HashMap;
import mille.bean.CardTypes.*;

public abstract class Player {
    protected final String name;
    protected ArrayList<Card> hand;
    protected ArrayList<Card> milePile;
    protected ArrayList<Card> battleAreaCards;
    protected ArrayList<Card> speedLimitAreaCards;
    protected HashMap<Card, Boolean> safetyCards;
    
    // needed to check for coup fourrees
    private Card lastCardPlayedUpon;
    
    public Player(String n)
    {
        name = n;
        hand = new ArrayList<>();
        milePile = new ArrayList<>();
        battleAreaCards = new ArrayList<>();
        speedLimitAreaCards = new ArrayList<>();
        safetyCards = new HashMap<>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public Card getLastBattleAreaCard()
    {
        if (!battleAreaCards.isEmpty())
        {
            return battleAreaCards.get(battleAreaCards.size() - 1);
        }
        
        return null;
    }
    
    public Card getLastSpeedLimitAreaCard()
    {
        if (!speedLimitAreaCards.isEmpty())
        {
            return speedLimitAreaCards.get(speedLimitAreaCards.size() - 1);
        }
        
        return null;
    }
    
    public HashMap<Card, Boolean> getSafetyCards()
    {
        return safetyCards;
    }
    
    // Different for computer and human
    public abstract void makePlay(ArrayList<Player> playerList);
    
    public void take6Cards(Deck deck)
    {
        for (int i = 0; i < 6; i++)
        {
            hand.add(deck.takeTopCard());
        }
    }
    
    public void takeCard(Deck deck)
    {
        if (!deck.isEmpty())
        {
            hand.add(deck.takeTopCard());
        }
    }
    
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
            else if (card instanceof HazardCard || card instanceof RemedyCard)
            {
                battleAreaCards.add(card);
            }
            
            else if (card instanceof DistanceCard)
            {
                milePile.add(card);
            }
            
            else if (card instanceof SafetyCard)
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
    
    public boolean playIsLegalMove(Card card)
    {
        if (card instanceof SpeedLimitCard)
        {
            if (!speedLimitAreaCards.isEmpty())
            {
                Card lastSpeedLimitCard = speedLimitAreaCards.get(speedLimitAreaCards.size() - 1);
                
                // can't play one speed limit card on top of another
                if (lastSpeedLimitCard instanceof SpeedLimitCard)
                {
                    return false;
                }
            }
        }
        
        else if (card instanceof HazardCard)
        {
            if (battleAreaCards.isEmpty())
            {
                return false;
            }
            
            else
            {
                Card lastBattleAreaCard = battleAreaCards.get(battleAreaCards.size() - 1);
                
                // only time a hazard card can be played is when roll card is on top
                if (!(lastBattleAreaCard instanceof RollCard))
                {
                    return false;
                }
            }
        }
        
        else if (card instanceof EndOfLimitCard)
        {
            if (speedLimitAreaCards.isEmpty())
            {
                return false;
            }
            else
            {
                Card lastSpeedLimitCard = speedLimitAreaCards.get(speedLimitAreaCards.size() - 1);
                
                // can't play one end of limit card on top of another
                if (lastSpeedLimitCard instanceof EndOfLimitCard)
                {
                    return false;
                }
            }
        }
                
        else if (card instanceof RemedyCard)
        {
            if (battleAreaCards.isEmpty())
            {
                return false;
            }
            
            else
            {
                Card lastBattleAreaCard = battleAreaCards.get(battleAreaCards.size() - 1);
                
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