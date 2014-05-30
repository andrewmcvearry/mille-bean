package mille.bean.CardTypes;

import mille.bean.*;

public abstract class DistanceCard extends Card
{
    private final int distanceValue;
    
    public DistanceCard(String name, CardImage image, int distValue)
    {
        super(name, image);
        distanceValue = distValue;
    }
    
    public int getDistanceValue()
    {
        return distanceValue;
    }
}