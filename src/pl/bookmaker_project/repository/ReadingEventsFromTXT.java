package pl.bookmaker_project.repository;

import pl.bookmaker_project.model.DrawableEvent;
import pl.bookmaker_project.model.EventToBet;
import pl.bookmaker_project.model.SportType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class ReadingEventsFromTXT
{
    public static ArrayList<EventToBet> read(File file) throws IOException
    {
        ArrayList<EventToBet> events = new ArrayList<>();

        try (BufferedReader breader = new BufferedReader(new FileReader(file)))
        {
            String row;
            String[] eventData;

            while ((row = breader.readLine()) != null)
            {
                eventData = row.split(";");

                int sport = Integer.parseInt(eventData[3]);

                SportType sportType = SportType.values()[sport];


                switch (sportType)
                {
                    case VOLLEYBALL -> events.add(new EventToBet((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(eventData[0])), eventData[1], eventData[2], SportType.VOLLEYBALL, Double.parseDouble(eventData[4]), Double.parseDouble(eventData[5]), null));
                    case FOOTBALL -> events.add(new DrawableEvent((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(eventData[0])), eventData[1], eventData[2], SportType.FOOTBALL, Double.parseDouble(eventData[4]), Double.parseDouble(eventData[5]), Double.parseDouble(eventData[6]), null));
                    case BOXING -> events.add(new DrawableEvent((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(eventData[0])), eventData[1], eventData[2], SportType.BOXING, Double.parseDouble(eventData[4]), Double.parseDouble(eventData[5]), Double.parseDouble(eventData[6]), null));
                    case TENNIS -> events.add(new EventToBet((new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(eventData[0])), eventData[1], eventData[2], SportType.TENNIS, Double.parseDouble(eventData[4]), Double.parseDouble(eventData[5]), null));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return events;
    }
}

