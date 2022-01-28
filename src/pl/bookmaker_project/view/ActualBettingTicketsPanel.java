package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;
import pl.bookmaker_project.observer.Observable;
import pl.bookmaker_project.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActualBettingTicketsPanel extends JPanel implements Observable
{

    private MenuController menuController;
    private final JButton bPlay, bCancel;
    private final JLabel lActualTickets;
    private final ActualBettingTicketsDisplayer actualBettingTicketsCreator;
    private final JScrollPane actualTicketsScrollPane;
    private final JPanel southPanel;
    private ArrayList<Observer> actualTicketsPanelObserverList = new ArrayList<>();


    public ActualBettingTicketsPanel(MenuController menuController)
    {
        this.menuController = menuController;
        this.actualBettingTicketsCreator = new ActualBettingTicketsDisplayer(menuController);
        this.actualTicketsScrollPane = new JScrollPane();
        this.southPanel = new JPanel();
        this.lActualTickets = new JLabel("Actual Betting Tickets: ",SwingConstants.CENTER);
        this.bPlay = new JButton("Play");
        this.bCancel = new JButton("Cancel");

        setUpActualBettingTicketsPanel();

        setUpListeners();

    }


    private void setUpActualBettingTicketsPanel()
    {
        this.setLayout(new BorderLayout());


        lActualTickets.setForeground(new Color(100,200,106));
        lActualTickets.setFont(new Font("Sans Serif",Font.BOLD,30));


        lActualTickets.setAlignmentX(CENTER_ALIGNMENT);

        this.add(lActualTickets,BorderLayout.PAGE_START);


        actualBettingTicketsCreator.setAlignmentY(CENTER_ALIGNMENT);
        actualTicketsScrollPane.setViewportView(actualBettingTicketsCreator);

        this.add(actualTicketsScrollPane, BorderLayout.CENTER);


        southPanel.setLayout(new BorderLayout());
        bCancel.setFocusable(false);
        bCancel.setFont(new Font("Sans Serif",Font.BOLD,30));

        southPanel.add(bCancel,BorderLayout.PAGE_END);


        bPlay.setFocusable(false);
        bPlay.setFont(new Font("Sans Serif",Font.BOLD,30));

        southPanel.add(bPlay,BorderLayout.PAGE_START);

        this.add(southPanel,BorderLayout.PAGE_END);

    }


    @Override
    public void registerObserver(Observer observer)
    {
        actualTicketsPanelObserverList.add(observer);
    }


    @Override
    public void removeObserver(Observer observer)
    {
        actualTicketsPanelObserverList.remove(observer);
    }


    @Override
    public void notifyObservers()
    {
        for (Observer observer : actualTicketsPanelObserverList)
        {
            observer.update(this.menuController, this);
        }
    }


    private void setUpListeners()
    {
        bCancel.addActionListener(click -> menuController.returnToMainMenu());
        bPlay.addActionListener(click -> menuController.playBettingTickets());
    }


}
