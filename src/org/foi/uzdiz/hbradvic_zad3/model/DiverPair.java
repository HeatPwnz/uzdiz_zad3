/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hrvoje
 */
public class DiverPair {
    
    protected List<Diver> diverPair;
    protected int visitedDepth;

    public DiverPair(Diver diver1, Diver diver2) {
        this.diverPair = new ArrayList<>();
        diverPair.add(diver1);
        diverPair.add(diver2);
    }

    public List<Diver> getDiverPair() {
        return diverPair;
    }

    public void setDiverPair(List<Diver> diverPair) {
        this.diverPair = diverPair;
    }

    public int getVisitedDepth() {
        return visitedDepth;
    }

    public void setVisitedDepth(int visitedDepth) {
        this.visitedDepth = visitedDepth;
    }
            
}
