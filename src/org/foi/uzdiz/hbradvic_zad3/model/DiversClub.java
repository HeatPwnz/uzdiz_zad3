/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

import org.foi.uzdiz.hbradvic_zad3.model.pojo.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.Diver;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.DiverSpecs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.hbradvic_zad3.controller.ControllerInterface;
import org.foi.uzdiz.hbradvic_zad3.helpers.CertificateHelper;
import org.foi.uzdiz.hbradvic_zad3.model.memento.Caretaker;
import org.foi.uzdiz.hbradvic_zad3.model.memento.Originator;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.DiveEquipment;
import org.foi.uzdiz.hbradvic_zad3.view.observer.DisplayObserver;
import org.foi.uzdiz.hbradvic_zad3.view.observer.InputObserver;

/**
 *
 * @author hEAT
 */
public class DiversClub implements ControllerInterface{

    private List<Diver> divers;
    private List<DiveAgency> diveAgencies;
    private CertificateHelper certificateHelper;
    private static final String diversRegex = "^([a-zA-Z]{1,});(CMAS|SSI|NAUI|BSAC);((R[0-5])|(I[1-6]));((19|20)\\d{2}$)";
    private static final String specialtyRegex = "^([\\p{L}\\s]+{1,});([\\p{L}\\s]{1,})";
    private List<Diver> filteredCrew;
    private final String diversDat;
    private final String specDat;
    private final String equipDat;
    private final int depth;
    private final int temp;
    private final int night;
    private final int recording;
    protected Originator originator;
    protected Caretaker caretaker;
    
    private final List<DisplayObserver> outputDisplayObservers;
    private final List<InputObserver> inputDisplayObservers;

    public DiversClub(String diversDat, String specDat, String equipDat, List<DiveAgency> diveAgencies, int depth, int temp, int night, int recording) {
        this.diversDat = diversDat;
        this.specDat = specDat;
        this.equipDat = equipDat;
        this.divers = new ArrayList<>();
        this.filteredCrew = new ArrayList<>();
        this.diveAgencies = new ArrayList<>();
        this.certificateHelper = new CertificateHelper();
        this.outputDisplayObservers = new ArrayList<>();
        this.inputDisplayObservers = new ArrayList<>();
        this.depth = depth;
        this.temp = temp;
        this.night = night;
        this.recording = recording;
        this.originator = new Originator();
        this.caretaker = new Caretaker();
    }
    
    public void addOutputDisplayObserver(DisplayObserver observer){
        outputDisplayObservers.add(observer);
    }
    
    public void addInputDisplayObservers(InputObserver observer){
        inputDisplayObservers.add(observer);
    }
    
    public void notifyOutpuDisplayObservers(Object object) {
        for (DisplayObserver observer : outputDisplayObservers) {
            observer.update(object);
        }
    }
    /*
    public void notifyInputDisplayObservers(Object object) {
        for (InputObserver observer : inputDisplayObservers) {
            observer.update(t);
        }
    }*/
    
    public List<Diver> getDivers() {
        return divers;
    }

    public void setDivers(List<Diver> divers) {
        this.divers = divers;
    }

    public List<Diver> fillDiversList() {
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
        originator.setDivers(divers);
        caretaker.add(originator.saveStateToMemento());
        return divers;
    }

    public List<Diver> filterDivers() {
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
                this.filteredCrew = photographDivers;
                originator.setDivers(filteredCrew);
                caretaker.add(originator.saveStateToMemento());
                return photographDivers;
            } else {
                ///System.out.println("There is too few or too many divers for Photography Diving Session");
                //return null;
            }
        }
        
        originator.setDivers(filteredByDepth);
        caretaker.add(originator.saveStateToMemento());
        this.filteredCrew = filteredByDepth;
        return filteredByDepth;
    }
    
    public void createEquipementStructure(){
        try {
            List<DiveEquipment> equipementList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(new File(equipDat)));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches(diversRegex)) {
                    String[] info = line.split(";");
                    if(info.length==2){
                        DiveEquipment diveEquipment = new DiveEquipment(info[0], info[1]);
                        equipementList.add(diveEquipment);
                    }else{
                        List<String> equip = new ArrayList<>();
                        equip.add(info[3]);
                        equip.add(info[4]);
                        equip.add(info[5]);
                        equip.add(info[6]);
                        equip.add(info[7]);
                        DiveEquipment diveEquipment = new DiveEquipment(info[0], info[1]);
                        diveEquipment.setDepth(Integer.parseInt(info[2]));
                        diveEquipment.setRequirements(equip);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DiversClub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DiversClub.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public void doYourWork() {
        fillDiversList();
        filterDivers();
        createEquipementStructure();
    }
    

    @Override
    public Object DisplayDivers() {
        notifyOutpuDisplayObservers("Time to show you the world... jk look at deez divers");
        System.out.println("Filetered divers...");
        for(Diver diver : filteredCrew){
            System.out.print(" - " + diver.getName());
        }
        System.exit(0);
        return null;
    }

    @Override
    public Object ReturnStateFromMemento() {
        notifyOutpuDisplayObservers("Retriving first state from Memento...");
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("Getting first model state from Memento...");
        for(Diver diver : originator.getDivers()){
            System.out.print(" + " + diver.getName());
        }
        System.exit(0);
        return null;
    }

    @Override
    public Object ExitProgram() {
        notifyOutpuDisplayObservers("ALT+F4...");
        System.out.println("ALT+F4...");
        System.exit(0);
        return null;
    }
}
