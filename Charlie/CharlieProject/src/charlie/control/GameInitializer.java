/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.control;

import charlie.interfaces.IGameInitializer;
import charlie.model.Country;
import charlie.model.Drug;
import charlie.model.Stash;
import charlie.model.User;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author RunEvil
 */
public class GameInitializer implements IGameInitializer {

    HighscoreController hc;
    ArrayList<Drug> startDrugs;
    ArrayList<Drug> inventoryDrugs;
    ArrayList<Country> countries;
    private String currentCountry;
    private int day;
    private Stash stash;
    private double money;
    private Drug drug;
    private int userAmount;
    private User user;
    private String username;

    public GameInitializer() {
        hc = new HighscoreController();
        startDrugs = initializeDrugs();
        countries = initializeCountries(startDrugs);
        stash = new Stash();
        drug = new Drug();
        hc.loadScoreFile();
    }

    @Override
    public ArrayList<Drug> initializeDrugs() {
        startDrugs = new ArrayList<Drug>();
        inventoryDrugs = new ArrayList();

        //Arguments = name, price, amount, priceStrategy
        Drug cocaine = new Drug("Cocaine", 1200.00, 30, 1);
        Drug heroin = new Drug("Heroin", 1800.00, 40, 5);
        Drug amphetamine = new Drug("Amphetamine", 200.00, 50, 2);
        Drug acid = new Drug("Acid", 550.00, 33, 2);
        Drug angelDust = new Drug("Angel Dust", 1200.00, 30, 6);
        Drug crystalMeth = new Drug("Crystal Meth", 800.00, 38, 0);
        Drug hash = new Drug("Hash", 180.00, 100, 4);
        Drug weed = new Drug("Weed", 150.00, 115, 6);
        Drug mushrooms = new Drug("Mushrooms", 120.00, 95, 3);
        Drug valium = new Drug("Valium", 290.00, 80, 0);

        startDrugs.add(cocaine);
        startDrugs.add(heroin);
        startDrugs.add(amphetamine);
        startDrugs.add(acid);
        startDrugs.add(angelDust);
        startDrugs.add(crystalMeth);
        startDrugs.add(hash);
        startDrugs.add(weed);
        startDrugs.add(mushrooms);
        startDrugs.add(valium);
        
        inventoryDrugs.add(cocaine);
        inventoryDrugs.add(heroin);
        inventoryDrugs.add(amphetamine);
        inventoryDrugs.add(acid);
        inventoryDrugs.add(angelDust);
        inventoryDrugs.add(crystalMeth);
        inventoryDrugs.add(hash);
        inventoryDrugs.add(weed);
        inventoryDrugs.add(mushrooms);
        inventoryDrugs.add(valium);

        return startDrugs;
    }
    
    @Override
    public ArrayList<Country> initializeCountries(ArrayList<Drug> startDrugs) {
        countries = new ArrayList<>();
        
        // Arguments = name, ArrayList
        Country denmark = new Country("Denmark", startDrugs);
        Country colombia = new Country("Colombia", startDrugs);
        Country germany = new Country("Germany", startDrugs);
        Country usa = new Country("USA", startDrugs);
        Country france = new Country("France", startDrugs);
        Country afghanistan = new Country("Afghanistan", startDrugs);

        countries.add(denmark);
        countries.add(colombia);
        countries.add(germany);
        countries.add(usa);
        countries.add(france);
        countries.add(afghanistan);

        return countries;
    }

    public ArrayList<Drug> getDrugs() {
        return startDrugs;
    }
    
    public void setStartDrugs(String name, int amount){
        
        for (int i = 0; i < startDrugs.size(); i++) {
            if(name.equals(startDrugs.get(i).getName())){
                startDrugs.get(i).setAvailability(amount);
            }
        }
    }
    
    public int getAmountStartDrug(String name){
        int amount = 1;
        for (int i = 0; i < startDrugs.size(); i++) {
            if(name.equals(startDrugs.get(i).getName())){
                amount = startDrugs.get(i).getAvailability();
            }
        }
        return amount;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public String retrieveUsername() {
        //String name;
        username = JOptionPane.showInputDialog("What is your name?");
        if (username.isEmpty()) {
            username = "Peter";
        }
        return username;
    }
    
    public void setUsername(String name){
        this.username = name;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public boolean nextDay(String selectedCountry) {
        boolean gameOver = false;

        // Check if it is a new country that has been selected 
        day++;
        if (day > 20) {
            gameOver();
            gameOver = true;
        }

        return gameOver;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(null, "Game over!\nYour drugs are being sold and your final score is being calculated!");
        // Sell inventory and calculate highscore
        JOptionPane.showMessageDialog(null, "Congratulations " + username + " Your highscore is: " + stash.getMoney());
        // Add user to highscore list
        hc.addScore(username, stash);
        // Show highscore list
        JOptionPane.showMessageDialog(null, hc.getHighscoreString());
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getDay() {
        return day;
    }

    public Stash getStash() {
        return stash;
    }

    public ArrayList<Drug> getStashDrugs() {
        return stash.getStashDrugs();
    }
    
    public int getStashDrugAmount(String str){
        int amount = 0;
        for (int i = 0; i < inventoryDrugs.size(); i++) {
            if(str.equals(inventoryDrugs.get(i).getName())){
                amount = inventoryDrugs.get(i).getUserAmount();            }
        }
        return amount;
    }

    public void setDrugs(String str, int boughtAmount) {

            for (int i = 0; i < inventoryDrugs.size(); i++) {   //Run through inventory

                if (str.equals(inventoryDrugs.get(i).getName())) {     // If the drug is not in inventory

                     // get amount, add bought amount & set newAmount
                    int amount = inventoryDrugs.get(i).getUserAmount();
                    int newAmount = amount + boughtAmount;
                    inventoryDrugs.get(i).setUserAmount(newAmount);
                } 
            }    // Sets the inventory in the user's stash
        stash.setStashDrugs(inventoryDrugs);
    }
    
    public void setDrugsSold(String str, int soldAmount) {

            for (int i = 0; i < inventoryDrugs.size(); i++) {   //Run through inventory

                if (str.equals(inventoryDrugs.get(i).getName())) {     // Find the drug in inventory

                    inventoryDrugs.get(i).setUserAmount(soldAmount);
                }
            }
        stash.setStashDrugs(inventoryDrugs); // Sets the inventory in the user's stash
    }       
    
    public void updateInventory(){
        stash.setStashDrugs(inventoryDrugs);
    }

    public void setAvailability(String str, int amount) {

        for (int i = 0; i < startDrugs.size(); i++) {

            if (str.equals(startDrugs.get(i).getName())) {
                startDrugs.get(i).setAvailability(amount);
            }
        }
    }

    public void setPrice(String str, double price) {

        for (int i = 0; i < startDrugs.size(); i++) {

            if (str.equals(startDrugs.get(i).getName())) {
                startDrugs.get(i).setPrice(price);
            }
        }
    }
    
    public double getMoney() {
        money = stash.getMoney();
        return money;
    }

    public void setMoney(double money) {
        stash.setMoney(money);
    }

    public int getUserAmount() {
        return drug.getUserAmount();
    }

    public void setUserAmount(int amount) {
        drug.setUserAmount(amount);
    }

    @Override
    public User initializeUser(String input) {
        return new User() {
            {
                name = input;
                stash = initializeStash();
            }
        };
    }

    //-----------------   PRIVATE METHODS ----------------------//
    private Stash initializeStash() {
        return new Stash();
    }

}
