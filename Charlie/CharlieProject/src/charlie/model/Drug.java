/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.model;

/**
 *
 * @author RunEvil
 */
public class Drug implements java.io.Serializable
{
    private String name;
    private double price;
    private int availability;
    private int priceStrategy;
    private int userAmount;

    public Drug() {
    }

    public Drug(String name, double price, int availability, int priceStrategy) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.priceStrategy = priceStrategy;
        this.userAmount = 0;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getPriceStrategy() {
        return priceStrategy;
    }

    public void setPriceStrategy(int priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public int getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(int userAmount) {
        this.userAmount = userAmount;
    }
    
}
