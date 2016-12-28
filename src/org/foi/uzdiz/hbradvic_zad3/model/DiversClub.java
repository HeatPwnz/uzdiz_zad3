/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.hbradvic_zad3.helpers.CertificateHelper;
import org.foi.uzdiz.hbradvic_zad3.view.observer.DisplayObserver;
import org.foi.uzdiz.hbradvic_zad3.view.observer.InputObserver;

/**
 *
 * @author hEAT
 */
public class DiversClub {

    private static DiversClub instance = null;
    private List<Diver> divers;
    private List<DiveAgency> diveAgencies;
    private CertificateHelper certificateHelper;
    private static final String diversRegex = "^([a-zA-Z]{1,});(CMAS|SSI|NAUI|BSAC);((R[0-5])|(I[1-6]));((19|20)\\d{2}$)";
    private static final String specialtyRegex = "^([\\p{L}\\s]+{1,});([\\p{L}\\s]{1,})";
    
    private final List<DisplayObserver> outputDisplayObservers;
    private final List<InputObserver> inputDisplayObservers;

    public DiversClub() {
        divers = new ArrayList<>();
        diveAgencies = new ArrayList<>();
        certificateHelper = new CertificateHelper();
        outputDisplayObservers = new ArrayList<>();
        inputDisplayObservers = new ArrayList<>();
    }
    
    public void addOutputDisplayObserver(DisplayObserver observer){
        outputDisplayObservers.add(observer);
    }
    
    public void addInputDisplayObservers(InputObserver observer){
        inputDisplayObservers.add(observer);
    }

    public List<Diver> getDivers() {
        return divers;
    }

    public void setDivers(List<Diver> divers) {
        this.divers = divers;
    }

    public List<Diver> fillDiversList(String diversDat, String specDat, List<DiveAgency> diveAgencies) {
        this.diveAgencies = diveAgencies;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(diversDat)));
            String line;
            DiveAgency agency = null;
            while ((line = br.readLine()) != null) {
                if (line.matches(diversRegex)) {
                    String[] info = line.split(";");
                    for (DiveAgency ag : diveAgencies) {
                        if (info[1].equals(ag.getName())) {
                            agency = ag;
                        }
                    }
                    Diver diver = new Diver.DiverBuilder(info[0], agency, info[2], Integer.parseInt(info[3])).build();
                    divers.add(diver);
                }
            }

            List<DiverSpecs> diverSpecs = new ArrayList<>();
            BufferedReader brSpec = new BufferedReader(new FileReader(new File(specDat)));
            String lineSpec;
            while ((lineSpec = brSpec.readLine()) != null) {
                if (lineSpec.matches(specialtyRegex)) {
                    String[] infoSpec = lineSpec.split(";");
                    diverSpecs.add(new DiverSpecs(infoSpec[0], infoSpec[1]));
                }
            }

            List<String> specTEMP;
            for (Diver diver : divers) {
                specTEMP = new ArrayList<>();
                for (DiverSpecs diverName : diverSpecs) {
                    if (diver.getName().equals(diverName.getDiver())) {
                        specTEMP.add(diverName.spec);
                    }
                }
                diver.setSpecs(specTEMP);
            }

        } catch (FileNotFoundException ex) {
            throw new IllegalStateException("File that you requested doesn't exist.");
        } catch (IOException ex) {
            Logger.getLogger(DiversClub.class.getName()).log(Level.SEVERE, null, ex);
        }

        return divers;
    }

    public List<Diver> filterDivers(List<Diver> divers, int depth, int temp, int night, int recording) {
        List<Diver> filteredByDepth = new ArrayList<>();
        List<Diver> filteredListHelper = new ArrayList<>();

        //add divers that are able to dive to specified depth
        for (Diver diver : divers) {
            if ((certificateHelper.returnMaxDive(diver.getLevel()) + 10) >= depth) {
                filteredByDepth.add(diver);
            }
        }

        //add divers that are able to dive in cold waters
        for (Diver diver : filteredByDepth) {
            if (diver.getSpecs().size() == 3) {
                filteredListHelper.add(diver);
                continue;
            }
            if (temp <= 15) {
                for (String cert : diver.getSpecs()) {
                    if (cert.equals("Suho odijelo")) {
                        filteredListHelper.add(diver);
                        break;
                    }
                }
            } else if (temp > 15) {
                filteredListHelper.add(diver);
            }
        }

        //add divers that are able to dive by night
        filteredByDepth.clear();
        for (Diver diver : filteredListHelper) {
            if (diver.getSpecs().size() == 3) {
                filteredByDepth.add(diver);
                continue;
            }
            if (night == 1) {
                for (String cert : diver.getSpecs()) {
                    if (cert.equals("Noćno ronjenje")) {
                        filteredByDepth.add(diver);
                        break;
                    }
                }
            } else if (night < 1) {
                filteredByDepth.add(diver);
            }
        }

        //count divers with photography spec
        int noPhotographDivers = 0;
        List<Diver> photographDivers = new ArrayList<>();
        for (Diver diver : filteredByDepth) {
            for (String cert : diver.getSpecs()) {
                if (cert.equals("Podvodni fotograf")) {
                    photographDivers.add(diver);
                    noPhotographDivers++;
                }
            }
        }

        //filteredListHelper.clear();
        if (recording != 0) {
            if (noPhotographDivers >= recording) {
                return photographDivers;
            } else {
                ///System.out.println("There is too few or too many divers for Photography Diving Session");
                //return null;
            }
        }

        return filteredByDepth;
    }

    public void doYourWork(List<Diver> filteredDivers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
