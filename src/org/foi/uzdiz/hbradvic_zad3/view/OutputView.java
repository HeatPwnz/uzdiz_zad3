/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view;

import org.foi.uzdiz.hbradvic_zad3.view.observer.DisplayObserver;

/**
 *
 * @author hEAT
 */
public interface OutputView<T> extends DisplayObserver{
    
   void output(T data);
    
}
