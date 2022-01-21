package pl.bookmaker_project.model;

import java.util.Date;

public class DrawableEvent extends EventToBet
{
    private double drawOdd;

    public DrawableEvent(Date date, String participantA, String participantB, SportType sportType, double oddA, double oddB, double drawOdd, Integer result)
    {
        super(date, participantA, participantB, sportType, oddA, oddB, result);
        this.drawOdd = drawOdd;
    }

    @Override
    public String toString()
    {
        if(getResult() == null)
        {
            return  getParticipantA() + " - " +
                    getParticipantB() + "   " +
                    dateFormatter(getDate()) + "    " +
                    getSportType() + "     " +
                    getOddA() + "   " +
                    getOddB() + "   " +
                    drawOdd
                    ;
        }


        else
        {
            return  getOddA() + " - " +
                    getOddB() + "   " +
                    dateFormatter(getDate()) + "    " +
                    getSportType() + "    " +
                    getOddA() + "   " +
                    getOddB() + "   " +
                    drawOdd + "    " +
                    getResult()
                    ;
        }

    }

    public double getDrawOdd()
    {
        return drawOdd;
    }
}
