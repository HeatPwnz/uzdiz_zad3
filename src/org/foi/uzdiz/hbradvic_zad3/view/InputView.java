/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view;

import org.foi.uzdiz.hbradvic_zad3.view.observer.InputObserver;
import org.foi.uzdiz.hbradvic_zad3.view.window.InputHandler;

/**
 *
 * @author hEAT
 */
public interface InputView<T> extends InputObserver{
    
    T getUserInput();
    
    void update(InputHandler handler);
}
