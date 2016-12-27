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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hEAT
 */
public class DiversClub implements SubjectInterface {

    private static DiversClub instance = null;
    private List<Diver> divers;
    private List<DiveDate> diveDatesList;
    private List<DiveAgency> diveAgencies;
    private List<Diver> diversTEMP;
    private Map<String, String> diverSpec;
    private DiveDate diveDateTEMP;
    private Federation federation;
    private static final String diversRegex = "^([a-zA-Z]{1,});(CMAS|SSI|NAUI|BSAC);((R[0-5])|(I[1-6]));((19|20)\\d{2}$)";
    private static final String specialtyRegex = "^([\\p{L}\\s]+{1,});([\\p{L}\\s]{1,})";
    private static final String equipementRegex = "";
    private static final String datesRegex = "^(\\d{4}.\\d{2}.\\d{2});(([01]?[0-9]|2[0-3]):[0-5][0-9]);(\\d{2});(\\d{1})";

    private DiversClub() {
        divers = new ArrayList<>();
        diveDatesList = new ArrayList<>();
        diveAgencies = new ArrayList<>();
        diversTEMP = new ArrayList<>();
        diverSpec = new HashMap<>();
    }

    public static DiversClub getInstance() {
        if (instance == null) {
            instance = new DiversClub();
        }
        return instance;
    }

    public List<Diver> getDivers() {
        return divers;
    }

    public void setDivers(List<Diver> divers) {
        this.divers = divers;
    }

    public List<DiveDate> getDiveDatesList() {
        return diveDatesList;
    }

    public void setDiveDatesList(List<DiveDate> diveDatesList) {
        this.diveDatesList = diveDatesList;
    }

