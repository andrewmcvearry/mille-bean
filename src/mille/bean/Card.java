package mille.bean;

public abstract class Card
{
    private final String name;
    private final CardImage image;
    
    public Card(String n, CardImage i)
    {
        name = n;
        image = i;
    }
    
    public String getName()
    {
        return name;
    }
    
    public CardImage getCardImage()
    {
        return image;
    }
}
