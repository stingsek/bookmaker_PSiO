package pl.bookmaker_project.view;

import pl.bookmaker_project.model.BettingTicketType;
import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel
{
    private MenuController menuController;
    private CheckBoxCreator checkBoxCreator;
    private JPanel southPanel;
    private JButton bCreateBT;
    private JLabel lAvailableEvents;
    private JScrollPane panelScrollPane;
    private JComboBox<BettingTicketType> ticketTypeComboBox;
//    private BetInfoCreator labelCreator;

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
//        this.labelCreator = new BetInfoCreator(menuController);

        setUpEventsPanel();
        setUpListeners();
    }

    private void setUpEventsPanel()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        this.add(lAvailableEvents,BorderLayout.PAGE_START);
        lAvailableEvents.setAlignmentX(CENTER_ALIGNMENT);
        lAvailableEvents.setFont(new Font("Sans Serif",Font.BOLD,30));
        lAvailableEvents.setForeground(new Color(200,1,84));


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
        bCreateBT.setFont(new Font("Sans Serif", Font.ITALIC,25));
        bCreateBT.setFocusable(false);
        southPanel.add(bCreateBT);
        this.add(southPanel);

    }

    private void createComponents()
    {


    }

    private void setUpListeners()
    {
        bCreateBT.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                menuController.goToTicketCreationPanel();
            }
        });

    }

    public CheckBoxCreator getCheckBoxCreator()
    {
        return checkBoxCreator;
    }

    public JButton getbCreateBT()
    {
        return bCreateBT;
    }

    public JLabel getlAvailableEvents()
    {
        return lAvailableEvents;
    }

    public JScrollPane getPanelScrollPane()
    {
        return panelScrollPane;
    }

    public JComboBox<BettingTicketType> getTicketTypeComboBox()
    {
        return ticketTypeComboBox;
    }
}
