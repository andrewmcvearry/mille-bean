package mille.bean;

public abstract class Card
{
    private String name;
    private CardImage image;
    
    public Card(String n, CardImage i)
    {
        name = n;
        image = i;
    }
}
