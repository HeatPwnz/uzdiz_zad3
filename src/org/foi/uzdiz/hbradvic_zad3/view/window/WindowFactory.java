/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.view.observer.EraserObserver;

/**
 *
 * @author Hrvoje
 */
public class WindowFactory {
    
    private EraserObserver observer;

    List<Window> returnWindows(WindowProps props, EraserObserver observer) {
        this.observer = observer;
        
        
        
        
        return null;
    }
    
    protected Window createInputWindow(WindowProps props){
        return null;
    }
    
}
