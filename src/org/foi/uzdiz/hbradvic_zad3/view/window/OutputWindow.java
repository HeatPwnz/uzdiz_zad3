/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import org.foi.uzdiz.hbradvic_zad3.view.OutputView;
import org.foi.uzdiz.hbradvic_zad3.view.observer.OutputObserver;
import org.foi.uzdiz.hbradvic_zad3.view.printer.Printer;

/**
 *
 * @author hEAT
 */
public class OutputWindow extends SubWindow implements OutputView<Object> {

    private OutputObserver observer;

    public OutputWindow(WindowProps options, Eraser eraser) {
        super(options, eraser);
    }

    @Override
    public void update(Object data) {
        output(data);
    }

    /* @Override
    public void show() {
        //clear();
    }*/
    @Override
    public void output(Object data) {
        Printer.savePosition();
        print(data.toString());
        if (observer != null) {
            observer.update();
        }
    }

    public void setOutputObserver(OutputObserver observer) {
        this.observer = observer;
    }

    @Override
    protected void display() {
        //clear();
    }
}
