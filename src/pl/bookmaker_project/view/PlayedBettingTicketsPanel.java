package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayedBettingTicketsPanel extends JPanel
{
    private MenuController menuController;
    private JButton bOK;
    private JLabel lPlayedTickets;
    private PlayedBettingTicketsDisplayer playedBettingTicketsCreator;
    private JScrollPane actualTicketsScrollPane;
    private JPanel southPanel;
    private JTable playedTicketsTable;

    public PlayedBettingTicketsPanel(MenuController menuController)
    {
        this.menuController = menuController;
        this.playedBettingTicketsCreator = new PlayedBettingTicketsDisplayer(menuController);
        this.actualTicketsScrollPane = new JScrollPane();
        this.southPanel = new JPanel();
        this.lPlayedTickets = new JLabel("Played Betting Tickets: ",SwingConstants.CENTER);
        this.bOK = new JButton("OK");


        setUpActualBettingTicketsPanel();

        setUpListeners();
    }

    private void setUpActualBettingTicketsPanel()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        lPlayedTickets.setForeground(new Color(255, 183, 0));
        lPlayedTickets.setFont(new Font("Sans Serif",Font.BOLD,30));

        lPlayedTickets.setAlignmentX(CENTER_ALIGNMENT);
        this.add(lPlayedTickets,BorderLayout.PAGE_START);

        playedBettingTicketsCreator.setAlignmentY(CENTER_ALIGNMENT);
        actualTicketsScrollPane.setViewportView(playedBettingTicketsCreator);
        this.add(actualTicketsScrollPane);

        southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.X_AXIS));

        bOK.setFocusable(false);
        bOK.setFont(new Font("Sans Serif",Font.BOLD,30));
        southPanel.add(bOK);
        this.add(southPanel);


    }

    private void setUpListeners()
    {
        bOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                menuController.returnToMainMenu();
            }
        });
    }
}
