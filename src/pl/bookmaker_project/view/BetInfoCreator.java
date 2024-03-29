package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BetInfoCreator extends JPanel
{
    private MenuController menuController;
    private final ArrayList<JLabel> eventLabels = new ArrayList<>();
    private final ArrayList <JLabel> oddLabels = new ArrayList<>();
    private final ArrayList<ButtonGroup> buttonGroups = new ArrayList<>();
    private JLabel eventDescriptionLabel, drawableEventOddsLabel, eventOddsLabel;


    public BetInfoCreator(MenuController menuController)
    {
        super();
        this.menuController = menuController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


            for (int i = 0; i < menuController.getSelectedEvents().size(); i++)
            {
                JPanel betInfoPanel = new JPanel();

                betInfoPanel.setLayout(new BorderLayout());

                this.eventDescriptionLabel = new JLabel(menuController.getSelectedEvents().get(i).toStringWithoutOdds());

                eventDescriptionLabel.setFocusable(false);
                eventDescriptionLabel.setForeground(new Color(200));
                eventDescriptionLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

                betInfoPanel.add(eventDescriptionLabel,BorderLayout.LINE_START);

                eventLabels.add(eventDescriptionLabel);


                JPanel radioButtonsPannel = new JPanel();

                radioButtonsPannel.setLayout(new BoxLayout(radioButtonsPannel,BoxLayout.X_AXIS));

                if(menuController.isInstanceOfDrawableEvent(menuController.getSelectedEvents().get(i)))
                    {

                        JRadioButton button1 = new JRadioButton("1");
                        JRadioButton button2 = new JRadioButton("X");
                        JRadioButton button3 = new JRadioButton("2");
                        JRadioButton button4 = new JRadioButton("1X");
                        JRadioButton button5 = new JRadioButton("12");
                        JRadioButton button6 = new JRadioButton("X2");

                        button1.setActionCommand("1");
                        button2.setActionCommand("X");
                        button3.setActionCommand("2");
                        button4.setActionCommand("1X");
                        button5.setActionCommand("12");
                        button6.setActionCommand("X2");

                        this.drawableEventOddsLabel = new JLabel("   1 ODD: " + menuController.getSelectedEvents().get(i).getOddA() + "    X ODD: " + menuController.getSelectedEvents().get(i).getDrawOdd() + "    2 ODD: " + menuController.getSelectedEvents().get(i).getOddB() + "     1X ODD:    12 ODD:    X2 ODD:");
                        drawableEventOddsLabel.setFont(new Font("Monospaced",Font.ITALIC,15));
                        drawableEventOddsLabel.setToolTipText("If you change an odd type you have to press enter one more time to see additional double chance odds");


                        oddLabels.add(drawableEventOddsLabel);

                        button1.setFocusable(false);
                        button1.setToolTipText("1 team will win");

                        button2.setFocusable(false);
                        button2.setToolTipText("will be draw");

                        button3.setFocusable(false);
                        button3.setToolTipText("2 team will win");

                        button4.setFocusable(false);
                        button4.setToolTipText("2 won't win");

                        button5.setFocusable(false);
                        button5.setToolTipText("won't be draw");

                        button6.setFocusable(false);
                        button6.setToolTipText("1 won't win");


                        ButtonGroup newButtonGroup = new ButtonGroup();

                        newButtonGroup.add(button1);
                        newButtonGroup.add(button2);
                        newButtonGroup.add(button3);
                        newButtonGroup.add(button4);
                        newButtonGroup.add(button5);
                        newButtonGroup.add(button6);


                        buttonGroups.add(newButtonGroup);
                        radioButtonsPannel.add(button1);
                        radioButtonsPannel.add(button2);
                        radioButtonsPannel.add(button3);
                        radioButtonsPannel.add(button4);
                        radioButtonsPannel.add(button5);
                        radioButtonsPannel.add(button6);


                        betInfoPanel.add(radioButtonsPannel,BorderLayout.LINE_END);

                        betInfoPanel.add(drawableEventOddsLabel,BorderLayout.CENTER);

                    }
                else
                {
                    JRadioButton button1 = new JRadioButton("1");
                    JRadioButton button2 = new JRadioButton("2");

                    button1.setActionCommand("1");
                    button2.setActionCommand("2");

                    this.eventOddsLabel = new JLabel("    1 ODD: " + menuController.getSelectedEvents().get(i).getOddA() + "     2 ODD: " + menuController.getSelectedEvents().get(i).getOddB());
                    eventOddsLabel.setFont(new Font("Monospaced",Font.ITALIC,15));

                    oddLabels.add(eventOddsLabel);


                    button1.setFocusable(false);
                    button1.setToolTipText("1 team will win");
                    button2.setFocusable(false);
                    button2.setToolTipText("2 team will win");

                    ButtonGroup newButtonGroup = new ButtonGroup();
                    newButtonGroup.add(button1);
                    newButtonGroup.add(button2);

                    buttonGroups.add(newButtonGroup);
                    radioButtonsPannel.add(button1);
                    radioButtonsPannel.add(button2);

                    betInfoPanel.add(radioButtonsPannel,BorderLayout.LINE_END);
                    betInfoPanel.add(eventOddsLabel,BorderLayout.CENTER);

                }
                this.add(betInfoPanel);
            }
    }


    public ArrayList<ButtonGroup> getButtonGroups()
    {
        return buttonGroups;
    }


    public ArrayList<JLabel> getOddLabels()
    {
        return oddLabels;
    }

}
