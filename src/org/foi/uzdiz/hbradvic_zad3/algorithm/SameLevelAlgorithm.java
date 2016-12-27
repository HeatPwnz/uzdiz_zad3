/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.helpers.CertificateHelper;
import org.foi.uzdiz.hbradvic_zad3.helpers.FileOutput;
import org.foi.uzdiz.hbradvic_zad3.model.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.DiveDate;
import org.foi.uzdiz.hbradvic_zad3.model.Diver;
import org.foi.uzdiz.hbradvic_zad3.model.DiverPair;
import org.foi.uzdiz.hbradvic_zad3.model.DiverTriplet;

/**
 *
 * @author hEAT
 */
public class SameLevelAlgorithm extends AlgorithmProduct {

    protected HashMap<DiveDate, ArrayList<Diver>> diversInDates = new HashMap<>();
    protected List<Diver> listaDivera = new ArrayList<>();
    protected List<Diver> fullList = new ArrayList<>();
    protected List<DiveDate> dives = new ArrayList<>();
    protected List<DiveAgency> diveAgencies = new ArrayList<>();
    protected String resultsDat;
    protected FileOutput fileOutput;
    double totalSafetyValue = 0;
    protected boolean sout;
    protected final String algorithm = "SAME CERTIFICATE ALGORITHM";

    public SameLevelAlgorithm(List<Diver> divers, List<DiveDate> dives, HashMap<DiveDate, ArrayList<Diver>> diversInDates, String resultsDat, boolean sout, List<DiveAgency> diveAgencies) {
        this.fullList = divers;
        this.dives = dives;
        this.diversInDates = diversInDates;
        this.resultsDat = resultsDat;
        this.sout = sout;
        this.diveAgencies = diveAgencies;
        sameLevelSort();
    }

