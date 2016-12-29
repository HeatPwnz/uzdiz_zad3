/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.controller;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hrvoje
 */
public class CommandHandler {
    
    private final Map<String, Command> cmdList = new HashMap<>();
    
    public void add(String key, Command value){
        cmdList.put(key, value);
    }
    
    public void setParameter(String key, String parameter){
        cmdList.get(key).setParameter(parameter);
    }
    
    public Object doIt(String key){
        Object result = cmdList.get(key).executeCommand();
        return result;
    }
}
