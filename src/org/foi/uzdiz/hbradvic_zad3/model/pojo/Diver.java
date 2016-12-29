/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model.pojo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.helpers.CertificateHelper;

/**
 *
 * @author hEAT
 */
public class Diver {
    
    private final String name;
    private final DiveAgency agency;
    //private final String agency;
    private final String level;
    private final int year;
    private List<String> specs;
    private List<DiveDate> divesHistory = new ArrayList<>();

    private Diver(DiverBuilder builder){
        this.name = builder.name;
        this.agency = builder.agency;
        this.level = builder.level;
        this.year = builder.year;
        //this.specs = builder.specs;
        //this.divesHistory = builder.divesHistory;
    }
    
    public String getName() {
        return name;
    }

    public DiveAgency getAgency() {
        return agency;
    }

    public String getLevel() {
        return level;
    }

    public int getYear() {
        return year;
    }

    public List<String> getSpecs() {
        return specs;
    }

    public void setSpecs(List<String> specs) {
        this.specs = specs;
    }

    public List<DiveDate> getDivesHistory() {
        return divesHistory;
    }

    public void setDivesHistory(List<DiveDate> divesHistory) {
        this.divesHistory = divesHistory;
    }

    public static Comparator<Diver> diverComparatorDepth = new Comparator<Diver>() {
        CertificateHelper ch = new CertificateHelper();
        @Override
        public int compare(Diver o1, Diver o2) {
            int diverDepth1 = ch.returnMaxDive(o1.getLevel());
            int diverDepth2 = ch.returnMaxDive(o2.getLevel());
            
            return Integer.compare(diverDepth1, diverDepth2);
        }
    };
    
    public static Comparator<Diver> diverComparatorCert = new Comparator<Diver>() {
        CertificateHelper ch = new CertificateHelper();
        @Override
        public int compare(Diver o1, Diver o2) {
            String diverCert1 = o1.getLevel();
            String diverCert2 = o2.getLevel();
            
            return diverCert1.compareTo(diverCert2);
        }
    };
    
    public static class DiverBuilder{
        private final String name;
        private final DiveAgency agency;
        private final String level;
        private final int year;
        private List<String> specs;

        public DiverBuilder(String name, DiveAgency agency, String level, int year) {
            this.name = name;
            this.agency = agency;
            this.level = level;
            this.year = year;
        }
        
        /*public DiverBuilder dives(List<String> specs) {
            this.specs = specs;
            return this;
        }*/
           
        public Diver build(){
            return new Diver(this);
        }
        
    }
    
}
