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

public class RepairsCard extends RemedyCard
{
    public RepairsCard(CardImage image)
    {
        super("Repairs", image);
    }
}

public class GasolineCard extends RemedyCard
{
    public GasolineCard(CardImage image)
    {
        super("Gasoline", image);
    }
}

public class SpareTireCard extends RemedyCard
{
    public SpareTireCard(CardImage image)
    {
        super("Spare Tire", image);
    }
}

public class EndOfLimitCard extends RemedyCard
{
    public EndOfLimitCard(CardImage image)
    {
        super("End of Limit", image);
    }
}

public class RollCard extends RemedyCard
{
    public RollCard(CardImage image)
    {
        super("Roll", image);
    }
}

