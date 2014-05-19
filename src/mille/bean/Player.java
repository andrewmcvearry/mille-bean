package mille.bean;

import java.util.ArrayList;
import java.util.Map;

public class Player {
    private String name;
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
    
    public void cardPlayed(Card card)
    {
        // program logic here
    }
    
    public int getTotalPoints()
    {
        // adds up all miles in milePile
        // and other things that would give you points
        return 0;
    }
    
    
}
