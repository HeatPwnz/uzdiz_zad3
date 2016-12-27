/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.model.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.DiveDate;
import org.foi.uzdiz.hbradvic_zad3.model.Diver;

/**
 *
 * @author Hrvoje
 */
public abstract class AlgorithmAbstract {

    public abstract  AlgorithmProduct createAlgorithm(List<Diver> divers, List<DiveDate> dives, HashMap<DiveDate, ArrayList<Diver>> diversInDates, long seed, List<String> algorithm, String resultsDat, List<DiveAgency> diveAgencies);
    
}
