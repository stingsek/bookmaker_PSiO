package pl.bookmaker_project.controller;

import pl.bookmaker_project.oddStrategy.AmericanOdd;
import pl.bookmaker_project.oddStrategy.BritishOdd;
import pl.bookmaker_project.oddStrategy.DecimalOdd;
import pl.bookmaker_project.oddStrategy.Odd;
import pl.bookmaker_project.repository.ReadingEventsFromTXT;
import pl.bookmaker_project.repository.Repository;
import pl.bookmaker_project.model.*;
import pl.bookmaker_project.observer.Pocket;
import pl.bookmaker_project.view.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuController
{

    //related to model:

    private final ArrayList<EventToBet> availableEvents;
    private Pocket pocket;
    private final ArrayList<BettingTicket> actualBettingTickets;
    private Odd odd;
    private final ArrayList<BettingTicket> playedBettingTickets;
    private ArrayList<EventToBet> selectedEvents;
    private final static double oddValueLimit = 1000;
    private final static double potentialPrizeLimit = 5000000;

    //related to view:

    private final MenuFrame menuFrame;
    private TicketCreatorPanel ticketCreatorPanel;
    private ActualBettingTicketsPanel actualBettingTicketsPanel;
    private PlayedBettingTicketsPanel playedBettingTicketsPanel;
    private EventResultPanel eventResultPanel;

    //related to saved data:

    private final Repository<BettingTicket> repo;




    public MenuController()
    {
        //loading data from txt file and repository

        this.availableEvents = getEventsResult(readAvailableEvents());
        this.repo = new Repository<>(this);
        this.repo.readActual();
        this.repo.readPocketStatus();
        this.repo.readPlayed();

        this.actualBettingTickets = repo.getActualTicketsRepo();
        this.pocket = repo.getPocketRepo();
        this.playedBettingTickets = repo.getPlayedTicketsRepo();


        //creating view
        this.menuFrame = new MenuFrame(this);
    }



    // methods to display information // switch between panels

    public void displayBalance()
    {
        JOptionPane.showMessageDialog(menuFrame.getMenu(),"Your balance equals: " + pocket.getBalance(),"Show Balance",JOptionPane.INFORMATION_MESSAGE);
    }


    public void displayActualTickets()
    {
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Actual Tickets Panel");
    }


    public void displayLimitOverrunningInformation()
    {
        JOptionPane.showMessageDialog(null,"Total Odd or Potential Prize is bigger than the limit!" + "\n" + "Total Odd Limit: " + oddValueLimit +  "\n" + "Potential Prize Limit: " + potentialPrizeLimit, "Limit overrunning", JOptionPane.ERROR_MESSAGE);
    }


    public void displayResultsPanel()
    {
        this.eventResultPanel = new EventResultPanel(this);
        menuFrame.getMainpanel().add(eventResultPanel, "Event Result Panel");
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Event Result Panel");
    }


    public void displayPlayedTickets()
    {
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Played Tickets Panel");
    }


    private void showZeroElementsErrorWhileCreatingBettingTicket()
    {
        JOptionPane.showMessageDialog(null, "Select at least one event!", "Warning", JOptionPane.ERROR_MESSAGE);
    }



    public void returnToMainMenu()
    {
        menuFrame.getCard().show(menuFrame.getMainpanel(),"Menu Panel");
    }


    public void confirmBettingTicket()
    {
        JOptionPane.showMessageDialog(null,"You've created a new betting ticket! ","creating Betting Ticket",JOptionPane.INFORMATION_MESSAGE);
    }


    private void showTicketTypeError()
    {
        JOptionPane.showMessageDialog(null,"Incorrect ticket type for selected events","Ticket Type Error",JOptionPane.ERROR_MESSAGE);
    }




    // method which reads data from txt file
    public ArrayList<EventToBet> readAvailableEvents()
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




    // methods to check values/conditions
    private boolean isBalanceBiggerThanTotalStake()
    {
        if(getTotalStake() >= pocket.getBalance())
        {
            return false;
        }
        return true;
    }


    public boolean isLimitBiggerThanTotalOdd()
    {
        if(oddValueLimit > countTotalOdd())
        {
            return true;
        }
        return false;

    }

    public void tfStakeHandling()
    {
        setAdditionalOdds();

        if (areAllButtonsSelected())
        {
            setActualTotalOdd();
            setOverallPotentialPrize();
        }
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


    private boolean isTfStakeFilled()
    {
        if(ticketCreatorPanel.getTfStake().getText().length() == 0)
        {
            return false;
        }
        return true;
    }


    private boolean isAtLeastOneActualTicket()
    {
        return actualBettingTickets.size() > 0;
    }


    private boolean isTotalOddBiggerThanZero()
    {
        if(countTotalOdd() > 0)
        {
            return true;
        }
        return false;
    }


    private boolean isTicketWon(int i)
    {
        int counter = 0;
        for (int j = 0; j < actualBettingTickets.get(i).getBets().size(); j++)
        {
            if (actualBettingTickets.get(i).getBets().get(j).getEventToBet().getResult().equals(1))
            {
                if((actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.WON_A)) || (actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.B_NOT_WON)) || (actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.NOT_DRAW)))
                {
                    counter++;
                }
            }
            else if(actualBettingTickets.get(i).getBets().get(j).getEventToBet().getResult().equals(2))
            {
                if((actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.WON_B)) || (actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.A_NOT_WON)) || (actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.NOT_DRAW)))
                {
                    counter++;
                }

            }
            else
            {

                if ((actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.DRAW)) || (actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.A_NOT_WON)) || (actualBettingTickets.get(i).getBets().get(j).getPossibleResult().equals(PossibleResult.B_NOT_WON)))
                {
                    counter++;
                }
            }
        }
        if(counter == actualBettingTickets.get(i).getBets().size())
        {
            return true;
        }
        return false;
    }


    public boolean isLimitBiggerThanPotentialPrize()
    {
        if(potentialPrizeLimit > countOverallPotentialPrize())
        {
            return true;
        }

        return false;
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


    private boolean wasEnterPressed()
    {
        if(ticketCreatorPanel.getlTotalOdd().getText().length() > 10)
        {
            return true;
        }
        return false;
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


    private boolean isStakeBiggerThanZero()
    {
        if(getTotalStake() > 0)
        {
            return true;
        }
        return false;
    }


    public boolean isInstanceOfDrawableEvent(EventToBet eventToBet)
    {
        if(eventToBet instanceof DrawableEvent)
        {
            return true;
        }
        return false;

    }

    //methods related with action/creating features/strategy pattern

    private void checkForPrize()
    {
        for(int i = 0; i < actualBettingTickets.size(); i++)
        {
            if(isTicketWon(i))
            {
                Double pocket = roundNumbers(actualBettingTickets.get(i).getStake()*actualBettingTickets.get(i).getTotalOdd()*Tax.TAX_VALUE).doubleValue();
                actualBettingTickets.get(i).setPrize(pocket);
                actualBettingTickets.get(i).setBettingTicketStatus(BettingTicketStatus.WON);
            }
            else
            {
                actualBettingTickets.get(i).setPrize(0.0);
                actualBettingTickets.get(i).setBettingTicketStatus(BettingTicketStatus.LOST);
            }
        }
    }


    public void playBettingTickets()
    {
        if(isAtLeastOneActualTicket())
        {
            changeStateOfActualBettingTickets();

            checkForPrize();

            this.actualBettingTicketsPanel.notifyObservers();

            this.repo.savePocketStatus();

            playedBettingTickets.addAll(actualBettingTickets);

            actualBettingTickets.clear();

            this.repo.saveActual();

            this.repo.savePlayed();

            changeStateOfActualBettingTickets();

            changeStateOfPlayedBettingTickets();

            displayResultsPanel();

            getEventsResult(this.availableEvents);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"The list of actual betting tickets is empty!","0 elements in the list",JOptionPane.ERROR_MESSAGE);
        }


    }


    //////////////////////////////////////////// (strategy pattern)

    private double oddConverter(double odd)
    {
        if(menuFrame.getMenu().getRbDecimalOdd().isSelected())
        {
            this.odd = new DecimalOdd();
            return this.odd.convertOdd(odd);
        }

        else if(menuFrame.getMenu().getRbBritishOdd().isSelected())
        {
            this.odd = new BritishOdd();
            return this.odd.convertOdd(odd);
        }
        else if(menuFrame.getMenu().getRbAmericanOdd().isSelected())
        {
            this.odd = new AmericanOdd();
            return this.odd.convertOdd(odd);
        }
        return 0;
    }


    private void changeOddsInMenuPanel()
    {
        for(int i = 0; i < menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().size(); i++)
        {
            if(isInstanceOfDrawableEvent(availableEvents.get(i)))
            {
                menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().get(i).setText(getAvailableEvents().get(i).toStringWithoutOdds() + "   " + "1 ODD: " + roundNumbers(oddConverter(getAvailableEvents().get(i).getOddA())).doubleValue() + "    "  + "2 ODD: " + roundNumbers(oddConverter(getAvailableEvents().get(i).getOddB())).doubleValue() + "    " + "Draw ODD: " + roundNumbers(oddConverter(getAvailableEvents().get(i).getDrawOdd())).doubleValue());
            }
            else
            {
                menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().get(i).setText(getAvailableEvents().get(i).toStringWithoutOdds() + "   " + "1 ODD: " + roundNumbers(oddConverter(getAvailableEvents().get(i).getOddA())).doubleValue() + "    " + "2 ODD: " + roundNumbers(oddConverter(getAvailableEvents().get(i).getOddB())).doubleValue());
            }
        }
    }


    private void changeOddsInTicketCreationPanel()
    {
        for(int i = 0; i < ticketCreatorPanel.getBetInfoCreator().getOddLabels().size(); i++)
        {
            if(isInstanceOfDrawableEvent(getSelectedEvents().get(i)))
            {
                ticketCreatorPanel.getBetInfoCreator().getOddLabels().get(i).setText("   1 ODD: " + roundNumbers(oddConverter(getSelectedEvents().get(i).getOddA())).doubleValue() + "    X ODD: " + roundNumbers(oddConverter(getSelectedEvents().get(i).getDrawOdd())).doubleValue() + "    2 ODD: " + roundNumbers(oddConverter(getSelectedEvents().get(i).getOddB())).doubleValue() + "     1X ODD:    12 ODD:    X2 ODD:");
            }
            else
            {
                ticketCreatorPanel.getBetInfoCreator().getOddLabels().get(i).setText("    1 ODD: " + roundNumbers(oddConverter(getSelectedEvents().get(i).getOddA())).doubleValue() + "     2 ODD: " + roundNumbers(oddConverter(getSelectedEvents().get(i).getOddB())).doubleValue());
            }

        }
    }


    public void changeOdds()
    {
        changeOddsInMenuPanel();
        if(ticketCreatorPanel != null)
        {
            changeOddsInTicketCreationPanel();
        }
    }

    ////////////////////////////////

    public void goToTicketCreationPanel()
    {

        getSelectedEvents();

        this.ticketCreatorPanel = new TicketCreatorPanel(this);
        ticketCreatorPanel.registerObserver(pocket);
        changeOddsInTicketCreationPanel();


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



    }


    public void changeStateOfPlayedBettingTickets()
    {
        this.playedBettingTicketsPanel = new PlayedBettingTicketsPanel(this);
        menuFrame.setPlayedBettingTicketsPanel(playedBettingTicketsPanel);
        menuFrame.getMainpanel().add(playedBettingTicketsPanel, "Played Tickets Panel");
    }


    public void changeStateOfActualBettingTickets()
    {
        this.actualBettingTicketsPanel = new ActualBettingTicketsPanel(this);
        menuFrame.setActualBettingTicketsPanel(actualBettingTicketsPanel);
        actualBettingTicketsPanel.registerObserver(pocket);
        menuFrame.getMainpanel().add(actualBettingTicketsPanel, "Actual Tickets Panel");
    }


    public void goBackToMenuAfterCreatingBettingTicket()
    {
        if(isTfStakeFilled()) {
            if (isStakeBiggerThanZero()) {
                if (areAllButtonsSelected()) {
                    if (isBalanceBiggerThanTotalStake()) {
                        if (wasEnterPressed()) {
                            if (isTotalOddBiggerThanZero()) {
                                if (isLimitBiggerThanPotentialPrize() && isLimitBiggerThanTotalOdd())
                                {
                                    confirmBettingTicket();

                                    createBettingTicket();

                                    ticketCreatorPanel.notifyObservers();

                                    repo.savePocketStatus();

                                    changeStateOfActualBettingTickets();

                                    returnToMainMenu();

                                } else {
                                    displayLimitOverrunningInformation();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Total odd can not be 0", "Odd value problem", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Press Enter to confirm the stake!", "Confirming without pressing ENTER", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your Balance is smaller than total stake!", "Balance problem", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Every event has to have one button selected!", "Odd selection problem", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Total stake can not be 0", "Stake value problem", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Type a number in text field!", "Empty Text Field", JOptionPane.ERROR_MESSAGE);
        }

    }


    public ArrayList<Bet> betsCreator()
    {
        ArrayList<Bet> bets = new ArrayList<>();
        for(int i = 0; i < getSelectedEvents().size(); i++)
        {
            PossibleResult possibleResult;
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

            Bet bet = new Bet(getSelectedEvents().get(i),possibleResult);
            bets.add(bet);
        }
        return bets;
    }


    public void createBettingTicket()
    {
        String ticketNumberLabel = ticketCreatorPanel.getlTicketNumber().getText();
        String[] ticketNumber = ticketNumberLabel.split(": ");

        String ticketTotalOddLabel = ticketCreatorPanel.getlTotalOdd().getText();
        String[] totalOdd = ticketTotalOddLabel.split(":");


        BettingTicketType bettingTicketType;

        if(Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.SOLO))
        {
            bettingTicketType = BettingTicketType.SOLO;
        }
        else if(Objects.equals(menuFrame.getMenuPanel().getTicketTypeComboBox().getSelectedItem(), BettingTicketType.AKO))
        {
            bettingTicketType = BettingTicketType.AKO;
        }
        else
        {
            bettingTicketType = BettingTicketType.COMBI;
        }
        BettingTicket newBettingTicket = new BettingTicket(Integer.parseInt(ticketNumber[1]),bettingTicketType,new Date(),betsCreator(),BettingTicketStatus.IN_PROGRESS,null,getTotalStake(),Double.parseDouble(totalOdd[1]));


        this.repo.addActual(newBettingTicket);
        this.repo.saveActual();

    }


    //auxiliary methods
    private int ticketNumberGenerator()
    {
        Random generator = new Random();
        return Math.abs(generator.nextInt(2000000000));

    }


    public String dateFormatter(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }


    public void blockLettersInTextField(JTextField textField)
    {
        textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char input = e.getKeyChar();

                if ((!Character.isDigit(input)))
                {
                    e.consume();
                }
            }
        });
    }


    public BigDecimal roundNumbers(double value1)
    {
        return new BigDecimal(value1).setScale(2, RoundingMode.HALF_UP);
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


    private double countOverallPotentialPrize()
    {
        return roundNumbers(countTotalOdd()*Tax.TAX_VALUE * getTotalStake()).doubleValue();
    }


    //getters and setters

    public void setOverallPotentialPrize()
    {
        ticketCreatorPanel.getlPotentialPrize().setText(" Potential Prize: " + countOverallPotentialPrize());
    }

    public void setAdditionalOdds()
    {
        for(int i = 0; i < selectedEvents.size(); i++)
        {
            if(isInstanceOfDrawableEvent(selectedEvents.get(i)) && (menuFrame.getMenu().getRbDecimalOdd().isSelected()))
                ticketCreatorPanel.getBetInfoCreator().getOddLabels().get(i).setText("   1 ODD: " + selectedEvents.get(i).getOddA() +  "    X ODD: " + selectedEvents.get(i).getDrawOdd() + "    2 ODD: " + selectedEvents.get(i).getOddB() + "    1X ODD: " + get1XOdd(i) + "   12 ODD: " + get12Odd(i)  + "   X2 ODD: " + getX2Odd(i) + " ");
            else if(isInstanceOfDrawableEvent(selectedEvents.get(i)) && (menuFrame.getMenu().getRbAmericanOdd().isSelected()))
            {
                ticketCreatorPanel.getBetInfoCreator().getOddLabels().get(i).setText("   1 ODD: " + roundNumbers(oddConverter(selectedEvents.get(i).getOddA())).doubleValue() +  "    X ODD: " + roundNumbers(oddConverter(selectedEvents.get(i).getDrawOdd())).doubleValue() + "    2 ODD: " + roundNumbers(oddConverter(selectedEvents.get(i).getOddB())).doubleValue() + "    1X ODD: " + roundNumbers(oddConverter(get1XOdd(i))).doubleValue() + "   12 ODD: " + roundNumbers(oddConverter(get12Odd(i))).doubleValue()  + "   X2 ODD: " + roundNumbers(oddConverter(getX2Odd(i))).doubleValue() + " ");
            }
            else if(isInstanceOfDrawableEvent(selectedEvents.get(i)) && (menuFrame.getMenu().getRbBritishOdd().isSelected()))
            {
                ticketCreatorPanel.getBetInfoCreator().getOddLabels().get(i).setText("   1 ODD: " + roundNumbers(oddConverter(selectedEvents.get(i).getOddA())).doubleValue() +  "    X ODD: " + roundNumbers(oddConverter(selectedEvents.get(i).getDrawOdd())).doubleValue() + "    2 ODD: " + roundNumbers(oddConverter(selectedEvents.get(i).getOddB())).doubleValue() + "    1X ODD: " + roundNumbers(oddConverter(get1XOdd(i))).doubleValue() + "   12 ODD: " + roundNumbers(oddConverter(get12Odd(i))).doubleValue()  + "   X2 ODD: " + roundNumbers(oddConverter(getX2Odd(i))).doubleValue() + " ");

            }
        }
    }


    public void setActualTotalOdd()
    {
            ticketCreatorPanel.getlTotalOdd().setText("Total Odd: " + countTotalOdd() + " ");
    }


    public ArrayList<EventToBet> getEventsResult(ArrayList<EventToBet> events)
    {
        Random resultGenerator = new Random();
        for(EventToBet event : events)
        {
            if(isInstanceOfDrawableEvent(event))
            {
                Integer result = resultGenerator.nextInt(3);
                event.setResult(result);
            }
            else
            {
                Integer result = resultGenerator.nextInt(2)+1;
                event.setResult(result);
            }
        }
        return events;
    }

    public ArrayList<EventToBet> getSelectedEvents()
    {
        this.selectedEvents = new ArrayList<>();
        for (int i = 0; i < menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().size(); i++)
        {
            if (menuFrame.getMenuPanel().getCheckBoxCreator().getCheckboxList().get(i).isSelected())
                selectedEvents.add(getAvailableEvents().get(i));
        }
        return selectedEvents;

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


    public double getTotalStake()
    {
        return Double.parseDouble(ticketCreatorPanel.getTfStake().getText());
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


    public ArrayList<EventToBet> getAvailableEvents()
    {
        return availableEvents;
    }
}
