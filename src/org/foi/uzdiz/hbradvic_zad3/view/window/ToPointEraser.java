/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import org.foi.uzdiz.hbradvic_zad3.view.observer.EraserObserver;
import org.foi.uzdiz.hbradvic_zad3.view.printer.Printer;

/**
 *
 * @author hEAT
 */
public class ToPointEraser extends Eraser {

    public ToPointEraser() {
        super();
    }

    public ToPointEraser(EraserObserver observer) {
        super(observer);
    }

    @Override
    protected void eraseContent(int startX, int endX, int startY, int endY) {
        Printer.clearFromStart(endX, startY, endY);
    }

}
