/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.helpers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.DiveDate;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.Diver;

/**
 *
 * @author Hrvoje
 */
public class FileOutput {

    protected List<DiveDate> dates = new ArrayList<>();
    protected List<Diver> diversList = new ArrayList<>();
    protected List<DiveAgency> diveAgencies = new ArrayList<>();
    protected String outputFile = "";
    protected String algorithm = "";

    public FileOutput(List<Diver> diversList, List<DiveDate> dates, String outputFile, String algorithm, List<DiveAgency> diveAgencies) {
        this.diversList = diversList;
        this.dates = dates;
        this.outputFile = outputFile;
        this.algorithm = algorithm;
        this.diveAgencies = diveAgencies;
        writeToFile();
    }

    public void writeToFile() {
        try {
            PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            writer.println("\n\n \t\t\t\t ===== DIVER'S DIVES ===== \n");
            String leftAlignFormatDivers = "| %-29s | %-8s | %-10s | %-11s |\n";

            for (Diver diver : diversList) {
                writer.println("|| Name: " + diver.getName());
                writer.println("|| Year: " + diver.getYear());
                writer.println("|| Agency: " + diver.getAgency().getName());
                writer.println("|| Certificate: " + diver.getLevel());
                writer.format("+-------------------------------+----------+------------+-------------+\n");
                writer.format("|    DATE                       | TIME     | MAX DEPTH  | No. DIVERS  |\n");
                writer.format("+-------------------------------+----------+------------+-------------+\n");
                for (DiveDate dejt : diver.getDivesHistory()) {
                    writer.format(leftAlignFormatDivers, dejt.getDiveDate(), dejt.getDiveTime(), dejt.getMaxDepth() + "m", dejt.getNoDivers());
                }
                writer.format("+-------------------------------+----------+------------+-------------+\n\n");
            }

            writer.println("\n\n \t\t\t\t ===== DIVING AGENCY STATISTICS ===== \n");
            String leftAlignFormatAgencies = "| %-16s | %-18s |\n";
            for (DiveAgency agency : diveAgencies) {
                int noDivers = 0;
                int noDives = 0;
                int sumDives = 0;
                double avgDepth = 0;
                writer.println("|| Agency: " + agency.getName());
                writer.format("+------------------+--------------------+\n");
                writer.format("|    No. DIVERS    |     AVG. DEPTH     |\n");
                writer.format("+------------------+--------------------+\n");
                for (Diver diver : agency.getAgencyDivers()) {
                    noDivers++;
                    for (DiveDate date : diver.getDivesHistory()) {
                        noDives++;
                        sumDives += date.getMaxDepth();
                    }
                }
                avgDepth = (double) sumDives / (double) noDives;
                writer.format(leftAlignFormatAgencies, noDivers, avgDepth);
                writer.format("+------------------+--------------------+\n\n");
            }

            writer.close();
            System.out.println("zapisano " + dates.size() + " termina u datoteku.");
        } catch (FileNotFoundException ex) {
            throw new IllegalStateException("File you tried to write to doesn't exist.");
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("File encoding not recognized.");
        }
    }

}
