package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame
{
    private MenuController menuController;
    private final JPanel mainpanel;
    private final MenuPanel menuPanel;
    private ActualBettingTicketsPanel actualBettingTicketsPanel;
    private PlayedBettingTicketsPanel playedBettingTicketsPanel;
    private final Menu menu;
    private final CardLayout card;


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

        this.setJMenuBar(menu);

        this.repaint();

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


    public ActualBettingTicketsPanel getActualBettingTicketsPanel()
    {
        return actualBettingTicketsPanel;
    }


    public void setActualBettingTicketsPanel(ActualBettingTicketsPanel actualBettingTicketsPanel)
    {
        this.actualBettingTicketsPanel = actualBettingTicketsPanel;
    }


    public void setPlayedBettingTicketsPanel(PlayedBettingTicketsPanel playedBettingTicketsPanel)
    {
        this.playedBettingTicketsPanel = playedBettingTicketsPanel;
    }
}
