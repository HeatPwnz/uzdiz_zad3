/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.view.InputView;
import org.foi.uzdiz.hbradvic_zad3.view.OutputView;
import org.foi.uzdiz.hbradvic_zad3.view.printer.Printer;

/**
 *
 * @author hEAT
 */
public class ParentWindow extends Window{
    
    private List<Window> subWindows;
    
    public ParentWindow(WindowProps props) {
        super(props);
    }

    @Override
    protected void display() {
        Printer.clear();
        for(Window window : subWindows){
            window.display();
        }
    }
    
    public InputView getInputView(){
        for(Window window : subWindows){
            if(window instanceof InputView){
                return (InputView) window;
            }
        }
        return null;
    } 
    
    public OutputView getOutputView(){
        for(Window window : subWindows){
            if(window instanceof OutputView){
                return (OutputView) window;
            }
        }
        return null;
    } 
    
}
