/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.hbradvic_zad3.view.InputView;
import org.foi.uzdiz.hbradvic_zad3.view.OutputView;
import org.foi.uzdiz.hbradvic_zad3.view.observer.EraserObserver;
import org.foi.uzdiz.hbradvic_zad3.view.printer.Printer;
import org.foi.uzdiz.hbradvic_zad3.view.printer.WindowPrinter;

/**
 *
 * @author hEAT
 */
public class ParentWindow extends Window {

    private List<Window> subWindows;
    private WindowFactory factory;
    private EraserObserver observer;

    public ParentWindow(WindowProps props) {
        super(props);
        this.factory = new WindowFactory();
        this.observer = (() -> {
            showBorders();
        });
        this.subWindows = factory.returnWindows(props, observer);
        //showBorders();
    }

    @Override
    public void display() {
        Printer.clear();
        for (Window window : subWindows) {
            window.display();
        }
    }

    public InputView getInputView() {
        for (Window window : subWindows) {
            if (window instanceof InputView) {
                return (InputView) window;
            }
        }
        return null;
    }

    public OutputView getOutputView() {
        for (Window window : subWindows) {
            if (window instanceof OutputView) {
                return (OutputView) window;
            }
        }
        return null;
    }

    private void showBorders() {

        int x1 = props.getStartX();
        int y1 = props.getStartY();
        int x2 = props.getNoCols();
        int y2 = props.getNoRows() - 1;
        
        WindowPrinter.printVerticalLine(x1, y1+1, props.getNoRows()-1);
        WindowPrinter.printVerticalLine(x2, y1+1, props.getNoRows()-1);
        WindowPrinter.printHorizontalLine(y1, x1, props.getNoCols());
        WindowPrinter.printHorizontalLine(y2, x1, props.getNoCols());
        /*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ParentWindow.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

}
