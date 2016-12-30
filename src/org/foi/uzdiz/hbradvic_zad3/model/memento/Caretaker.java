/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hrvoje
 */
public class Caretaker {
    
    public List<Memento> mementoList = new ArrayList<>();
    
    public void add(Memento memento){
        mementoList.add(memento);
    }
    
    public Memento get(int index){
        return mementoList.get(index);
    }
    
}
