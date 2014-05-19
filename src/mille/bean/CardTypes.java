package mille.bean;

public abstract class HazardCard extends Card
{
    public HazardCard(String name, CardImage image)
    {
        super(name, image);
    }
}

public abstract class RemedyCard extends Card
{
    public RemedyCard(String name, CardImage image)
    {
        super(name, image);
    }
}

public abstract class SafetyCard extends Card
{
    public SafetyCard(String name, CardImage image)
    {
        super(name, image);
    }
}

public abstract class DistanceCard extends Card
{
    public DistanceCard(String name, CardImage image)
    {
        super(name, image);
    }
}
