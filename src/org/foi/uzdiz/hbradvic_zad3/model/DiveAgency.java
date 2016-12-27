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
public class DiveAgency implements ObserverInterface{
    
    protected String name;
    protected List<String> certificates = new ArrayList<String>(){{
        add("R0");
        add("R1");
        add("R2");
        add("R3");
        add("R4");
        add("R5");
        add("I1");
        add("I2");
        add("I3");
        add("I4");
        add("I5");
        add("I6");
        add("I7");
    }};
    protected List<Diver> agencyDivers = new ArrayList<>();

    public DiveAgency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public List<Diver> getAgencyDivers() {
        return agencyDivers;
    }

    public void setAgencyDivers(List<Diver> agencyDivers) {
        this.agencyDivers = agencyDivers;
    }

    @Override
    public void update(DiveDate diveDate, List<Diver> diversList) {
        System.out.println("-NOTIFING- " + name);
        for(Diver diver: diversList){
            if(diver.getAgency().equals(this)){
                 System.out.println(diver.getName() + " | " + diver.getLevel() + " | " + diveDate.getDiveDate() + " | " + diveDate.getMaxDepth());
                 agencyDivers.add(diver);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
