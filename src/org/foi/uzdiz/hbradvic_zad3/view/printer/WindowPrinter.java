/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.printer;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hrvoje
 */
public class WindowPrinter {
    
    private static final String LINE = "_";
    
    public static void printWindowSplitter(int noRows, int noCols){
        for(int i=0; i<=noCols; i++){
            print(LINE, i, noRows-1);
        }
    }
    
    public static void print(String text, int y, int noRows){
        Printer.position(noRows, y);
        System.out.println(text);
    }
    
    public static String clearText(String text) {
        return text.replaceAll("\\t", "    ");
    }
    
    public static String[] formatText(String text, int maxWidth, int maxHeight) {
        text = clearText(text);
        String[] sArr = text.split("(?<=\\G.{" + maxWidth + "})");
        int size = sArr.length % maxHeight;
        List<String> list = Arrays
                .asList(sArr)
                .subList(sArr.length - size, sArr.length);
        return list.toArray(new String[size]);
    }
    
    public static int print(String[] text, int x, int y){
        for(String s: text){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                Logger.getLogger(WindowPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
            print(s, x, y);
            y++;
        }
        return y;
    }
    
}
