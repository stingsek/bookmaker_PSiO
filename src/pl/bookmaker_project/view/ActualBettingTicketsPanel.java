package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualBettingTicketsPanel extends JPanel
{
    private MenuController menuController;
    private JButton bPlay, bCancel;
    private JLabel lActualTickets;
    private ActualBettingTicketsDisplayer actualBettingTicketsCreator;
    private JScrollPane actualTicketsScrollPane;
    private JPanel southPanel;

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
    private void setUpListeners()
    {
        bCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                menuController.returnToMainMenu();
            }
        });

    }

    public JButton getbPlay() {
        return bPlay;
    }

    public JButton getbCancel() {
        return bCancel;
    }

    public JLabel getlActualTickets() {
        return lActualTickets;
    }

    public ActualBettingTicketsDisplayer getActualBettingTicketsCreator() {
        return actualBettingTicketsCreator;
    }

    public JScrollPane getActualTicketsScrollPane() {
        return actualTicketsScrollPane;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }
}
