package mille.bean;

import java.util.ArrayList;
import java.util.Collections;
import mille.bean.CardTypes.*;

public class Deck
{
    private ArrayList<Card> cards;
    
    /**
     *  Builds the Deck by adding all the card types to the
     *  cards ArrayList
     */
    public Deck()
    {
        cards = new ArrayList<>();
        
        // TODO: change this
        String imagePath = System.getProperty("user.dir") + "/images/";
        
        // constructs the deck by filling in all the card types
        for (int i = 0; i < 3; i++) {
            cards.add(new AccidentCard(imagePath + "Accident.png"));
        }
        
        for (int i = 0; i < 3; i++) {
            cards.add(new OutOfGasCard(imagePath + "OutOfGas.png"));
        }
        
        for (int i = 0; i < 3; i++) {
            cards.add(new FlatTireCard(imagePath + "FlatTire.png"));
        }
        
        for (int i = 0; i < 4; i++) {
            cards.add(new SpeedLimitCard(imagePath + "SpeedLimit.png"));
        }
        
        for (int i = 0; i < 5; i++) {
            cards.add(new StopCard(imagePath + "Stop.png"));
        }
        
        for (int i = 0; i < 6; i++) {
            cards.add(new RepairsCard(imagePath + "Repairs.png"));
        }
        
        for (int i = 0; i < 6; i++) {
            cards.add(new GasolineCard(imagePath + "Gasoline.png"));
        }
        
        for (int i = 0; i < 6; i++) {
            cards.add(new SpareTireCard(imagePath + "SpareTire.png"));
        }
        
        for (int i = 0; i < 6; i++) {
            cards.add(new EndOfLimitCard(imagePath + "EndOfLimit.png"));
        }
        
        for (int i = 0; i < 14; i++) {
            cards.add(new RollCard(imagePath + "Roll.png"));
        }
        
        cards.add(new DrivingAceCard(imagePath + "DrivingAce.png"));
        cards.add(new ExtraTankCard(imagePath + "ExtraTank.png"));
        cards.add(new PunctureProofCard(imagePath + "Puncture-proof.png"));
        cards.add(new RightOfWayCard(imagePath + "RightOfWay.png"));
        
        for (int i = 0; i < 10; i++)
        {
            cards.add(new Distance25Card(imagePath + "Distance25.png"));
        }
        
        for (int i = 0; i < 10; i++)
        {
            cards.add(new Distance50Card(imagePath + "Distance50.png"));
        }
        
        for (int i = 0; i < 10; i++)
        {
            cards.add(new Distance75Card(imagePath + "Distance75.png"));
        }
        
        for (int i = 0; i < 12; i++)
        {
            cards.add(new Distance100Card(imagePath + "Distance100.png"));
        }
        
        for (int i = 0; i < 4; i++)
        {
            cards.add(new Distance200Card(imagePath + "Distance200.png"));
        }
    }
    
    public void shuffle()
    {
        // rearranges all the cards in the Deck
        Collections.shuffle(cards);
    }
    
    public Card getTopCard()
    {
        // returns the card at the top of the Deck
        return cards.get(cards.size() - 1);
    }
    
    public Card takeTopCard()
    {
        // returns the top card and removes it from the Deck
        Card topCard = getTopCard();
        cards.remove(cards.size() - 1);
        return topCard;
    }
    
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }
}
