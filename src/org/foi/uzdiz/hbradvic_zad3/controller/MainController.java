/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.controller;

import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.model.Diver;
import org.foi.uzdiz.hbradvic_zad3.model.DiversClub;
import org.foi.uzdiz.hbradvic_zad3.view.InputView;

/**
 *
 * @author hEAT
 */
public class MainController {
    
    private DiversClub diversClub;
    private InputView inputView;
    private InputParser inputParser;
    
    public void doSomething(List<Diver> divers){
        
        while(true){
            String input = (String) inputView.getUserInput();
            
        }
        
    }
    
}
