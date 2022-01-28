package pl.bookmaker_project.view;

import pl.bookmaker_project.controller.MenuController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class ActualBettingTicketsDisplayer extends JPanel
{
    private MenuController menuController;
    private JTable actualTicketsTable;


    public ActualBettingTicketsDisplayer(MenuController menuController)
    {
        super();
        this.menuController = menuController;

        setBackground(new Color(100, 200, 106));

        setLayout(new BorderLayout());

        Vector<Vector> ticketData = new Vector<>();

        for (int i = 0; i < menuController.getActualBettingTickets().size(); i++)
        {
            Vector<?> vector = new Vector<>(Arrays.asList(menuController.getActualBettingTickets().get(i).getNumber(), menuController.getActualBettingTickets().get(i).getBettingTicketType(), menuController.dateFormatter(menuController.getActualBettingTickets().get(i).getCreationDate()), menuController.getActualBettingTickets().get(i).getBets().size(), menuController.getActualBettingTickets().get(i).getStake(),menuController.getActualBettingTickets().get(i).getTotalOdd()));
            ticketData.add(vector);
        }

        Vector<String> columnNames = new Vector<>(Arrays.asList("Ticket Number", "Ticket Type", "Creation Date", "Bets quantity" , "Stake", "Total Odd"));


        actualTicketsTable = new JTable(ticketData, columnNames) {
            public boolean isCellEditable(int ticketData, int columnNames) {
                return false;

            }


            public Component prepareRenderer(TableCellRenderer r, int ticketData, int columnNames) {
                Component c = super.prepareRenderer(r, ticketData, columnNames);

                if (ticketData % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(Color.LIGHT_GRAY);
                }

                if (isCellSelected(ticketData, columnNames))
                {
                    c.setBackground(Color.GREEN);
                }

                return c;

            }

        };


        actualTicketsTable.setPreferredScrollableViewportSize(new Dimension(700, 150));

        actualTicketsTable.setFillsViewportHeight(true);

        actualTicketsTable.getTableHeader().setReorderingAllowed(false);

        actualTicketsTable.setFont(new Font("Monospaced",Font.BOLD,15));

        actualTicketsTable.setRowHeight(30);



        JScrollPane jScrollPane = new JScrollPane(actualTicketsTable);
        jScrollPane.setPreferredSize(new Dimension(600,400));


        this.add(jScrollPane,BorderLayout.CENTER);


        this.setBorder(BorderFactory.createLineBorder(Color.black));

    }

}
