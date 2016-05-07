/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.control;

import charlie.interfaces.ICalculateDrugs;
import charlie.model.Country;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

/**
 *
 * @author RunEvil
 */
public class DrugController implements ICalculateDrugs {

    GameInitializer gameInitializer;
    private Random randomizer;
    Country country;
    private String name;
    private double price;
    private int price2;
    private int counter;
    private int availability;
    private int priceStrategy;

    public DrugController() {
        gameInitializer = new GameInitializer();
        this.randomizer = new Random();
        country = new Country();
        counter = 0;
    }

    // 0 = Normal (recalculateDrug), 1 = Old Memory, 2 = CompletelyRandom, 3 = Seconds of the Clock, 4 = simpleTwoChoice, 5 = tenPercent, 6 = pickOneOfTen
    public void calculateDrug() {
        //Loop through arraylist of drugs and do switch on index of priceStrategy.
        for (int i = 0; i < gameInitializer.startDrugs.size(); i++) {
            name = gameInitializer.startDrugs.get(i).getName();
            price = gameInitializer.startDrugs.get(i).getPrice();
            availability = gameInitializer.startDrugs.get(i).getAvailability();
            priceStrategy = gameInitializer.startDrugs.get(i).getPriceStrategy();

            switch (priceStrategy) {
                case 0:
                    recalculateDrug();
                    break;
                case 1:
                    oldMemory();
                    break;
                case 2:
                    completelyRandom();
                    break;
                case 3:
                    secondsOfTheClock();
                    break;
                case 4:
                    simpleTwoChoice();
                    break;
                case 5:
                    tenPercent();
                    break;
                case 6:
                    pickOneOfTen();
                    break;
            }
        }
    }

    // Normal
    public void recalculateDrug() {
        int priceChance = randomizer.nextInt(100); // calculateNewPrice(price)
        if (priceChance < 65) {
            gameInitializer.setPrice(name, calculateNewPrice(price));
        }

        int availabilityChance = randomizer.nextInt(100);
        if (availabilityChance < 65) {
            gameInitializer.setAvailability(name, calculateNewAvailability(availability));
        }
    }

    // Simple Two Choice
    public void simpleTwoChoice() {
        // Start with the White Price & Amount and then simply switch to the other price every turn.

        int visit;
        visit = country.getVisits();

        // Black price: 300 - black amount: 180
        if (visit % 2 == 0) {
            gameInitializer.setPrice(name, 300);
            gameInitializer.setAvailability(name, 180);
        } else { // White price: 90 - white amount: 50
            gameInitializer.setPrice(name, 90);
            gameInitializer.setAvailability(name, 50);
        }
//        Example: The first time the White Price and White amount is used. The second time the
//        Black Price and Black Amount must be used. The third time the White Price and White
//        amount is used and so on.
    }

    // Seconds Of The Clock
    public void secondsOfTheClock() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        int sec;
        sec = cal.get(Calendar.SECOND);

