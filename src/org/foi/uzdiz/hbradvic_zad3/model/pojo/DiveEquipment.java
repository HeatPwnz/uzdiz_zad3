/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.model.pojo;

import java.util.List;

/**
 *
 * @author hEAT
 */
public class DiveEquipment {
    
    private double level;
    private String Name;
    private int depth;
    private List<String> requirements;
    private List<DiveEquipment> subEquipement;

    public DiveEquipment(double level, String Name) {
        this.level = level;
        this.Name = Name;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<DiveEquipment> getSubEquipement() {
        return subEquipement;
    }

    public void setSubEquipement(List<DiveEquipment> subEquipement) {
        this.subEquipement = subEquipement;
    }
}
