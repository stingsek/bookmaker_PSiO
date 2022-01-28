package pl.bookmaker_project.model;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventToBet implements Serializable
{

    @Serial
    private static final long serialVersionUID = 1314078320455545535L;
    private final Date date;
    private final String participantA;
    private final String participantB;
    private final SportType sportType;
    private double oddA;
    private double oddB;
    private Integer result;



    public EventToBet(Date date, String participantA, String participantB, SportType sportType, double oddA, double oddB, Integer result) {
        this.date = date;
        this.participantA = participantA;
        this.participantB = participantB;
        this.sportType = sportType;
        this.oddA = oddA;
        this.oddB = oddB;
        this.result = result;
    }


    @Override
    public String toString()
    {
        {
            return participantA + " - " +
                    participantB + "    " +
                    dateFormatter(date) + "    " +
                    sportType + "   " +
                    "1 ODD: " + oddA + "    " +
                    "2 ODD: " + oddB
                    ;
        }
    }


    public String toStringWithoutOdds()
    {
        {
            return participantA + " - " +
                    participantB + "    " +
                    dateFormatter(date) + "    " +
                    sportType
                    ;
        }

    }


    public double getDrawOdd()
    {
        return 0;
    }


    public String dateFormatter(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }


    public Date getDate() {
        return date;
    }


    public String getParticipantA() {
        return participantA;
    }


    public String getParticipantB() {
        return participantB;
    }


    public SportType getSportType() {
        return sportType;
    }


    public double getOddA() {
        return oddA;
    }


    public double getOddB() {
        return oddB;
    }


    public Integer getResult() {
        return result;
    }


    public void setResult(Integer result) {
        this.result = result;
    }
}
