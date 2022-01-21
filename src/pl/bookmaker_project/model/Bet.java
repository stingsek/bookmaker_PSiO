package pl.bookmaker_project.model;

public class Bet
{
    private EventToBet eventToBet;
    private double stake;
    private PossibleResult possibleResult;

    public Bet(EventToBet eventToBet, double stake, PossibleResult possibleResult)
    {
        this.eventToBet = eventToBet;
        this.stake = stake;
        this.possibleResult = possibleResult;
    }

    public String toString()
    {
        return eventToBet.getParticipantA() + " - " + eventToBet.getParticipantB() + "/" + eventToBet.dateFormatter(eventToBet.getDate()) +
                "/" +  stake + "/" + possibleResult + "\n";

    }

}
