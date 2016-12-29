/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.controller;

/**
 *
 * @author hEAT
 */
public class InputParser {
    
    private String[] params;

    public void setValue(String value) {
        if (value == null) {
            value = "";
        }
        value = value.trim().toUpperCase();
        this.params = value.split(" ");
    }
    
    public boolean checkKey(){
        return params[0].equals("P") || params[0].equals("V") || params[0].equals("N") || params[0].equals("Q");
    }
    
}
