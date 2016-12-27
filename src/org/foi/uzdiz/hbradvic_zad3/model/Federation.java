/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hEAT
 */
public class Federation implements ObserverInterface{

    protected String name;
    protected List<DiveAgency> agencies = new ArrayList<>();

    public Federation(String name) {
        this.name = name;
    }
    
    @Override
    public void update(DiveDate diveDate, List<Diver> diversList) {
        System.out.println(" -- NOTIFIED -- " + name);
        //nemam pojma kaj drugo ispisivati kad je obavjesten savez, nije napisano u zadaci
    }
    
}