    public void sameLevelSort() {
        CertificateHelper certificateHelper = new CertificateHelper();
        for (DiveDate dejt : dives) {
            List<Object> dajverGrupnjak = new ArrayList<>();
            listaDivera = diversInDates.get(dejt);

            //helper arrays
            List<Diver> ostaci = new ArrayList<>();
            List<Diver> grupica;
            List<String> certif = new ArrayList<>();

            //sort listaDivera by divers maxDepth
            Collections.sort(listaDivera, Diver.diverComparatorCert);

            for (Diver dajve : listaDivera) {
                grupica = new ArrayList<>();
                if (!certif.contains(dajve.getLevel())) {
                    certif.add(dajve.getLevel());
                    grupica.add(dajve);
                    for (Diver d : listaDivera) {
                        if (dajve.getLevel().equals(d.getLevel()) && !dajve.getName().equals(d.getName())) {
                            grupica.add(d);
                        }
                    }

                    if (grupica.size() <= 1) {
                        ostaci.add(dajve);
                        //listaDivera.remove(dajve);
                    } else {
                        while (grupica.size() > 0) {
                            if (grupica.size() % 2 == 0) {
                                DiverPair diverPair = new DiverPair(grupica.get(grupica.size() - 1), grupica.get(grupica.size() - 2));
                                int depth = dejt.getMaxDepth();
                                int diverDepths[] = new int[2];
                                int i = 0;
                                for (Diver dajv : diverPair.getDiverPair()) {
                                    int diverDepth = certificateHelper.returnMaxDive(dajv.getLevel());
                                    if (diverDepth >= depth) {
                                        diverDepths[i] = depth;
                                        i++;
                                    } else {
                                        diverDepths[i] = diverDepth;
                                        i++;
                                    }
                                }
                                int maxDive;
                                if ((diverDepths[0] - diverDepths[1]) > 10) {
                                    maxDive = diverDepths[1] + 10;
                                } else if ((diverDepths[1] - diverDepths[0]) > 10) {
                                    maxDive = diverDepths[0] + 10;
                                } else {
                                    if (diverDepths[0] >= diverDepths[1]) {
                                        maxDive = diverDepths[0];
                                    } else {
                                        maxDive = diverDepths[1];
                                    }
                                }
                                //add SafetyValue for each DiveDate group
                                List<Integer> safetyVals = new ArrayList<>();
                                for (Diver dajverinho : diverPair.getDiverPair()) {
                                    safetyVals.add(certificateHelper.returnSafetyValue(dajverinho.getLevel()));
                                }
                                int maxSafety = Collections.max(safetyVals);
                                int minSafety = Collections.min(safetyVals);
                                double result = (double) dejt.getMaxDepth() / (double) ((maxSafety - minSafety) + 1);
                                totalSafetyValue += result;

                                diverPair.setVisitedDepth(maxDive);
                                dajverGrupnjak.add(diverPair);
                                grupica.remove(grupica.size() - 1);
                                grupica.remove(grupica.size() - 1);
                            } else {
                                DiverTriplet diverTriplet = new DiverTriplet(grupica.get(grupica.size() - 1), grupica.get(grupica.size() - 2), grupica.get(grupica.size() - 3));
                                int depth = dejt.getMaxDepth();
                                int diverDepths[] = new int[3];
                                int i = 0;
                                for (Diver dajv : diverTriplet.getDiverTriplet()) {
                                    int diverDepth = certificateHelper.returnMaxDive(dajv.getLevel());
                                    if (diverDepth >= depth) {
                                        diverDepths[i] = depth;
                                        i++;
                                    } else {
                                        diverDepths[i] = diverDepth;
                                        i++;
                                    }
                                }
                                int maxDive;
                                if ((diverDepths[0] - diverDepths[1]) > 10) {
                                    maxDive = diverDepths[1] + 10;
                                } else if ((diverDepths[1] - diverDepths[0]) > 10) {
                                    maxDive = diverDepths[0] + 10;
                                } else {
                                    if (diverDepths[0] >= diverDepths[1]) {
                                        maxDive = diverDepths[0];
                                    } else {
                                        maxDive = diverDepths[1];
                                    }
                                }
                                //add SafetyValue for each DiveDate group
                                List<Integer> safetyVals = new ArrayList<>();
                                for (Diver dajverinho : diverTriplet.getDiverTriplet()) {
                                    safetyVals.add(certificateHelper.returnSafetyValue(dajverinho.getLevel()));
                                }
                                int maxSafety = Collections.max(safetyVals);
                                int minSafety = Collections.min(safetyVals);
                                double result = (double) dejt.getMaxDepth() / (double) ((maxSafety - minSafety) + 1);
                                totalSafetyValue += result;

                                diverTriplet.setVisitedDepth(maxDive);
                                dajverGrupnjak.add(diverTriplet);
                                grupica.remove(grupica.size() - 1);
                                grupica.remove(grupica.size() - 1);
                                grupica.remove(grupica.size() - 1);
                            }
                        }
                    }
                }
            }
            //put rest of divers with no same kind in pairs or triplets
            while (ostaci.size() > 0) {
                if (ostaci.size() % 2 == 0) {
                    DiverPair diverPair = new DiverPair(ostaci.get(ostaci.size() - 1), ostaci.get(ostaci.size() - 2));
                    int depth = dejt.getMaxDepth();
                    int diverDepths[] = new int[2];
                    int i = 0;
                    for (Diver dajv : diverPair.getDiverPair()) {
                        int diverDepth = certificateHelper.returnMaxDive(dajv.getLevel());
                        if (diverDepth >= depth) {
                            diverDepths[i] = depth;
                            i++;
                        } else {
                            diverDepths[i] = diverDepth;
                            i++;
                        }
                    }
                    int maxDive;
                    if ((diverDepths[0] - diverDepths[1]) > 10) {
                        maxDive = diverDepths[1] + 10;
                    } else if ((diverDepths[1] - diverDepths[0]) > 10) {
                        maxDive = diverDepths[0] + 10;
                    } else {
                        if (diverDepths[0] >= diverDepths[1]) {
                            maxDive = diverDepths[0];
                        } else {
                            maxDive = diverDepths[1];
                        }
                    }
                    //add SafetyValue for each DiveDate group
                    List<Integer> safetyVals = new ArrayList<>();
                    for (Diver dajverinho : diverPair.getDiverPair()) {
                        safetyVals.add(certificateHelper.returnSafetyValue(dajverinho.getLevel()));
                    }
                    int maxSafety = Collections.max(safetyVals);
                    int minSafety = Collections.min(safetyVals);
                    double result = (double) dejt.getMaxDepth() / (double) ((maxSafety - minSafety) + 1);
                    totalSafetyValue += result;

                    diverPair.setVisitedDepth(maxDive);
                    dajverGrupnjak.add(diverPair);
                    ostaci.remove(ostaci.size() - 1);
                    ostaci.remove(ostaci.size() - 1);
                } else if (ostaci.size() == 1) {
                    DiverPair diverPair = new DiverPair(ostaci.get(ostaci.size() - 1), ostaci.get(ostaci.size() - 1));
                    int depth = dejt.getMaxDepth();
                    int diverDepths[] = new int[2];
                    int i = 0;
                    for (Diver dajv : diverPair.getDiverPair()) {
                        int diverDepth = certificateHelper.returnMaxDive(dajv.getLevel());
                        if (diverDepth >= depth) {
                            diverDepths[i] = depth;
                            i++;
                        } else {
                            diverDepths[i] = diverDepth;
                            i++;
                        }
                    }
                    int maxDive;
                    if ((diverDepths[0] - diverDepths[1]) > 10) {
                        maxDive = diverDepths[1] + 10;
                    } else if ((diverDepths[1] - diverDepths[0]) > 10) {
                        maxDive = diverDepths[0] + 10;
                    } else {
                        if (diverDepths[0] >= diverDepths[1]) {
                            maxDive = diverDepths[0];
                        } else {
                            maxDive = diverDepths[1];
                        }
                    }
                    //add SafetyValue for each DiveDate group
                    List<Integer> safetyVals = new ArrayList<>();
                    for (Diver dajverinho : diverPair.getDiverPair()) {
                        safetyVals.add(certificateHelper.returnSafetyValue(dajverinho.getLevel()));
                    }
                    int maxSafety = Collections.max(safetyVals);
                    int minSafety = Collections.min(safetyVals);
                    double result = (double) dejt.getMaxDepth() / (double) ((maxSafety - minSafety) + 1);
                    totalSafetyValue += result;

                    diverPair.setVisitedDepth(maxDive);
                    dajverGrupnjak.add(diverPair);
                    ostaci.remove(ostaci.size() - 1);
                } else {
                    DiverTriplet diverTriplet = new DiverTriplet(ostaci.get(ostaci.size() - 1), ostaci.get(ostaci.size() - 2), ostaci.get(ostaci.size() - 3));
                    int depth = dejt.getMaxDepth();
                    int diverDepths[] = new int[3];
                    int i = 0;
                    for (Diver dajv : diverTriplet.getDiverTriplet()) {
                        int diverDepth = certificateHelper.returnMaxDive(dajv.getLevel());
                        if (diverDepth >= depth) {
                            diverDepths[i] = depth;
                            i++;
                        } else {
                            diverDepths[i] = diverDepth;
                            i++;
                        }
                    }
                    int maxDive;
                    if ((diverDepths[0] - diverDepths[1]) > 10) {
                        maxDive = diverDepths[1] + 10;
                    } else if ((diverDepths[1] - diverDepths[0]) > 10) {
                        maxDive = diverDepths[0] + 10;
                    } else {
                        if (diverDepths[0] >= diverDepths[1]) {
                            maxDive = diverDepths[0];
                        } else {
                            maxDive = diverDepths[1];
                        }
                    }
                    //add SafetyValue for each DiveDate group
                    List<Integer> safetyVals = new ArrayList<>();
                    for (Diver dajverinho : diverTriplet.getDiverTriplet()) {
                        safetyVals.add(certificateHelper.returnSafetyValue(dajverinho.getLevel()));
                    }
                    int maxSafety = Collections.max(safetyVals);
                    int minSafety = Collections.min(safetyVals);
                    double result = (double) dejt.getMaxDepth() / (double) ((maxSafety - minSafety) + 1);
                    totalSafetyValue += result;
                    
                    diverTriplet.setVisitedDepth(maxDive);
                    dajverGrupnjak.add(diverTriplet);
                    ostaci.remove(ostaci.size() - 1);
                    ostaci.remove(ostaci.size() - 1);
                    ostaci.remove(ostaci.size() - 1);
                }
            }
            dejt.setDivers(dajverGrupnjak);
            System.out.println(dejt.getDiveDate() + " - broj grupica: " + dejt.getDivers().size());
        }
        System.out.println(" TOTAL SAFETY:   " + totalSafetyValue);
        
        if (sout) {
            this.fileOutput = new FileOutput(fullList, dives, resultsDat, algorithm, diveAgencies);
        }
    }

    public double getTotalSafetyValue() {
        return totalSafetyValue;
    }
    
}