    public void fillDiversList(String diversDat, String specDat, List<DiveAgency> diveAgencies, Federation federation) {
        this.diveAgencies = diveAgencies;
        this.federation = federation;
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
            System.out.println("Divers added to the DiversClub list: " + divers.size());

            List<DiverSpecs> diverSpecs = new ArrayList<>();
            BufferedReader brSpec = new BufferedReader(new FileReader(new File(specDat)));
            String lineSpec;
            while ((lineSpec = brSpec.readLine()) != null) {
                if (lineSpec.matches(specialtyRegex)) {
                    String[] infoSpec = lineSpec.split(";");
                    diverSpecs.add(new DiverSpecs(infoSpec[0], infoSpec[1]));
                }
            }

            System.out.println(diverSpecs.size());
            List<String> specTEMP;
            for (Diver diver : divers) {
                specTEMP = new ArrayList<>();
                for (DiverSpecs diverName : diverSpecs) {
                    if(diver.getName().equals(diverName.getDiver())){
                        specTEMP.add(diverName.spec);
                    }
                }
                diver.setSpecs(specTEMP);
            }

            System.out.println("Spectialities added to each diver");

            for (Diver diver : divers) {
                System.out.println(diver.getName() + " / " + diver.getSpecs().size());
            }

        } catch (FileNotFoundException ex) {
            throw new IllegalStateException("File that you requested doesn't exist.");
        } catch (IOException ex) {
            Logger.getLogger(DiversClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillDiveDates(String divesDat) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(divesDat)));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.matches(datesRegex)) {
                    String[] info = line.split(";");
                    if (Integer.parseInt(info[3]) > 1) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd");
                        Date date = dateFormat.parse(info[0]);
                        LocalTime time = LocalTime.parse(info[1]);
                        DiveDate diveDates = new DiveDate(date, time, Integer.parseInt(info[2]), Integer.parseInt(info[3]));
                        diveDatesList.add(diveDates);
                    }
                }
            }
            System.out.println("DiveDates added to the DiveDates list: " + diveDatesList.size());

        } catch (FileNotFoundException ex) {
            throw new IllegalStateException("File that you requested doesn't exist.");
        } catch (IOException | ParseException ex) {
            Logger.getLogger(DiversClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<DiveDate, ArrayList<Diver>> sortDiversInDates(List<Diver> divers, List<DiveDate> dates, long seed) throws ParseException {
        HashMap<DiveDate, ArrayList<Diver>> diverGroup = new HashMap<>();
        if (!divers.isEmpty() && !dates.isEmpty()) {
            ArrayList<Diver> dajvers = null;
            DiveDate prethodni = new DiveDate(new Date(), LocalTime.MIN, 0, 0);
            for (DiveDate date : dates) {
                if (!prethodni.getDiveDate().equals(date.getDiveDate())) {
                    dajvers = new ArrayList<>();
                    long seeed = System.nanoTime();
                    Random rnd = new Random(seeed);
                    for (int i = 0; i < date.getNoDivers(); i++) {
                        int index = rnd.nextInt(date.getNoDivers());
                        Diver diver = divers.get(index);
                        if (dajvers.contains(diver)) {
                            i--;
                            continue;
                        }

                        List<DiveDate> dejts = new ArrayList<>();
                        dejts = diver.getDivesHistory();
                        dejts.add(date);
                        diver.setDivesHistory(dejts);

                        dajvers.add(diver);
                        System.out.println(date.getDiveDate().toString() + " - " + date.getDiveTime() + " - " + diver.getName());
                    }
                    prethodni = date;
                    diverGroup.put(date, dajvers);

                    //notify Agency Observers
                    diveDateTEMP = date;
                    diversTEMP = dajvers;
                    notifyObservers();

                    System.out.println("Grupica: " + dajvers.size());
                } else {
                    if (prethodni.getNoDivers() < date.getNoDivers()) {
                        int i = date.getNoDivers() - prethodni.getNoDivers();
                        Random rnd = new Random(seed);
                        for (int j = 0; j < i; j++) {
                            int index = rnd.nextInt(date.getNoDivers());
                            Diver diver = divers.get(index);
                            if (dajvers.contains(diver)) {
                                j--;
                                continue;
                            }

                            List<DiveDate> dejts = new ArrayList<>();
                            dejts = diver.getDivesHistory();
                            dejts.add(date);
                            diver.setDivesHistory(dejts);

                            dajvers.add(diver);
                            System.out.println(date.getDiveDate().toString() + " - " + date.getDiveTime() + " - " + diver.getName());
                        }
                        prethodni = date;
                        diverGroup.put(date, dajvers);

                        //notify Agency Observers
                        diveDateTEMP = date;
                        diversTEMP = dajvers;
                        notifyObservers();

                        System.out.println("+Grupica: " + dajvers.size());
                    } else if (prethodni.getNoDivers() > date.getNoDivers()) {
                        int i = prethodni.getNoDivers() - date.getNoDivers();
                        for (int j = 0; j < i; j++) {
                            dajvers.remove(prethodni.getNoDivers() - i);
                        }
                        prethodni = date;
                        diverGroup.put(date, dajvers);

                        //notify Agency Observers
                        diveDateTEMP = date;
                        diversTEMP = dajvers;
                        notifyObservers();

                        System.out.println("-Grupica: " + dajvers.size());
                    } else {
                        diverGroup.put(date, dajvers);

                        //notify Agency Observers
                        diveDateTEMP = date;
                        diversTEMP = dajvers;
                        notifyObservers();

                        System.out.println("=Grupica: " + dajvers.size());
                    }
                }
            }
            System.out.println("Uspjesno popunjeno " + diverGroup.size() + " termina.");
            return diverGroup;
        } else {
            System.out.println("Jedna od listi nije popunjena.");
            return diverGroup;
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < diveAgencies.size(); i++) {
            ObserverInterface observer = (ObserverInterface) diveAgencies.get(i);
            observer.update(diveDateTEMP, diversTEMP);
        }

        ObserverInterface observer = federation;
        observer.update(diveDateTEMP, diversTEMP);
    }

}
