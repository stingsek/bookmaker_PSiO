package pl.bookmaker_project.observer;

public interface Observable
{
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
