package charlie.start;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import charlie.control.GameInitializer;
import charlie.view.MainWindow;

/**
 *
 * @author RunEvil
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameInitializer init = new GameInitializer();
        String name = init.retrieveUsername();
        init.initializeUser(name);
        MainWindow mainWindow = new MainWindow(name);
    }
}
