package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame
{
    private MenuController menuController;
    private JPanel mainpanel;
    private MenuPanel menuPanel;
    //private TicketCreatorPanel ticketCreatorPanel;
    private ActualBettingTicketsPanel actualBettingTicketsPanel;
    private PlayedBettingTicketsPanel playedBettingTicketsPanel;
    private Menu menu;
    private CardLayout card;

    //w polu bedzie controller

    public MenuFrame(MenuController menuController)//parametrem bedzie controller
    {
        super();
        this.menuController = menuController;
        this.menuPanel = new MenuPanel(menuController);
        this.menu = new Menu(menuController);
        this.actualBettingTicketsPanel = new ActualBettingTicketsPanel(menuController);
        this.playedBettingTicketsPanel = new PlayedBettingTicketsPanel(menuController);
        this.mainpanel = new JPanel();
        this.card = new CardLayout();
        setUpMenuFrame();
        setUpListeners();

    }

    private void createComponents()
    {

    }

    private void setUpMenuFrame()
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setSize(1200,700);
        this.setTitle("bookmaker app");
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        mainpanel.setLayout(card);

        mainpanel.add(menuPanel,"Menu Panel");
        mainpanel.add(actualBettingTicketsPanel, "Actual Tickets Panel");
        mainpanel.add(playedBettingTicketsPanel,"Played Tickets Panel");

        this.add(mainpanel);
        this.setVisible(true);

//Method came from the ItemListener class implementation,
//contains functionality to process the combo box item selecting
//        this.setContentPane(ticketCreatorPanel);
//        this.setContentPane(actualBettingTicketsPanel);

//        this.setContentPane(ticketCreatorPanel);
//        frameScrollPane.setVisible(true);
        this.setJMenuBar(menu);

        this.repaint();

    }

    private void setUpListeners()
    {


    }


    public Menu getMenu()
    {
        return menu;
    }

    public JPanel getMainpanel()
    {
        return mainpanel;
    }

    public CardLayout getCard()
    {
        return card;
    }

    public MenuPanel getMenuPanel()
    {
        return menuPanel;
    }

//    public TicketCreatorPanel getTicketCreatorPanel()
//    {
//        return ticketCreatorPanel;
//    }

    public ActualBettingTicketsPanel getActualBettingTicketsPanel()
    {
        return actualBettingTicketsPanel;
    }

    public PlayedBettingTicketsPanel getPlayedBettingTicketsPanel()
    {
        return playedBettingTicketsPanel;
    }


}
