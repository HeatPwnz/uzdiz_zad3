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
        value = value.trim().toLowerCase();
        this.params = value.split(" ");
    }
    
}
