/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.controller.MainController;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.DiveAgency;
import org.foi.uzdiz.hbradvic_zad3.model.DiversClub;
import org.foi.uzdiz.hbradvic_zad3.view.InputView;
import org.foi.uzdiz.hbradvic_zad3.view.OutputView;
import org.foi.uzdiz.hbradvic_zad3.view.window.ParentWindow;
import org.foi.uzdiz.hbradvic_zad3.view.window.WindowProps;

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
            WindowProps windowProps = new WindowProps(rows, cols, rowsTank, 1, 1);

            String diversDat = args[3];
            String specDat = args[4];
            String equipDat = args[5];

            int depth = Integer.parseInt(args[6]);
            int temp = Integer.parseInt(args[7]);
            int night = Integer.parseInt(args[8]);
            int recording = Integer.parseInt(args[9]);

            List<DiveAgency> diveAgencies = fillDivingAgencies();

            DiversClub diversClub = new DiversClub(diversDat, specDat, equipDat, diveAgencies, depth, temp, night, recording);

            ParentWindow window = new ParentWindow(windowProps);
            window.display();
            
            InputView inputView = window.getInputView();
            OutputView outputView = window.getOutputView();
            
            diversClub.addInputDisplayObservers(inputView);
            diversClub.addOutputDisplayObserver(outputView);
            diversClub.doYourWork();
            
            MainController mainController = new MainController();
            mainController.setDiversClub(diversClub);
            mainController.setInputView(inputView);
            mainController.doSomething();
            
        } else {
            System.out.println("Params you entered are wrong, fixerinho needed.");
        }
    }

    private static boolean checkInputParams(String[] args) {
        if (args == null || args.length != 10) {
            return false;
        }
        int noRows = Integer.parseInt(args[0]);
        if (noRows < 24 || noRows > 80) {
            return false;
        }
        int noCols = Integer.parseInt(args[1]);
        if (noCols < 80 || noCols > 160) {
            return false;
        }
        int noRowsTank = Integer.parseInt(args[2]);
        if (noRowsTank < noRows || noRowsTank > 400) {
            return false;
        }
        int depth = Integer.parseInt(args[6]);
        if (depth < 5 || depth > 40) {
            return false;
        }
        int temp = Integer.parseInt(args[7]);
        if (temp < 0 || temp > 35) {
            return false;
        }
        int night = Integer.parseInt(args[8]);
        if (night != 1 && night != 0) {
            return false;
        }
        int recording = Integer.parseInt(args[9]);
        if (recording < 0 || recording > 999) {
            return false;
        }
        return true;
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
