package pl.bookmaker_project.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BettingTicket
{
    private int number;
    private BettingTicketType bettingTicketType;
    private Date creationDate;
    private ArrayList<Bet> bets;
    private BettingTicketStatus bettingTicketStatus;
    private Double prize;

    public BettingTicket(int number, BettingTicketType bettingTicketType, Date creationDate, ArrayList<Bet> bets, BettingTicketStatus bettingTicketStatus,Double prize)
    {
        this.number = number;
        this.bettingTicketType = bettingTicketType;
        this.creationDate = creationDate;
        this.bets = bets;
        this.bettingTicketStatus = bettingTicketStatus;
        this.prize = prize;
    }

    @Override
    public String toString()
    {
        if(BettingTicketStatus.IN_PROGRESS == bettingTicketStatus)
            return
                "  number:" + number + "\n" +
                ", bettingTicketType:" + bettingTicketType + "\n" +
                ", creationDate:" + dateFormatter(creationDate) + "\n" +
                ", bets:" + bets + "\n" +
                ", bettingTicketStatus:" + bettingTicketStatus + "\n"
                ;
        else
            return
                "  number:" + number + "\n" +
                ", bettingTicketType: " + bettingTicketType + "\n" +
                ", creationDate: " + dateFormatter(creationDate) + "\n" +
                ", bets: " + bets + "\n" +
                ", bettingTicketStatus: " + bettingTicketStatus + "\n" +
                "prize: " + prize
                ;


    }
    public String dateFormatter(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BettingTicketType getBettingTicketType() {
        return bettingTicketType;
    }

    public void setBettingTicketType(BettingTicketType bettingTicketType) {
        this.bettingTicketType = bettingTicketType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }


    public BettingTicketStatus getBettingTicketStatus() {
        return bettingTicketStatus;
    }

    public void setBettingTicketStatus(BettingTicketStatus bettingTicketStatus) {
        this.bettingTicketStatus = bettingTicketStatus;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }
}
