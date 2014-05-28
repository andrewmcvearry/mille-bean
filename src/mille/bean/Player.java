package mille.bean;

import java.util.ArrayList;
import java.util.Map;
import mille.bean.CardTypes.*;

public class Player {
    private final String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> milePile;
    private ArrayList<Card> battleAreaCards;
    private ArrayList<Card> speedLimitAreaCards;
    private ArrayList<Map<Card, Boolean>> safetyCards;
    
    public Player(String n)
    {
        name = n;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void playCardUpon(Card card)
    {
        if (playCardUponIsLegalMove(card))
        {
            if (card ins)
        }
        if (card instanceof SpeedLimitCard)
        {
            if (playCardUponIsLegalMove(card))
            {
                
            }
        }
    }
    
    public boolean playCardUponIsLegalMove(Card card)
    {
        if (card instanceof SpeedLimitCard)
        {
            if
        }
    }
    
    public int getTotalPoints()
    {
        // adds up all miles in milePile
        // and other things that would give you points
        return 0;
    }
    
    
}