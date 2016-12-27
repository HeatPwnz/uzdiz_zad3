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
public class DiverTriplet {
    
    protected List<Diver> diverTriplet;
    protected int visitedDepth;

    public DiverTriplet(Diver diver1, Diver diver2, Diver diver3) {
        this.diverTriplet = new ArrayList<>();
        diverTriplet.add(diver1);
        diverTriplet.add(diver2);
        diverTriplet.add(diver3);
    }

    public List<Diver> getDiverTriplet() {
        return diverTriplet;
    }

    public void setDiverTriplet(List<Diver> diverTriplet) {
        this.diverTriplet = diverTriplet;
    }

    public int getVisitedDepth() {
        return visitedDepth;
    }

    public void setVisitedDepth(int visitedDepth) {
        this.visitedDepth = visitedDepth;
    }
    
}
