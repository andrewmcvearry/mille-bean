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

public class AccidentCard extends HazardCard
{
    public AccidentCard(CardImage image)
    {
        super("Accident", image);
    }
}

public class OutOfGasCard extends HazardCard
{
    public OutOfGasCard(CardImage image)
    {
        super("Out of Gas", image);
    }
}

public class FlatTireCard extends HazardCard
{
    public FlatTireCard(CardImage image)
    {
        super("Flat Tire", image);
    }
}

public class SpeedLimitCard extends HazardCard
{
    public SpeedLimitCard(CardImage image)
    {
        super("Speed Limit", image);
    }
}

public class StopCard extends HazardCard
{
    public StopCard(CardImage image)
    {
        super("Stop", image);
    }
}