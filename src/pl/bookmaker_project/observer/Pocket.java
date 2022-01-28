package pl.bookmaker_project.observer;
import pl.bookmaker_project.controller.MenuController;

import java.io.Serial;
import java.io.Serializable;

public class Pocket implements Observer, Serializable
{
    @Serial
    private static final long serialVersionUID = 8287146033447273527L;
    private double balance;

    public Pocket(double balance)
    {
        this.balance = balance;
    }


    @Override
    public void update(MenuController menuController,Observable observable)
    {

        if(observable.equals(menuController.getMenuFrame().getActualBettingTicketsPanel()))
        {
            for(int i = 0; i < menuController.getActualBettingTickets().size(); i++)
            {
                this.setBalance(menuController.roundNumbers(this.balance + menuController.getActualBettingTickets().get(i).getPrize()).doubleValue());
            }
        }

        else
        {
            this.setBalance(menuController.roundNumbers(this.balance - (menuController.getActualBettingTickets().get(menuController.getActualBettingTickets().size()-1).getStake())).doubleValue());
        }
    }


    public double getBalance()
    {
        return balance;
    }


    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
