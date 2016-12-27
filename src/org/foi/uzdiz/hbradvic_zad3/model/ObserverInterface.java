/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

import java.util.List;

/**
 *
 * @author hEAT
 */
public interface ObserverInterface {
    public void update(DiveDate diveDate, List<Diver> diversList);
}
