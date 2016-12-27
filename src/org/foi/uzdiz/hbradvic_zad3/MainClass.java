/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.hbradvic_zad3.algorithm.AlgorithmFactory;
import org.foi.uzdiz.hbradvic_zad3.model.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.DiveDate;
import org.foi.uzdiz.hbradvic_zad3.model.Diver;
import org.foi.uzdiz.hbradvic_zad3.model.DiversClub;
import org.foi.uzdiz.hbradvic_zad3.model.Federation;

/**
 *
 * @author Hrvoje
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (checkInputParams(args)) {
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            int rowsTank = Integer.parseInt(args[2]);
            
            String diversDat = args[3];
            //String divesDat = args[4];
            String specDat = args[4];
            String equipDat = args[5];

            int depth = Integer.parseInt(args[6]);
            int temp = Integer.parseInt(args[7]);
            int night = Integer.parseInt(args[8]);
            int recording = Integer.parseInt(args[9]);
            
            //String resultsDat = args[6];

            List<DiveAgency> diveAgencies = new ArrayList<>();
            diveAgencies = fillDivingAgencies();
            Federation federation = new Federation("HRS");

            HashMap<DiveDate, ArrayList<Diver>> diversInDates = new HashMap<>();
            List<Diver> divers = new ArrayList<>();
            //List<DiveDate> diveDatesList = new ArrayList<>();

            DiversClub.getInstance().fillDiversList(diversDat, specDat, diveAgencies, federation);
            //DiversClub.getInstance().fillDiveDates(divesDat);

            divers = DiversClub.getInstance().getDivers();
            /* diveDatesList = DiversClub.getInstance().getDiveDatesList();
                try {
                    diversInDates = DiversClub.getInstance().sortDiversInDates(divers, diveDatesList, seed);
                } catch (ParseException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }*/

            AlgorithmFactory algorithmFactory = new AlgorithmFactory();
            //algorithmFactory.createAlgorithm(divers, diveDatesList, diversInDates, seed, realAlgorithms, resultsDat, diveAgencies);

        }else{
            System.out.println("Params you entered are wrong, fixerinho needed.");
        }
    }

    private static boolean checkInputParams(String[] args){
        if(args == null || args.length <= 0 || args.length >= 11){
            return false;
        }
        int noRows = Integer.parseInt(args[0]);
        if(noRows<24 || noRows>80){
            return false;
        }
        int noCols = Integer.parseInt(args[1]);
        if(noCols<80 || noCols>160){
            return false;
        }
        int noRowsTank = Integer.parseInt(args[2]);
        if(noRowsTank<noRows || noRowsTank>400){
            return false;
        }
        int depth = Integer.parseInt(args[6]);
        if(depth<5 || depth>40){
            return false;
        }
        int temp = Integer.parseInt(args[7]);
        if(temp<0 || temp>35){
            return false;
        }
        int night = Integer.parseInt(args[8]);
        if(night!=1 && night!=0){
            return false;
        }
        int recording = Integer.parseInt(args[9]);
        if(recording<0 || recording>999){
            return false;
        }
        return true;
    }
    
    private static List<String> checkAlgorithms(List<String> algorithms) {
        List<String> realOnes = new ArrayList<>();
        //add only correct algorithms
        for (String alg : algorithms) {
            if (alg.equals("RandomAlgorithm") || alg.equals("SameLevelAlgorithm") || alg.equals("MaxDepthAlgorithm")) {
                realOnes.add(alg);
            }
        }
        //check if there is duplicate algorithms
        int counter = 0;
        for (String check : realOnes) {
            for (String a : realOnes) {
                if (check.equals(a)) {
                    counter++;
                }
            }
            if (counter > 1) {
                break;
            }
            counter = 0;
        }
        if (counter > 1) {
            System.out.println("bezi van ima duplikata");
            realOnes.clear();
        }
        return realOnes;
    }

    private static List<DiveAgency> fillDivingAgencies() {
        List<DiveAgency> agencies = new ArrayList<>();

        DiveAgency agency1 = new DiveAgency("CMAS");
        agencies.add(agency1);
        DiveAgency agency2 = new DiveAgency("SSI");
        agencies.add(agency2);
        DiveAgency agency3 = new DiveAgency("NAUI");
        agencies.add(agency3);
        DiveAgency agency4 = new DiveAgency("BSAC");
        agencies.add(agency4);

        return agencies;
    }

}
