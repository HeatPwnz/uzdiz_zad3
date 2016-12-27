/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.helpers;

import java.util.HashMap;

/**
 *
 * @author Hrvoje
 */
public class CertificateHelper {

    
    private HashMap<String, String[]> proCerts = new HashMap<String, String[]>();
    private HashMap<String, String[]> recCerts = new HashMap<String, String[]>();
    private HashMap<String, Integer> certs = new HashMap<String, Integer>(){{
        put("R0", 0);
        put("R1", 10);
        put("R2", 30);
        put("R3", 40);
        put("R4", 40);
        put("R5", 40);
    }};
    private HashMap<String, Integer> safetyValues = new HashMap<String, Integer>(){{
        put("R0", 0);
        put("R1", 1);
        put("R2", 2);
        put("R3", 3);
        put("R4", 4);
        put("R5", 5);
        put("I1", 6);
        put("I2", 7);
        put("I3", 8);
        put("I4", 9);
        put("I5", 10);
        put("I6", 11);
        put("I7", 12);
        put("I8", 13);
    }};

    public CertificateHelper() {
          
        recCerts.put("SSI",  new String[] { "Scuba Diver", "Open Water Diver", "Advanced Adventure", "Diver Stress & Rescue", "Advanced Open Water Diver", "Master Diver" });
        recCerts.put("NAUI", new String[] { "Scuba Diver", "Scuba Diver", "Advanced Scuba Diver", "Scuba Rescue Diver", "Master Scuba Diver", "Master Scuba Diver" });
        recCerts.put("BSAC", new String[] { "Ocean Diver", "Ocean Diver", "Ocean Diver", "Sports Diver", "Sports Diver", "Sports Diver" });
        recCerts.put("CMAS", new String[] { "One Star Diver", "One Star Diver", "One Star Diver", "Two Star Diver", "Two Star Diver", "Two Star Diver" });

        proCerts.put("SSI",  new String[] { "Dive Guide", "Divemaster", "Dive Control Specialist", "Open Water Instructor", "Advanced Open Water Instructor", "Divemaster Instructor", "Dive Control Specialist Instructor", "Instructor Trainer" });
        proCerts.put("NAUI", new String[] { "Assistant Instructor", "Divemaster", "Divemaster", "Instructor", "Instructor", "Instructor", "Instructor", "Instructor Trainer" });
        proCerts.put("BSAC", new String[] { "Dive Leader", "Dive Leader", "Assistant Open Water Instructor", "Open Water Instructor", "Advanced Instructor", "Advanced Instructor", "Advanced Instructor", "Advanced Instructor" });
        proCerts.put("CMAS", new String[] { "Three Star Diver", "Three Star Diver", "One Star Instructor", "Two Star Instructor", "Two Star Instructor", "Two Star Instructor", "Two Star Instructor" });
    }

    public HashMap<String, String[]> getProCerts() {
        return proCerts;
    }

    public void setProCerts(HashMap<String, String[]> proCerts) {
        this.proCerts = proCerts;
    }

    public HashMap<String, String[]> getRecCerts() {
        return recCerts;
    }

    public void setRecCerts(HashMap<String, String[]> recCerts) {
        this.recCerts = recCerts;
    }

    public HashMap<String, Integer> getCerts() {
        return certs;
    }

    public void setCerts(HashMap<String, Integer> certs) {
        this.certs = certs;
    }
    
    public int returnMaxDive(String cert){
        if(cert.startsWith("R")){
            return certs.get(cert);
        }else if(cert.startsWith("I")){
            return 40;
        }else{
            return 0;
        }
    }

    public int returnSafetyValue(String cert){
        return safetyValues.get(cert);
    }
}
