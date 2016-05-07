/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.model;

import java.util.ArrayList;

/**
 *
 * @author RunEvil
 */
public class Country {
    private String name;
    private ArrayList<Drug> drugs;
    private int visits = 1;

    public Country() {
    }

    public Country(String name, ArrayList<Drug> drugs) {
        this.name = name;
        this.drugs = drugs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }
    
    public void visitCounter(){
        this.visits ++;
    }
    
    public int getVisits(){
        return this.visits;
    }
}
