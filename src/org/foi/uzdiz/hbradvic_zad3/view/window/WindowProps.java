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

    public WindowProps(int noRows, int noCols, int noRowsTank) {
        this.noRows = noRows;
        this.noCols = noCols;
        this.noRowsTank = noRowsTank;
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
    
    
}
