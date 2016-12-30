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
public class Memento {
    
    private List<Diver> divers;

    public Memento(List<Diver> divers) {
        this.divers = divers;
    }

    public List<Diver> getState() {
        return divers;
    }
}
