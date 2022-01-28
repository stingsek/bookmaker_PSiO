package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class EventResultPanel extends JPanel
{

    private MenuController menuController;
    private JTable eventsTable;
    private final JLabel allResultsLabel;
    private final JButton bOk;
    private final JPanel northPanel,southPanel, centerPanel;



    public EventResultPanel(MenuController menuController)
    {
        super();

        this.menuController = menuController;
        this.allResultsLabel = new JLabel("All results:",SwingConstants.CENTER);
        this.bOk = new JButton("OK");
        this.northPanel = new JPanel();
        this.centerPanel = new JPanel();
        this.southPanel = new JPanel();

        setUpEventResultPanel();
        setUpListeners();
    }


    private void setUpEventResultPanel()
    {
        this.setBackground(new Color(218, 205, 55));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        northPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        


        Vector<Vector> eventData = new Vector<>();

        for (int i = 0; i < menuController.getAvailableEvents().size(); i++)
        {
            Vector<?> vector = new Vector<>(Arrays.asList(menuController.getAvailableEvents().get(i).getParticipantA(),menuController.getAvailableEvents().get(i).getParticipantB(),menuController.dateFormatter(menuController.getAvailableEvents().get(i).getDate()),menuController.getAvailableEvents().get(i).getSportType(),menuController.getAvailableEvents().get(i).getResult()));
            eventData.add(vector);
        }

        Vector<String> columnNames = new Vector<>(Arrays.asList("Participant 1", "Participant 2", "Date","Sport Type","Result"));


        this.eventsTable = new JTable(eventData,columnNames)
        {
            public boolean isCellEditable(int ticketData, int columnNames )
            {
                return false;

            }

            public Component prepareRenderer(TableCellRenderer r, int ticketData, int columnNames)
            {
                Component c = super.prepareRenderer(r, ticketData, columnNames);

                if (ticketData % 2 == 0)
                {
                    c.setBackground(Color.WHITE);
                }
                else
                {
                    c.setBackground(Color.LIGHT_GRAY);
                }

                if(isCellSelected(ticketData,columnNames))
                {
                    c.setBackground(new Color(218, 205, 55));
                }

                return c;

            }



        };


        allResultsLabel.setFont(new Font("Monospaced",Font.BOLD,40));

        allResultsLabel.setForeground(new Color(218, 205, 55));

        northPanel.add(allResultsLabel,BorderLayout.PAGE_START);


        eventsTable.setPreferredScrollableViewportSize(new Dimension(700, 150));

        eventsTable.setFillsViewportHeight(true);

        eventsTable.getTableHeader().setReorderingAllowed(false);

        eventsTable.setFont(new Font("Monospaced",Font.BOLD,15));

        eventsTable.setRowHeight(30);


        JScrollPane jScrollPane = new JScrollPane(eventsTable);

        jScrollPane.setPreferredSize(new Dimension(600,400));

        this.add(jScrollPane,BorderLayout.CENTER);

        this.setBorder(BorderFactory.createLineBorder(Color.black));

        northPanel.add(jScrollPane,BorderLayout.PAGE_END);

        bOk.setFont(new Font("Monospaced",Font.BOLD,30));

        southPanel.add(bOk,BorderLayout.PAGE_END);

        this.add(northPanel);

        this.add(southPanel);


    }


    private void setUpListeners()
    {
        bOk.addActionListener(click -> menuController.returnToMainMenu());
    }
}

