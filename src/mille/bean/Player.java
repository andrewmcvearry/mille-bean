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
    protected ArrayList<Card> safetyCards;
    
    // needed to check for coup fourrees
    private Card lastCardPlayedUpon;
    
    public Player(String n)
    {
        name = n;
        hand = new ArrayList<>();
        milePile = new ArrayList<>();
        battleAreaCards = new ArrayList<>();
        speedLimitAreaCards = new ArrayList<>();
        safetyCards = new ArrayList<>();
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
    
    public ArrayList<Card> getSafetyCards()
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
//                        safetyCards.put(card, true);
                        safetyCards.add(card);
                    }
                    
                    else if (lastCardPlayedUpon instanceof AccidentCard && card instanceof DrivingAceCard)
                    {
//                        safetyCards.put(card,true);
                        safetyCards.add(card);
                    }
                    
                    else if (lastCardPlayedUpon instanceof OutOfGasCard && card instanceof ExtraTankCard)
                    {
//                        safetyCards.put(card, true);
                        safetyCards.add(card);
                    }
                    
                    else if (lastCardPlayedUpon instanceof FlatTireCard && card instanceof PunctureProofCard)
                    {
//                        safetyCards.put(card, true);
                        safetyCards.add(card);
                    }
                }
                
                else
                {
//                    safetyCards.put(card, false);
                    safetyCards.add(card);
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
                // can't play one speed limit card on top of another
                if (getLastSpeedLimitAreaCard() instanceof SpeedLimitCard)
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
                // only time a hazard card can be played is when roll card is on top
                if (!(getLastBattleAreaCard() instanceof RollCard))
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
                // can't play one end of limit card on top of another
                if (getLastSpeedLimitAreaCard() instanceof EndOfLimitCard)
                {
                    return false;
                }
            }
        }
                
        else if (card instanceof RemedyCard)
        {
            if (battleAreaCards.isEmpty() && !(card instanceof RollCard))
            {
                return false;
            }
            
            else if (!battleAreaCards.isEmpty())
            {   
                if (card instanceof RepairsCard && !(getLastBattleAreaCard() instanceof AccidentCard))
                {
                    return false;
                }

                else if (card instanceof GasolineCard && !(getLastBattleAreaCard() instanceof OutOfGasCard))
                {
                    return false;
                }

                else if (card instanceof SpareTireCard && !(getLastBattleAreaCard() instanceof FlatTireCard))
                {
                    return false;
                }

                else if (card instanceof RollCard && !(getLastBattleAreaCard() instanceof StopCard
                         || getLastBattleAreaCard() instanceof RemedyCard))
                {
                    return false;
                }
            }
        }
        
        else if (card instanceof DistanceCard)
        {
            if (!(getLastBattleAreaCard() instanceof RollCard))
            {
                return false;
            }
            if (((DistanceCard)(card)).getDistanceValue() + getTotalDistance() > 700)
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
        int totalPoints = getTotalDistance();
        totalPoints += getSafetyCards().size() * 100;
        
        if (getSafetyCards().size() == 4)
        {
            totalPoints += 700;
        }
        
        if (getTotalDistance() == 700)
        {
            totalPoints += 400;
            
            boolean distance200Played = false;
            for (Card card : milePile)
            {
                if (card instanceof Distance200Card)
                {
                    distance200Played = true;
                }
            }
            
            if (distance200Played == false)
            {
                totalPoints += 300;
            }
            
            // shutout...
        }
        
        return totalPoints;
    }
}