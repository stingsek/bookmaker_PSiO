package pl.bookmaker_project.controller;

import pl.bookmaker_project.ReadingEventsFromTXT;
import pl.bookmaker_project.model.*;
import pl.bookmaker_project.oddStrategy.Odd;
import pl.bookmaker_project.view.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class MenuController
{
    //related to model:
    private Bet bet;
    private BettingTicket bettingTicket;
    private EventToBet eventToBet;
    private Pocket pocket;
    private ArrayList<BettingTicket> actualBettingTickets;
    private Odd oddStrategy;

    private ArrayList<BettingTicket> playedBettingTickets;
    private ArrayList<EventToBet> selectedEvents;
    private static double oddValueLimit = 1000;
    private static double potentialPrizeLimit = 5000000;

    //related to view
    private MenuFrame menuFrame;
    private TicketCreatorPanel ticketCreatorPanel;


    public MenuController()
    {
        getEvents();





        this.pocket = new Pocket(10000);


        //todo najpierw ładuje dane w kontrolerze.
        //ArrayList chosenEvents = //todo getChosenEvents;

        ArrayList<Bet> bets = new ArrayList<>();
        Bet bet1 = new Bet(new EventToBet(new Date(),"Barcelona","Real", SportType.FOOTBALL,4,2,2),50,PossibleResult.DRAW);
        Bet bet2 = new Bet(new EventToBet(new Date(),"Atletico","Villareal",SportType.FOOTBALL,5,2,null),40,PossibleResult.WON_B);
        bets.add(bet1);
        bets.add(bet2);
        this.actualBettingTickets = new ArrayList<>();
        BettingTicket bettingTicket1 = new BettingTicket(45455,BettingTicketType.AKO,(new Date()),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket3 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket4 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket5 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket6 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket7 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket8 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket9 = new BettingTicket(45455,BettingTicketType.AKO,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        BettingTicket bettingTicket2 = new BettingTicket(45455,BettingTicketType.COMBI,new Date(),bets,BettingTicketStatus.IN_PROGRESS,null);
        actualBettingTickets.add(bettingTicket1);
        actualBettingTickets.add(bettingTicket2);
        actualBettingTickets.add(bettingTicket3);
        actualBettingTickets.add(bettingTicket4);
        actualBettingTickets.add(bettingTicket5);
        actualBettingTickets.add(bettingTicket6);
        actualBettingTickets.add(bettingTicket7);
        actualBettingTickets.add(bettingTicket8);
        actualBettingTickets.add(bettingTicket9);
        this.playedBettingTickets = new ArrayList<>();
        playedBettingTickets.add(bettingTicket1);


        this.menuFrame = new MenuFrame(this);
    }


    public void confirmBettingTicket()
    {
        JOptionPane.showMessageDialog(null,"You've created a new betting ticket! ","creating Betting Ticket",JOptionPane.INFORMATION_MESSAGE);
    }


    public void returnToMainMenu()
    {
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Menu Panel");
    }


    public void displayBalance()
    {
        JOptionPane.showMessageDialog(menuFrame.getMenu(),"Your balance equals: " + pocket.getBalance(),"Show Balance",JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayActualTickets()
    {
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Actual Tickets Panel");
    }

    public void displayPlayedTickets()
    {
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Played Tickets Panel");
    }

    public ArrayList<EventToBet> getEvents()
    {
        File file = new File("C:\\Users\\Michał Żądełek\\IdeaProjects\\bookmaker_PSiO\\src\\pl\\bookmaker_project\\events_list.txt");
        try
        {
            return ReadingEventsFromTXT.read(file);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public EventToBet getEventToBet()
    {
        return eventToBet;
    }


    public boolean isBalanceBiggerThanTotalStake()
    {
        if(getTotalStake() >= pocket.getBalance())
        {
            return false;
        }
        return true;
    }

    //public void create
    public void playBettingTickets()
    {
        playedBettingTickets.addAll(actualBettingTickets);
        actualBettingTickets.clear();
    }

    public int ticketNumberGenerator()
    {
        Random generator = new Random();
        return Math.abs(generator.nextInt(2000000000));
    }


    public void goToTicketCreationPanel()
    {

        getSelectedEvents();

        this.ticketCreatorPanel = new TicketCreatorPanel(this);

        menuFrame.getMainpanel().add(ticketCreatorPanel,"Ticket Creator Panel");

        int ticketNumber = ticketNumberGenerator();
        Date ticketCreationDate = new Date();


        ticketCreatorPanel.getlTicketNumber().setText("Ticket Number: " + ticketNumber);

        ticketCreatorPanel.getlDate().setText("Date: " + dateFormatter(ticketCreationDate));

        ticketCreatorPanel.getlBalance().setText("Balance: "+ pocket.getBalance());

        if(Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.SOLO))
        {
            ticketCreatorPanel.getlTicketType().setText("Ticket Type: " + BettingTicketType.SOLO);
        }
        else if(Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.AKO))
        {
            ticketCreatorPanel.getlTicketType().setText("Ticket Type: " + BettingTicketType.AKO);
        }
        else
        {
            ticketCreatorPanel.getlTicketType().setText("Ticket Type: " + BettingTicketType.COMBI);
        }


        if(!isAtLeastOneSelected())
        {
            showZeroElementsErrorWhileCreatingBettingTicket();
        }
        else if(!isTicketTypeCorrect())
        {
            showTicketTypeError();
        }
        else
        {
            menuFrame.getCard().show(menuFrame.getMainpanel(), "Ticket Creator Panel");
        }

        //public void createBet


    }

    public boolean isTotalOddBiggerThanZero()
    {
        if(countTotalOdd() > 0)
        {
            return true;
        }
        return false;
    }

    private void showTicketTypeError()
    {
        JOptionPane.showMessageDialog(null,"Incorrect ticket type for selected events","Ticket Type Error",JOptionPane.ERROR_MESSAGE);
    }

    public String dateFormatter(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }


    private void viewInit()
    {

    }

    private boolean isAtLeastOneSelected()
    {
        for (JCheckBox checkBox : menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList())
        {
            if (checkBox.isSelected())
            {
                return true;
            }
        }
            return false;
    }

    private void showZeroElementsErrorWhileCreatingBettingTicket()
    {
        JOptionPane.showMessageDialog(null, "Select at least one event!", "Warning", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isTicketTypeCorrect()
    {
        int selectedCheckboxesCounter = 0;
        int footballCounter = 0;
        int volleyballCounter = 0;
        int boxingCounter = 0;
        int tennisCounter = 0;
            for(JCheckBox checkBox : menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList())
            {
                if(checkBox.isSelected())
                {
                    selectedCheckboxesCounter++;
                }
            }
            if (selectedCheckboxesCounter == 1 && Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.SOLO))
            {
                return true;
            }
            else if(selectedCheckboxesCounter > 1 && Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.AKO))
            {
                return true;
            }
            else if (selectedCheckboxesCounter > 1 && Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.COMBI))
            {
                for (int i = 0; i < getSelectedEvents().size(); i++)
                {
                    if(getSelectedEvents().get(i).getSportType().equals(SportType.FOOTBALL))
                    {
                        footballCounter++;
                    }
                    else if(getSelectedEvents().get(i).getSportType().equals(SportType.VOLLEYBALL))
                    {
                        volleyballCounter++;
                    }
                    else if(getSelectedEvents().get(i).getSportType().equals(SportType.BOXING))
                    {
                        boxingCounter++;
                    }
                    else
                        tennisCounter++;
                }
                if((boxingCounter > 0 && footballCounter > 0) || (boxingCounter > 0 && volleyballCounter > 0) || (boxingCounter > 0 && tennisCounter > 0) || (tennisCounter > 0 && footballCounter > 0) || (volleyballCounter > 0 && footballCounter > 0) || (tennisCounter > 0 && volleyballCounter > 0))
                {
                    return true;
                }
            }
            return false;

    }

    public ArrayList<EventToBet> getSelectedEvents()
    {
        this.selectedEvents = new ArrayList<>();
        for (int i = 0; i < menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().size(); i++)
        {
            if (menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().get(i).isSelected())
                selectedEvents.add(getEvents().get(i));
        }
        return selectedEvents;

    }

    public boolean isInstanceOfDrawableEvent(EventToBet eventToBet)
    {
        if(eventToBet instanceof DrawableEvent)
        {
            return true;
        }
        return false;

    }


    public ArrayList<Bet> betsCreator()
    {
        ArrayList<Bet> bets = new ArrayList<>();
        for(int i = 0; i < getSelectedEvents().size(); i++)
        {
            PossibleResult possibleResult = null;
            if(ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("1"))
            {
                possibleResult = PossibleResult.WON_A;
            }
            else if(ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("X"))
            {
                possibleResult = PossibleResult.DRAW;
            }
            else if(ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("2"))
            {
                possibleResult = PossibleResult.WON_B;
            }
            else if(ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("1X"))
            {
                possibleResult = PossibleResult.B_NOT_WON;
            }
            else if(ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("12"))
            {
                possibleResult = PossibleResult.NOT_DRAW;
            }
            else
            {
                possibleResult = PossibleResult.A_NOT_WON;
            }

            Bet bet = new Bet(getSelectedEvents().get(i),getTotalStake(),possibleResult);
            bets.add(bet);
        }
        return bets;
    }



    public MenuFrame getMenuFrame()
    {
        return menuFrame;
    }


    public ArrayList<BettingTicket> getActualBettingTickets()
    {
        return actualBettingTickets;
    }

    public ArrayList<BettingTicket> getPlayedBettingTickets()
    {
        return playedBettingTickets;
    }

    private double countTotalOdd()
    {
        double totalOdd =  1;

        for( int i = 0; i < getSelectedEvents().size() ; i++)
        {
            if (ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("1"))
            {
                totalOdd *= getSelectedEvents().get(i).getOddA();
            }
            else if (ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("X"))
            {
                totalOdd *= getSelectedEvents().get(i).getDrawOdd();
            }
            else if (ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("2")) {
                totalOdd *= getSelectedEvents().get(i).getOddB();
            }
            else if (ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("1X"))
            {
                totalOdd *= get1XOdd(i);
            }
            else if (ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection().getActionCommand().equals("12"))
            {
                totalOdd *= get12Odd(i);
            }
            else
            {
                totalOdd *= getX2Odd(i);
            }
        }
        return roundNumbers(totalOdd).doubleValue();
    }



    private double getTotalStake()
    {
        return Double.parseDouble(ticketCreatorPanel.getTfStake().getText());
    }

    public void blockLettersInTextField(JTextField textField)
    {
        textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();

                if ((input < '0') || (Character.isLetter(input)))
                {
                    e.consume();
                }
            }
        });
    }


    private double countOverallPotentialPrize()
    {
        return roundNumbers(countTotalOdd()*Tax.taxValue * getTotalStake()).doubleValue();
    }

    public boolean areAllButtonsSelected()
    {
        for (int i = 0 ; i < getSelectedEvents().size(); i++)
        {
            if((ticketCreatorPanel.getBetInfoCreator().getButtonGroups().get(i).getSelection() == null))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isStakeBiggerThanZero()
    {
        if(getTotalStake() < 0)
        {
            return false;
        }
        return true;
    }


    public void setAdditionalOdds()
    {
        for(int i = 0; i < selectedEvents.size(); i++)
        {
            if(isInstanceOfDrawableEvent(selectedEvents.get(i)))
            ticketCreatorPanel.getBetInfoCreator().getOddLabels().get(i).setText("   1 ODD: " + selectedEvents.get(i).getOddA() +  "    X ODD: " + selectedEvents.get(i).getDrawOdd() + "    2 ODD: " + selectedEvents.get(i).getOddB() + "    1X ODD: " + get1XOdd(i) + "   12 ODD: " + get12Odd(i)  + "   X2 ODD: " + getX2Odd(i) + " ");
        }

    }

    public BigDecimal roundNumbers(double value1)
    {
         BigDecimal bd = new BigDecimal(value1).setScale(2, RoundingMode.HALF_UP);
         return bd;
    }

    public boolean isLimitBiggerThanPotentialPrize()
    {
        if(potentialPrizeLimit > countOverallPotentialPrize())
        {
            return true;
        }

        return false;

    }

    public void displayLimitOverrunningInformation()
    {
        JOptionPane.showMessageDialog(null,"Total Odd or Potential Prize is bigger than the limit!" + "\n" + "Total Odd Limit: " + oddValueLimit +  "\n" + "Potential Prize Limit: " + potentialPrizeLimit, "Limit overrunning", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isLimitBiggerThanTotalOdd()
    {
        if(oddValueLimit > countTotalOdd())
        {
            return true;
        }
        return false;

    }





    public double get1XOdd(int i)
    {
        return ((double) ((Math.round(((((getTotalStake() * getSelectedEvents().get(i).getOddA()) / (getSelectedEvents().get(i).getOddA() + getSelectedEvents().get(i).getDrawOdd())) * (getSelectedEvents().get(i).getOddA()) / 100)) * 100)) / 100));
    }

    public double get12Odd(int i)
    {
        return ((double) ((Math.round(((((getTotalStake() * getSelectedEvents().get(i).getOddA()) / (getSelectedEvents().get(i).getOddA() + getSelectedEvents().get(i).getOddB())) * (getSelectedEvents().get(i).getOddA()) / 100)) * 100)) / 100));
    }

    public double getX2Odd(int i)
    {
        return ((double) ((Math.round(((((getTotalStake() * getSelectedEvents().get(i).getOddB()) / (getSelectedEvents().get(i).getOddB() + getSelectedEvents().get(i).getDrawOdd())) * (getSelectedEvents().get(i).getOddB()) / 100)) * 100)) / 100));
    }

    public void setOverallPotentialPrize()
    {
        ticketCreatorPanel.getlPotentialPrize().setText("Potential Prize: " + countOverallPotentialPrize());
    }

    public void setActualTotalOdd()
    {
        ticketCreatorPanel.getlTotalOdd().setText("Total Odd: " + countTotalOdd());

    }

    public void setActualTotalStake()
    {
        ticketCreatorPanel.getlTotalStake().setText("Total Stake: " + getTotalStake());
    }




}
