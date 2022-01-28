package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;
import pl.bookmaker_project.observer.Observable;
import pl.bookmaker_project.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicketCreatorPanel extends JPanel implements Observable
{

    private MenuController menuController;
    private final BetInfoCreator betInfoCreator;
    private final JLabel lBalance,lTicketNumber, lDate, lTicketType, lTotalOdd, lPotentialPrize;
    private final JButton bCancel, bConfirm;
    private final JPanel northPanel, southPanel, endPanel;
    private final JScrollPane betInfoCreatorScrollPane;
    private final JTextField tfStake;
    private ArrayList<Observer> ticketPanelObserverList = new ArrayList<Observer>();


    public TicketCreatorPanel(MenuController menuController)
    {
        super();
        this.menuController = menuController;
        this.bCancel = new JButton("Cancel");
        this.bConfirm = new JButton("Confirm");
        this.northPanel = new JPanel();
        this.southPanel = new JPanel();
        this.endPanel = new JPanel();
        this.betInfoCreator = new BetInfoCreator(menuController);
        this.lBalance = new JLabel("Balance: -",SwingConstants.CENTER);
        this.lTicketNumber = new JLabel("TicketNumber: -",SwingConstants.CENTER);
        this.lDate = new JLabel("Date: -",SwingConstants.CENTER);
        this.lTicketType = new JLabel("Ticket Type: -",SwingConstants.CENTER);
        this.betInfoCreatorScrollPane = new JScrollPane();
        this.lPotentialPrize = new JLabel("Potential Prize:",SwingConstants.CENTER);
        this.lTotalOdd = new JLabel("Total Odd:",SwingConstants.CENTER);
        this.tfStake = new JTextField("0");

        setUpTicketCreatorPanel();
        setUpListeners();
    }

    private void setUpTicketCreatorPanel()
    {
        this.setLayout(new BorderLayout());

        northPanel.setLayout(new BorderLayout());
        northPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        lTicketNumber.setForeground(new Color(200,0,105));
        lTicketNumber.setBorder(BorderFactory.createLineBorder(Color.black,0));
        lTicketNumber.setFont(new Font("Sans Serif",Font.BOLD,25));

        northPanel.add(lTicketNumber,BorderLayout.LINE_START);


        lDate.setForeground(new Color(200,0,105));
        lDate.setBorder(BorderFactory.createLineBorder(Color.black,0));
        lDate.setFont(new Font("Sans Serif",Font.BOLD,25));

        northPanel.add(lDate,BorderLayout.CENTER);


        lTicketType.setForeground(new Color(200,0,105));
        lTicketType.setBorder(BorderFactory.createLineBorder(Color.black,0));
        lTicketType.setFont(new Font("Sans Serif",Font.BOLD,25));

        northPanel.add(lTicketType,BorderLayout.LINE_END);


        this.add(northPanel,BorderLayout.PAGE_START);


        betInfoCreator.setAlignmentY(CENTER_ALIGNMENT);
        betInfoCreatorScrollPane.setViewportView(betInfoCreator);


        this.add(betInfoCreatorScrollPane,BorderLayout.CENTER);


        southPanel.setLayout(new BorderLayout());
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        lBalance.setForeground(new Color(200,0,105));
        lBalance.setBorder(BorderFactory.createLineBorder(Color.black,0));
        lBalance.setFont(new Font("Sans Serif",Font.BOLD,25));

        southPanel.add(lBalance,BorderLayout.CENTER);


        tfStake.setToolTipText("Type the stake and press ENTER after you will select an odd for every event");
        menuController.blockLettersInTextField(tfStake);

        southPanel.add(tfStake,BorderLayout.NORTH);



        lTotalOdd.setForeground(new Color(200,0,105));
        lTotalOdd.setBorder(BorderFactory.createLineBorder(Color.black,0));
        lTotalOdd.setFont(new Font("Sans Serif",Font.BOLD,25));

        southPanel.add(lTotalOdd,BorderLayout.EAST);


        lPotentialPrize.setForeground(new Color(200,0,105));
        lPotentialPrize.setBorder(BorderFactory.createLineBorder(Color.black,0));
        lPotentialPrize.setFont(new Font("Sans Serif",Font.BOLD,25));

        southPanel.add(lPotentialPrize,BorderLayout.WEST);


        endPanel.setLayout(new BorderLayout());


        bCancel.setFont(new Font("Sans Serif", Font.ITALIC,30));
        bCancel.setFocusable(false);

        endPanel.add(bCancel, BorderLayout.PAGE_END);

        bConfirm.setFont(new Font("Sans Serif", Font.ITALIC,30));
        bConfirm.setFocusable(false);

        endPanel.add(bConfirm, BorderLayout.PAGE_START);


        southPanel.add(endPanel,BorderLayout.PAGE_END);


        this.add(southPanel,BorderLayout.SOUTH);

    }



    @Override
    public void registerObserver(Observer observer)
    {
        ticketPanelObserverList.add(observer);
    }


    @Override
    public void removeObserver(Observer observer)
    {
        ticketPanelObserverList.remove(observer);
    }


    @Override
    public void notifyObservers()
    {
        for (Observer observer : ticketPanelObserverList)
        {
            observer.update(this.menuController, this);
        }
    }


    private void setUpListeners()
    {
        bCancel.addActionListener(click -> menuController.returnToMainMenu());

        bConfirm.addActionListener(click -> menuController.goBackToMenuAfterCreatingBettingTicket());

        tfStake.addActionListener(click -> menuController.tfStakeHandling());
    }


    public JTextField getTfStake()
    {
        return tfStake;
    }


    public BetInfoCreator getBetInfoCreator()
    {
        return betInfoCreator;
    }


    public JLabel getlBalance() {
        return lBalance;
    }


    public JLabel getlTicketNumber()
    {
        return lTicketNumber;
    }


    public JLabel getlDate() {
        return lDate;
    }


    public JLabel getlTicketType() {
        return lTicketType;
    }


    public JLabel getlTotalOdd() {
        return lTotalOdd;
    }


    public JLabel getlPotentialPrize() {
        return lPotentialPrize;
    }



}
