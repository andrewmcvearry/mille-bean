package mille.bean;


public class Player {
    private String name;
    private Hand hand;
    private milePile milePile;
    private BattleAreaCards battleAreaCards;
    private SpeedLimitAreaCards speedLimitAreaCards;
    
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
}
