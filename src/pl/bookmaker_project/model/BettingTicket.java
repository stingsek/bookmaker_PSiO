package pl.bookmaker_project.model;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BettingTicket implements Serializable {
    @Serial
    private static final long serialVersionUID = 6940111521214511935L;
    private final int number;
    private final BettingTicketType bettingTicketType;
    private final Date creationDate;
    private final ArrayList<Bet> bets;
    private final Double stake;
    private final Double totalOdd;
    private BettingTicketStatus bettingTicketStatus;
    private Double prize;


    public BettingTicket(int number, BettingTicketType bettingTicketType, Date creationDate, ArrayList<Bet> bets, BettingTicketStatus bettingTicketStatus, Double prize, Double stake, Double totalOdd) {
        this.number = number;
        this.bettingTicketType = bettingTicketType;
        this.creationDate = creationDate;
        this.bets = bets;
        this.bettingTicketStatus = bettingTicketStatus;
        this.prize = prize;
        this.stake = stake;
        this.totalOdd = totalOdd;
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
                ", bettingTicketStatus:" + bettingTicketStatus + "\n" +
                ", bettingTicketStatus:" + stake + "\n" +
                ", total Odd:" + totalOdd;


        else
            return
                "  number:" + number + "\n" +
                ", bettingTicketType: " + bettingTicketType + "\n" +
                ", creationDate: " + dateFormatter(creationDate) + "\n" +
                ", bets: " + bets + "\n" +
                ", bettingTicketStatus: " + bettingTicketStatus + "\n" +
                "prize: " + prize + "\n" +
                ", bettingTicketStatus:" + stake + "\n" +
                ", total Odd:" + totalOdd;


    }


    public String dateFormatter(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }


    public int getNumber() {
        return number;
    }


    public BettingTicketType getBettingTicketType() {
        return bettingTicketType;
    }


    public Date getCreationDate() {
        return creationDate;
    }


    public ArrayList<Bet> getBets() {
        return bets;
    }


    public BettingTicketStatus getBettingTicketStatus() {
        return bettingTicketStatus;
    }


    public void setBettingTicketStatus(BettingTicketStatus bettingTicketStatus)
    {
        this.bettingTicketStatus = bettingTicketStatus;
    }


    public Double getStake()
    {
        return stake;
    }


    public Double getPrize()
    {
        return prize;
    }


    public Double getTotalOdd()
    {
        return totalOdd;
    }


    public void setPrize(Double prize) {
        this.prize = prize;
    }
}
