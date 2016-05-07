package charlie.model;

import java.util.ArrayList;

/**
 *
 * @author RunEvil
 */
public class Stash implements java.io.Serializable
{
    private double money;
    private ArrayList<Drug> stashDrugs;

    public Stash() {
        this.money = 5000.00;
        this.stashDrugs = new ArrayList<>();
    }
    
    public Stash(double money, ArrayList<Drug> drugs) {
        this.money = money;
        this.stashDrugs = drugs;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Drug> getStashDrugs() {
        return stashDrugs;
    }

    public void setStashDrugs(ArrayList<Drug> stashDrugs) {
        this.stashDrugs = stashDrugs;
    }
}