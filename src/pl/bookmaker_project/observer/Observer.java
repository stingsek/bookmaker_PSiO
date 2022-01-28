package pl.bookmaker_project.observer;

import pl.bookmaker_project.controller.MenuController;


public interface Observer
{
    void update(MenuController menuController, Observable observable);

}
