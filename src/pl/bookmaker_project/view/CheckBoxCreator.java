package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CheckBoxCreator extends JPanel
{
    private MenuController menuController;
    private ArrayList<JCheckBox> checkboxList = new ArrayList<JCheckBox>();
    private final JSeparator separator;


    public CheckBoxCreator(MenuController menuController)
    {
        super();

        this.menuController = menuController;

        this.setBackground(new Color(255, 255, 255, 255));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < menuController.getAvailableEvents().size(); i++)
        {
            JCheckBox newCheckbox = new JCheckBox(menuController.readAvailableEvents().get(i).toString());

            newCheckbox.setFocusable(false);
            newCheckbox.setFont(new Font("Monospaced",Font.ITALIC,20));
            newCheckbox.setToolTipText("Select event if you want to bet it");
            newCheckbox.setForeground(new Color(4, 10, 169));
            newCheckbox.setBackground(new Color(255, 255, 255, 255));
            newCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
            newCheckbox.setBorderPainted(true);
            add(newCheckbox,SwingConstants.CENTER);


            checkboxList.add(newCheckbox);
        }
        this.separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        add(separator);

    }

    public ArrayList<JCheckBox> getCheckboxList()
    {
        return checkboxList;
    }
}
