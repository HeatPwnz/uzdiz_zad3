/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.List;

/**
 *
 * @author hEAT
 */
public abstract class Window {
    
    private WindowProps props;

    public Window(WindowProps props) {
        this.props = props;
    }

    public WindowProps getProps() {
        return props;
    }

    public void setProps(WindowProps props) {
        this.props = props;
    }
    
    
    protected abstract void display();
}
