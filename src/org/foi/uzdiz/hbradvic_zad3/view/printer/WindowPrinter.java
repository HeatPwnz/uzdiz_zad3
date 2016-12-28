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
    
    private static final String VERTICAL_LINE = "|";
    private static final String HORIZONTAL_LINE = "_";
    
    
    public static void print(String text, int y, int noCols){
        Printer.position(noCols, y);
        System.out.println(text);
    }
    
       public static void printVerticalLine(int x, int startY, int endY) {
        for (int i = startY; i <= endY; i++) {
            print(VERTICAL_LINE, x, i);
        }
    }

    public static void printHorizontalLine(int y, int startX, int endX) {
        for (int i = startX; i <= endX; i++) {
            print(HORIZONTAL_LINE, i, y);
        }
    }
    
    public static String clearText(String text) {
        return text.replaceAll("\t", "    ");
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
