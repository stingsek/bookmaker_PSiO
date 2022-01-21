package pl.bookmaker_project.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventToBet
{
    private Date date;
    private String participantA;
    private String participantB;
    private SportType sportType;
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
        if (result == null)
        {
            return participantA + " - " +
                    participantB + "    " +
                    dateFormatter(date) + "    " +
                    sportType + "   " +
                    oddA + "    " +
                    oddB
                    ;
        } else
        {
            return participantA + " - " +
                    participantB + "    " +
                    dateFormatter(date) + "    " +
                    sportType + "   " +
                    oddA + "    " +
                    oddB + "    " +
                    result
                    ;
        }


    }

    public String toStringWithoutOdds()
    {
        if (result == null)
        {
            return participantA + " - " +
                    participantB + "    " +
                    dateFormatter(date) + "    " +
                    sportType
                    ;
        } else
        {
            return participantA + " - " +
                    participantB + "    " +
                    dateFormatter(date) + "    " +
                    sportType + "    " +
                    result
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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParticipantA() {
        return participantA;
    }

    public void setParticipantA(String participantA) {
        this.participantA = participantA;
    }

    public String getParticipantB() {
        return participantB;
    }

    public void setParticipantB(String participantB) {
        this.participantB = participantB;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public double getOddA() {
        return oddA;
    }

    public void setOddA(double oddA) {
        this.oddA = oddA;
    }

    public double getOddB() {
        return oddB;
    }

    public void setOddB(double oddB) {
        this.oddB = oddB;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
