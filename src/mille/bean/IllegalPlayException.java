package mille.bean;

public class IllegalPlayException extends Exception
{
    public IllegalPlayException(Card errorCard, String message)
    {
        super(errorCard.getName() + ": " + message);
    }
}