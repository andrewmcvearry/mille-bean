package mille.bean;

import java.util.ArrayList;

public class Deck
{
    private ArrayList<Card> cards;
    
    public Deck()
    {
        // TODO: change this
        CardImage defaultImage = new CardImage();
        
        cards.add(new AccidentCard(defaultImage));
        // fill in all Mille Bornes cards here
        // look on wikibook etc.
    }
    
    public void shuffle()
    {
        // rearrange all the cards in the deck
    }
    
    public Card getTopCard()
    {
        // returns the card at the top of the cards ArrayList
        return cards.get(cards.size() - 1);
    }
    
    public Card takeTopCard()
    {
        // returns the top card and removes it from the Deck
        Card topCard = getTopCard();
        cards.remove(cards.size() - 1);
        return topCard;
    }
}
