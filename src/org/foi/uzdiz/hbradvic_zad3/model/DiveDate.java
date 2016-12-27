/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hrvoje
 */
public class DiveDate {
    
    private Date diveDate;
    private LocalTime diveTime;
    private int maxDepth;
    private int noDivers;
    private List<Object> divers = new ArrayList<>();

    public DiveDate(Date diveDate, LocalTime diveTime, int maxDepth, int noDivers) {
        this.diveDate = diveDate;
        this.diveTime = diveTime;
        this.maxDepth = maxDepth;
        this.noDivers = noDivers;
    }

    public Date getDiveDate() {
        return diveDate;
    }

    public void setDiveDate(Date diveDate) {
        this.diveDate = diveDate;
    }

    public LocalTime getDiveTime() {
        return diveTime;
    }

    public void setDiveTime(LocalTime diveTime) {
        this.diveTime = diveTime;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getNoDivers() {
        return noDivers;
    }

    public void setNoDivers(int noDivers) {
        this.noDivers = noDivers;
    }

    public List<Object> getDivers() {
        return divers;
    }

    public void setDivers(List<Object> divers) {
        this.divers = divers;
    }

}
