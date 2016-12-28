/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import org.foi.uzdiz.hbradvic_zad3.view.observer.EraserObserver;

/**
 *
 * @author Hrvoje
 */
public abstract class Eraser {
    
    private EraserObserver observer;
    
    public Eraser() {

    }

    public Eraser(EraserObserver observer) {
        this.observer = observer;
    }
    
    protected abstract void eraseContent(int startX, int endX, int startY, int endY);

    public void erase(int startX, int endX, int startY, int endY) {
        eraseContent(startX, endX, startY, endY);
        if (observer != null) {
            observer.update();
        }
    }
    
}