        // Price: Add 1 to the clock if it’s exactly 00 to avoid 0$ price.
        if (sec == 00) {
            sec++;
            // Price: the double of the current clocks seconds.
            gameInitializer.setPrice(name, sec * 2);
            // Amount: the current clocks seconds.
            gameInitializer.setAvailability(name, sec);
        } else {
            // Price: the double of the current clocks seconds.
            gameInitializer.setPrice(name, sec * 2);
            // Amount: the current clocks seconds.
            gameInitializer.setAvailability(name, sec);
        }
    }

    // Completely Random
    public void completelyRandom() {
        Random priceRand = new Random();

        int priceNumber = 0;

        priceNumber = 10 + priceRand.nextInt(12010);
        
        gameInitializer.setPrice(name, priceNumber);

        Random availabilityRand = new Random();

        int availabilityNumber = 0;

        availabilityNumber = 2 + availabilityRand.nextInt(402);
        
        gameInitializer.setAvailability(name, availabilityNumber);

    }

    // Old Memory
    public void oldMemory() {
        boolean upDown;
        boolean upDown2;

        switch (counter) {

            case 0:
                price2 = 1000;
                availability = 100;
                gameInitializer.setPrice(name, price2);
                gameInitializer.setAvailability(name, availability);
                break;
            case 1:
                upDown = randomizer.nextBoolean();
                int priceModifier = randomizer.nextInt(66 - 10) + 1;
                int modifiedPrice = price2 * priceModifier;
                int newPrice = (upDown ? (price2 = price2 + modifiedPrice) : ( price2 = price2 - modifiedPrice )); 
                price2 = Math.abs(newPrice + 75);      // Return the absolute value                                 
                availability += 7;
                gameInitializer.setPrice(name, price2);
                gameInitializer.setAvailability(name, availability);
                break;
            default:
                upDown2 = randomizer.nextBoolean();
                int priceModifier2 = randomizer.nextInt(36 - 10) + 1;
                int modifiedPrice2 = price2 * priceModifier2;
                int newPrice2 = (upDown2 ? (price2 = price2 + modifiedPrice2) : ( price2 = price2 - modifiedPrice2 ));
                price2 = Math.abs(newPrice2 + 75);      // Return the absolute value 
                availability += 13;
                gameInitializer.setPrice(name, price2);
                gameInitializer.setAvailability(name, availability);
                break;
        }
        counter++;
    }

    // tenPercent
    public void tenPercent() {
        // The price starts at 180 and the amount starts at 40. Both price and amount goes up by 10 percent each round
        double newPrice;
        int newAmount;

        newPrice = price * 1.1;
        newAmount = (int) (availability * 1.1);

        gameInitializer.setPrice(name, newPrice);
        gameInitializer.setAvailability(name, newAmount);
    }

    // pickOneOfTen
    public void pickOneOfTen() {
        int priceColumn = randomizer.nextInt(9);

        switch (priceColumn) {
            case 0:
                gameInitializer.setPrice(name, 150);
                break;
            case 1:
                gameInitializer.setPrice(name, 230);
                break;
            case 2:
                gameInitializer.setPrice(name, 180);
                break;
            case 3:
                gameInitializer.setPrice(name, 2350);
                break;
            case 4:
                gameInitializer.setPrice(name, 17);
                break;
            case 5:
                gameInitializer.setPrice(name, 360);
                break;
            case 6:
                gameInitializer.setPrice(name, 190);
                break;
            case 7:
                gameInitializer.setPrice(name, 440);
                break;
            case 8:
                gameInitializer.setPrice(name, 550);
                break;
        }

        int amountColumn = randomizer.nextInt(9);

        switch (amountColumn) {
            case 0:
                gameInitializer.setAvailability(name, 100);
                break;
            case 1:
                gameInitializer.setAvailability(name, 190);
                break;
            case 2:
                gameInitializer.setAvailability(name, 200000);
                break;
            case 3:
                gameInitializer.setAvailability(name, 2);
                break;
            case 4:
                gameInitializer.setAvailability(name, 95);
                break;
            case 5:
                gameInitializer.setAvailability(name, 30);
                break;
            case 6:
                gameInitializer.setAvailability(name, 165);
                break;
            case 7:
                gameInitializer.setAvailability(name, 185);
                break;
            case 8:
                gameInitializer.setAvailability(name, 250);
                break;
        }
    }
    
    public GameInitializer getGameInitializer(){
        return gameInitializer;
    }

    //-----------------   PRIVATE METHODS ----------------------//
    private double calculateNewPrice(double currentPrice) {
        int upOrDown = randomizer.nextInt(2);
        int changePercentage = randomizer.nextInt(85) + 1;
        double newPrice;

        if (upOrDown == 0) {
            newPrice = currentPrice - (currentPrice * changePercentage / 100);
        } else {
            newPrice = currentPrice + (currentPrice * changePercentage / 100);
        }

        return newPrice;
    }

    private int calculateNewAvailability(int availability) {
        int upOrDown = randomizer.nextInt(2);
        int changePercentage = randomizer.nextInt(40) + 16;
        int newAvailability;

        if (upOrDown == 0) {
            newAvailability = (int) Math.floor(availability - (availability * changePercentage / 100));
        } else {
            newAvailability = (int) Math.floor(availability + (availability * changePercentage / 100));
        }

        return newAvailability;
    }

}
