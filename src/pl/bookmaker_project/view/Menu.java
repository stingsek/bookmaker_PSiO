package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;


public class Menu extends JMenuBar
{
    private MenuController menuController;
    private JMenu mBettingTicket, mPocket, mOddType;
    private JRadioButton rbAmericanOdd, rbBritishOdd, rbDecimalOdd;
    private JMenuItem iDisplayActual, iDisplayPlayed, iDisplayBalance;
    private ButtonGroup bgOdds;

    public Menu(MenuController menuController)
    {
        super();
        this.menuController = menuController;

        createComponents();
        setUpMenuBar();
        setUpListeners();

    }

    private void createComponents()
    {
        this.mOddType = new JMenu("Odd Type");
        this.mPocket = new JMenu("Pocket");
        this.mBettingTicket = new JMenu("Betting Ticket");
        this.iDisplayActual = new JMenuItem("Display Actual");
        this.iDisplayBalance = new JMenuItem("Display Balance");
        this.iDisplayPlayed = new JMenuItem("Display Played");
        this.rbAmericanOdd = new JRadioButton("American Odd");
        this.rbBritishOdd = new JRadioButton("British Odd");
        this.rbDecimalOdd = new JRadioButton("Decimal Odd");
        this.bgOdds = new ButtonGroup();

    }

    private void setUpMenuBar()
    {
        this.add(mBettingTicket);
        this.add(mOddType);
        this.add(mPocket);


        mBettingTicket.add(iDisplayActual);
        mBettingTicket.add(iDisplayPlayed);

        mPocket.add(iDisplayBalance);

        bgOdds.add(rbDecimalOdd);
        bgOdds.add(rbBritishOdd);
        bgOdds.add(rbAmericanOdd);

        mOddType.add(rbDecimalOdd);
        mOddType.add(rbBritishOdd);
        mOddType.add(rbAmericanOdd);

        rbBritishOdd.setFocusable(false);
        rbAmericanOdd.setFocusable(false);
        rbDecimalOdd.setFocusable(false);
    }

    private void setUpListeners()
    {
        iDisplayBalance.addActionListener(click -> menuController.displayBalance());

        iDisplayActual.addActionListener(click -> menuController.displayActualTickets());

        iDisplayPlayed.addActionListener(click -> menuController.displayPlayedTickets());

        rbDecimalOdd.addActionListener(click -> menuController.changeOdds());

        rbAmericanOdd.addActionListener(click -> menuController.changeOdds());

        rbBritishOdd.addActionListener(click -> menuController.changeOdds());

    }

    public JRadioButton getRbAmericanOdd()
    {
        return rbAmericanOdd;
    }

    public JRadioButton getRbBritishOdd()
    {
        return rbBritishOdd;
    }

    public JRadioButton getRbDecimalOdd()
    {
        return rbDecimalOdd;
    }
}

