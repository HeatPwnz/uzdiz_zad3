/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.controller.commands;

import org.foi.uzdiz.hbradvic_zad3.controller.Command;
import org.foi.uzdiz.hbradvic_zad3.controller.ControllerInterface;

/**
 *
 * @author Hrvoje
 */
public class ExitProgram extends Command{

    public ExitProgram(ControllerInterface controllerInterface) {
        super(controllerInterface);
    }

    @Override
    public Object executeCommand() {
        return controllerInterface.ExitProgram();
    }

    @Override
    public void setParameter(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
