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

public class DrivingAceCard extends SafetyCard
{
    public DrivingAceCard(CardImage image)
    {
        super("Driving Ace", image);
    }
}

public class ExtraTankCard extends SafetyCard
{
    public ExtraTankCard(CardImage image)
    {
        super("Extra Tank", image);
    }
}

public class PunctureProofCard extends SafetyCard
{
    public PunctureProofCard(CardImage image)
    {
        super("Punture-proof", image);
    }
}

public class RightOfWayCard extends SafetyCard
{
    public RightOfWayCard(CardImage image)
    {
        super("Right of Way", image);
    }
}

public class Distance25Card extends DistanceCard
{
    public Distance25Card(CardImage image)
    {
        super("25 km", image);
    }
}

public class Distance50Card extends DistanceCard
{
    public Distance50Card(CardImage image)
    {
        super("50 km", image);
    }
}

public class Distance75Card extends DistanceCard
{
    public Distance75Card(CardImage image)
    {
        super("75 km", image);
    }
}

public class Distance100Card extends DistanceCard
{
    public Distance100Card(CardImage image)
    {
        super("100 km", image);
    }
}

public class Distance200Card extends DistanceCard
{
    public Distance200Card(CardImage image)
    {
        super("200 km", image);
    }
}