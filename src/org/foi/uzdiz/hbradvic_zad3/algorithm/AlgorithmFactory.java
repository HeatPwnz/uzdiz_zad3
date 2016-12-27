/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.foi.uzdiz.hbradvic_zad3.model.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.DiveDate;
import org.foi.uzdiz.hbradvic_zad3.model.Diver;

/**
 *
 * @author hEAT
 */
public class AlgorithmFactory extends AlgorithmAbstract {

    @Override
    public AlgorithmProduct createAlgorithm(List<Diver> divers, List<DiveDate> dives, HashMap<DiveDate, ArrayList<Diver>> diversInDates, long seed, List<String> algorithms, String resultsDat, List<DiveAgency> diveAgencies) {
        HashMap<String, Double> xD = new HashMap<>();
        if (dives.isEmpty() || diversInDates.isEmpty()) {
            System.out.println("Files you provided have some errors inside them, pls fix m8");
            return null;
        }
        for (String algorithm : algorithms) {
            switch (algorithm) {
                case "MaxDepthAlgorithm":
                    MaxDivesAlgorithm maxDivesAlgorithm = new MaxDivesAlgorithm(divers, dives, diversInDates, resultsDat, false, diveAgencies);
                    System.out.println("MaxDepthAlgorithm");
                    xD.put("MaxDepthAlgorithm", maxDivesAlgorithm.getTotalSafetyValue());
                    break;
                //return maxDivesAlgorithm;
                case "RandomAlgorithm":
                    RandomAlgorithm randomAlgorithm = new RandomAlgorithm(divers, dives, diversInDates, seed, resultsDat, false, diveAgencies);
                    System.out.println("RandomAlgorithm");
                    xD.put("RandomAlgorithm", randomAlgorithm.getTotalSafetyValue());
                    break;
                //return randomAlgorithm;
                case "SameLevelAlgorithm":
                    SameLevelAlgorithm sameLevelAlgorithm = new SameLevelAlgorithm(divers, dives, diversInDates, resultsDat, false, diveAgencies);
                    System.out.println("SameLevelAlgorithm");
                    xD.put("SameLevelAlgorithm", sameLevelAlgorithm.getTotalSafetyValue());
                    break;
                //return sameLevelAlgorithm;
                default:
                    System.out.println("You submited non-existant algorithm");
                //return null;
            }
        }
        //get minimum SafetyValue
        Entry<String, Double> min = null;
        for (Entry<String, Double> entry : xD.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        switch (min.getKey()) {
            case "MaxDepthAlgorithm":
                AlgorithmProduct maxDivesAlgorithm = new MaxDivesAlgorithm(divers, dives, diversInDates, resultsDat, true, diveAgencies);
                System.out.println("MaxDepthAlgorithm");
                return maxDivesAlgorithm;
            case "RandomAlgorithm":
                AlgorithmProduct randomAlgorithm = new RandomAlgorithm(divers, dives, diversInDates, seed, resultsDat, true, diveAgencies);
                System.out.println("RandomAlgorithm");
                return randomAlgorithm;
            case "SameLevelAlgorithm":
                AlgorithmProduct sameLevelAlgorithm = new SameLevelAlgorithm(divers, dives, diversInDates, resultsDat, true, diveAgencies);
                System.out.println("SameLevelAlgorithm");
                return sameLevelAlgorithm;
            default:
                System.out.println("You submited non-existant algorithm");
                return null;
            }

    }
}
