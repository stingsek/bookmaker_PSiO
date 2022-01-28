package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class PlayedBettingTicketsDisplayer extends JPanel
{
    private MenuController menuController;
    private final JTable playedTicketsTable;

    public PlayedBettingTicketsDisplayer(MenuController menuController)
    {
        super();
        this.menuController = menuController;

        setBackground(new Color(255, 183, 0));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Vector<Vector> ticketData = new Vector<>();


        for (int i = 0; i < menuController.getPlayedBettingTickets().size(); i++)
        {
            Vector<?> vector = new Vector<>(Arrays.asList(menuController.getPlayedBettingTickets().get(i).getNumber(),menuController.getPlayedBettingTickets().get(i).getBettingTicketType(),menuController.dateFormatter(menuController.getPlayedBettingTickets().get(i).getCreationDate()),menuController.getPlayedBettingTickets().get(i).getBets().size(),menuController.getPlayedBettingTickets().get(i).getBettingTicketStatus(),menuController.getPlayedBettingTickets().get(i).getPrize(),menuController.getPlayedBettingTickets().get(i).getStake(),menuController.getPlayedBettingTickets().get(i).getTotalOdd()));
            ticketData.add(vector);
        }

        Vector<String> columnNames = new Vector<>(Arrays.asList("Ticket Number", "Ticket Type", "Creation Date","Bets quantity","Ticket Status","Prize", "Stake", "Total Odd"));


        playedTicketsTable = new JTable(ticketData,columnNames)
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
                    c.setBackground(new Color(255,165,0));
                }

                return c;

            }
        };


       playedTicketsTable.setPreferredScrollableViewportSize(new Dimension(700, 150));
       playedTicketsTable.setFillsViewportHeight(true);
       playedTicketsTable.getTableHeader().setReorderingAllowed(false);
       playedTicketsTable.setFont(new Font("Monospaced",Font.BOLD,15));
       playedTicketsTable.setRowHeight(30);

        JScrollPane jScrollPane = new JScrollPane(playedTicketsTable);
        jScrollPane.setPreferredSize(new Dimension(600,400));

        this.add(jScrollPane,BorderLayout.CENTER);

        this.setBorder(BorderFactory.createLineBorder(Color.black));

    }
}
