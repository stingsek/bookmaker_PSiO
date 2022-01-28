package pl.bookmaker_project.repository;

import pl.bookmaker_project.controller.MenuController;
import pl.bookmaker_project.observer.Pocket;

import java.io.*;
import java.util.ArrayList;

public class Repository<T extends Serializable> implements Serializable
{
    @Serial
    private static final long serialVersionUID = -7597612114435918183L;
    ArrayList<T> actualTicketsRepo;
    ArrayList<T> playedTicketsRepo;
    Pocket pocketRepo;
    MenuController menuController;

    public Repository(MenuController menuController)
    {
        this.menuController = menuController;
        actualTicketsRepo = new ArrayList<>();
        playedTicketsRepo = new ArrayList<>();
        pocketRepo = new Pocket(10000);
    }

    public void saveActual()
    {
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("actualRepoFile.ser")))
        {
            so.writeObject(this.actualTicketsRepo);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void savePlayed()
    {
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("playedRepoFile.ser")))
        {
            so.writeObject(this.playedTicketsRepo);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void savePocketStatus()
    {
        try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream("pocketRepoFile.ser")))
        {
            so.writeObject(this.pocketRepo);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void readActual()
    {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("actualRepoFile.ser"))) {
            Object obj1 = is.readObject();
            this.actualTicketsRepo = (ArrayList<T>) obj1;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readPlayed()
    {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("playedRepoFile.ser"))) {
            Object obj1 = is.readObject();
            this.playedTicketsRepo = (ArrayList<T>) obj1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readPocketStatus()
    {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("pocketRepoFile.ser"))) {
            Object obj1 = is.readObject();
            this.pocketRepo = (Pocket) obj1;

        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void addActual(T item)
    {
        actualTicketsRepo.add(item);
    }

    public void addPlayed(T item)
    {
        playedTicketsRepo.add(item);
    }

    public void deleteActual(T item)
    {
        actualTicketsRepo.remove(item);
    }

    public void deletePlayed(T item)
    {
        playedTicketsRepo.remove(item);
    }


    public ArrayList<T> getActualTicketsRepo()
    {
        return actualTicketsRepo;
    }

    public Pocket getPocketRepo()
    {
        return pocketRepo;
    }

    public void setActualTicketsRepo(ArrayList<T> actualTicketsRepo)
    {
        this.actualTicketsRepo = actualTicketsRepo;
    }

    public ArrayList<T> getPlayedTicketsRepo()
    {
        return playedTicketsRepo;
    }

    public void setPlayedTicketsRepo(ArrayList<T> playedTicketsRepo)
    {
        this.playedTicketsRepo = playedTicketsRepo;
    }
}

