/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.hbradvic_zad3.view.printer.WindowPrinter;

/**
 *
 * @author Hrvoje
 */
public abstract class SubWindow extends Window {

    private final int x;
    private int y;
    private int noX;
    private int noY;

    protected final Eraser eraser;

    public SubWindow(WindowProps props, Eraser eraser) {
        super(props);
        this.x = props.getStartX();
        this.y = props.getStartY();
        this.noX = props.getNoRows();
        this.noY = props.getNoCols();
        this.eraser = eraser;
    }

    protected void print(String cmd) {
        String[] cmdArray = cmd.split("\\n");
        for (String s : cmdArray) {
            printLine(s);
        }
    }

    protected void printLine(String string) {
        String[] formatted = WindowPrinter.formatText(string, x, y);
        for (String s : formatted) {
            try {
                if ((y - noY) >= noX) {
                    clear();
                }
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(SubWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            WindowPrinter.print(s, x, y);
            y++;
        }
    }

    protected void clear() {
        int startX = props.getStartX();
        int endX = startX + props.getNoCols();
        int startY = props.getStartY();
        int endY = props.getNoRows() + startY;
        eraser.erase(startX, endX, startY, endY);
        resetY();
    }

    protected void resetY() {
        this.y = props.getStartY();
    }

}
