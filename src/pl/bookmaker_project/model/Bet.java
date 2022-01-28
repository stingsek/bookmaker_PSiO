package pl.bookmaker_project.model;

import java.io.Serial;
import java.io.Serializable;

public class Bet implements Serializable
{
    @Serial
    private static final long serialVersionUID = 3982716114649589033L;
    private final EventToBet eventToBet;
    private final PossibleResult possibleResult;


    public Bet(EventToBet eventToBet, PossibleResult possibleResult)
    {
        this.eventToBet = eventToBet;
        this.possibleResult = possibleResult;
    }


    public String toString()
    {
        return eventToBet.getParticipantA() + " - " + eventToBet.getParticipantB() + "/" + eventToBet.dateFormatter(eventToBet.getDate()) +
                "/" +  possibleResult + "\n";
    }


    public EventToBet getEventToBet()
    {
        return eventToBet;
    }


    public PossibleResult getPossibleResult()
    {
        return possibleResult;
    }
}
