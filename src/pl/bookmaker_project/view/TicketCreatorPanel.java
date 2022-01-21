package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketCreatorPanel extends JPanel
{

    private MenuController menuController;
    private BetInfoCreator betInfoCreator;
    private JLabel lBalance,lTicketNumber, lDate, lTicketType, lTotalOdd, lTotalStake, lPotentialPrize;
    private JButton bCancel, bConfirm;
    private JPanel northPanel, southPanel, endPanel;
    private JScrollPane betInfoCreatorScrollPane;
    private JTextField tfStake;


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
        this.lPotentialPrize = new JLabel("Potential Prize: -",SwingConstants.CENTER);
        this.lTotalOdd = new JLabel("Total Odd: -",SwingConstants.CENTER);
        this.lTotalStake = new JLabel("Total Stake: -",SwingConstants.CENTER);
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
//        betInfoCreatorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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

    public JLabel getlTicketNumber() {
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

    public JLabel getlTotalStake() {
        return lTotalStake;
    }

    public JLabel getlPotentialPrize() {
        return lPotentialPrize;
    }

    public JButton getbCancel() {
        return bCancel;
    }

    public JButton getbConfirm() {
        return bConfirm;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public JPanel getEndPanel() {
        return endPanel;
    }

    public JScrollPane getBetInfoCreatorScrollPane() {
        return betInfoCreatorScrollPane;
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

        bConfirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                if(menuController.isStakeBiggerThanZero())
                {
                    if(menuController.areAllButtonsSelected())
                    {
                        if (menuController.isBalanceBiggerThanTotalStake())
                        {
                            if(menuController.isTotalOddBiggerThanZero())
                            {
                                if (menuController.isLimitBiggerThanPotentialPrize() && menuController.isLimitBiggerThanTotalOdd())
                                {
                                    menuController.confirmBettingTicket();
                                    menuController.returnToMainMenu();
                                }
                                else
                                {
                                    menuController.displayLimitOverrunningInformation();
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Total odd can not be 0", "Odd value problem",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Your Balance is smaller than total stake!", "Balance problem",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Every event has to have one button selected!","Odd selection problem",JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });


        tfStake.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                menuController.setAdditionalOdds();
                menuController.setActualTotalStake();

                if(menuController.areAllButtonsSelected())
                {
                    menuController.setActualTotalOdd();
                    menuController.setOverallPotentialPrize();
                }


            }
        });
    }



//                        else
//                        {
//                            JOptionPane.showMessageDialog(null,"Total stake can not be bigger than Balance!","Stake value Problem",JOptionPane.ERROR_MESSAGE);
//                        }
//                    }
//                    else
//                    {
//                        JOptionPane.showMessageDialog(null,"Stake can not be 0!","Stake 0 in value",JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(null,"Select odd for all events!","Odds Selection problem",JOptionPane.ERROR_MESSAGE);
//                }


}
