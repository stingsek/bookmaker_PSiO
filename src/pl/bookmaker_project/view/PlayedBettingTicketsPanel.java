package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class PlayedBettingTicketsPanel extends JPanel
{
    private MenuController menuController;
    private final JButton bOK;
    private final JLabel lPlayedTickets;
    private final PlayedBettingTicketsDisplayer playedBettingTicketsCreator;
    private final JScrollPane actualTicketsScrollPane;
    private final JPanel southPanel;

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
        bOK.addActionListener(click -> menuController.returnToMainMenu());
    }
}
