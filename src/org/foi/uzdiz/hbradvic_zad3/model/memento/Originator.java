/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model.memento;

import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.Diver;

/**
 *
 * @author Hrvoje
 */
public class Originator {
    
    private List<Diver> divers;

    public List<Diver> getDivers() {
        return divers;
    }

    public void setDivers(List<Diver> divers) {
        this.divers = divers;
    }
    
    public Memento saveStateToMemento(){
        return new Memento(divers);
    }
    
    public void getStateFromMemento(Memento memento){
        divers = memento.getState();
    }
    
}
