package pl.bookmaker_project.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class DrawableEvent extends EventToBet implements Serializable
{
    @Serial
    private static final long serialVersionUID = 3643139112025118633L;
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
                    "1 ODD: " + getOddA() + "    " +
                    "2 ODD: " + getOddB() + "    " +
                    "Draw ODD: " + drawOdd
                    ;
        }
        else
        {
            return  getOddA() + " - " +
                    getOddB() + "   " +
                    dateFormatter(getDate()) + "    " +
                    getSportType() + "    " +
                    "1 ODD: " + getOddA() + "    " +
                    "2 ODD: " + getOddB() + "    " +
                    "Draw ODD: " + drawOdd + "    " +
                    getResult()
                    ;
        }

    }


    public double getDrawOdd()
    {
        return drawOdd;
    }
}
