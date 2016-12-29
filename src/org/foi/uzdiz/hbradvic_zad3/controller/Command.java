/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.controller;

/**
 *
 * @author Hrvoje
 */
public abstract class Command {
    
    protected ControllerInterface controllerInterface;

    public Command(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }
    
    
    
    public abstract Object executeCommand();
    public abstract void setParameter(String parameter);
    
}
