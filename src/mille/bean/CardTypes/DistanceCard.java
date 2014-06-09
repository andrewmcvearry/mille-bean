package mille.bean.CardTypes;

import mille.bean.*;

public abstract class DistanceCard extends Card
{
    private final int distanceValue;
    
    public DistanceCard(String name, String imagePath, int distValue)
    {
        super(name, imagePath);
        distanceValue = distValue;
    }
    
    public int getDistanceValue()
    {
        return distanceValue;
    }
}