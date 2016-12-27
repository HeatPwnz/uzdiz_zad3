/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

/**
 *
 * @author Hrvoje
 */
public class DiverSpecs {
    public String diver;
    public String spec;

    public DiverSpecs(String diver, String spec) {
        this.diver = diver;
        this.spec = spec;
    }

    public String getDiver() {
        return diver;
    }

    public void setDiver(String diver) {
        this.diver = diver;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
    
    
}
