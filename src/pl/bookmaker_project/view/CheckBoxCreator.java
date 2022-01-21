package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CheckBoxCreator extends JPanel
{
    private MenuController menuController;
    private ArrayList<JCheckBox> checkboxList = new ArrayList<JCheckBox>();


    public CheckBoxCreator(MenuController menuController)
    {
        super();
        this.menuController = menuController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < menuController.getEvents().size(); i++)
        {
            JCheckBox newCheckbox = new JCheckBox(menuController.getEvents().get(i).toString());
            newCheckbox.setFocusable(false);
            newCheckbox.setFont(new Font("Monospaced",Font.ITALIC,20));
            newCheckbox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(newCheckbox,CENTER_ALIGNMENT);
            checkboxList.add(newCheckbox);
        }

        this.setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public ArrayList<JCheckBox> getCheckboxList()
    {
        return checkboxList;
    }
}
