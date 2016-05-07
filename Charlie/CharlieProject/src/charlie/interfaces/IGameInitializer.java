/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.interfaces;

import charlie.model.Country;
import charlie.model.Drug;
import charlie.model.User;
import java.util.ArrayList;

/**
 *
 * @author RunEvil
 */
public interface IGameInitializer {
    ArrayList<Drug> initializeDrugs();
    ArrayList<Country> initializeCountries(ArrayList<Drug> startDrugs);
    User initializeUser(String input);
}
