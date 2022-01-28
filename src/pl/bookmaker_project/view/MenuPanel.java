package pl.bookmaker_project.view;

import pl.bookmaker_project.model.BettingTicketType;
import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;


public class MenuPanel extends JPanel
{
    private MenuController menuController;
    private final CheckBoxCreator checkBoxCreator;
    private final JPanel southPanel;
    private final JButton bCreateBT;
    private final JLabel lAvailableEvents;
    private final JScrollPane panelScrollPane;
    private final JComboBox<BettingTicketType> ticketTypeComboBox;



    public MenuPanel(MenuController menuController)
    {
        super();
        this.menuController = menuController;
        this.southPanel = new JPanel();
        this.checkBoxCreator = new CheckBoxCreator(menuController);
        this.bCreateBT = new JButton("Create a new Betting Ticket");
        this.lAvailableEvents = new JLabel("Available Events:");
        this.panelScrollPane = new JScrollPane();
        this.ticketTypeComboBox = new JComboBox<>();


        setUpEventsPanel();
        setUpListeners();
    }

    private void setUpEventsPanel()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        this.add(lAvailableEvents,BorderLayout.PAGE_START);

        lAvailableEvents.setAlignmentX(CENTER_ALIGNMENT);
        lAvailableEvents.setFont(new Font("Monospaced",Font.BOLD,30));
        lAvailableEvents.setForeground(new Color(4, 10, 169));


        checkBoxCreator.setAlignmentX(CENTER_ALIGNMENT);
        panelScrollPane.setViewportView(checkBoxCreator);

        this.add(panelScrollPane);

        ticketTypeComboBox.setToolTipText("Select the type of the Betting Ticket");
        ticketTypeComboBox.addItem(BettingTicketType.SOLO);
        ticketTypeComboBox.addItem(BettingTicketType.AKO);
        ticketTypeComboBox.addItem(BettingTicketType.COMBI);

        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        ticketTypeComboBox.setRenderer(listRenderer);

        this.add(ticketTypeComboBox);


        southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.X_AXIS));

        bCreateBT.setAlignmentX(CENTER_ALIGNMENT);
        bCreateBT.setFont(new Font("Monospaced", Font.BOLD,25));
        bCreateBT.setFocusable(false);
        bCreateBT.setForeground(new Color(4, 10, 169));

        southPanel.add(bCreateBT);

        this.add(southPanel);

    }


    private void setUpListeners()
    {
        bCreateBT.addActionListener(click -> menuController.goToTicketCreationPanel());
    }


    public CheckBoxCreator getCheckBoxCreator()
    {
        return checkBoxCreator;
    }


    public JComboBox<BettingTicketType> getTicketTypeComboBox()
    {
        return ticketTypeComboBox;
    }
}
