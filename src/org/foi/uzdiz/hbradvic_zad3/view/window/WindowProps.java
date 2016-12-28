/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

/**
 *
 * @author hEAT
 */
public class WindowProps {
    
    private final int noRows;
    private final int noCols;
    private final int noRowsTank;
    private final int startX;
    private final int startY;

    public WindowProps(int noRows, int noCols, int noRowsTank, int startX, int startY) {
        this.noRows = noRows % 2 == 0 ? noRows : noRows - 1;
        this.noCols = noCols % 2 == 0 ? noCols : noCols - 1;
        this.noRowsTank = noRowsTank;
        this.startX = startX;
        this.startY = startY;
    }

    public int getNoRows() {
        return noRows;
    }

    public int getNoCols() {
        return noCols;
    }

    public int getNoRowsTank() {
        return noRowsTank;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
    
    
    
}
