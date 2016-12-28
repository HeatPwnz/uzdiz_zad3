/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.printer;

/**
 *
 * @author hEAT
 */
public class Printer {
    
    private static final String ANSI_ESC = "\033[";

    public static void position(int x, int y) {
        System.out.print(ANSI_ESC + x + ";" + y + "f");
    }
    
    public static void clear() {
        System.out.print(ANSI_ESC + "2J");
    }
    
    public static void restorePosition() {
        System.out.print("\0338");
    }

    public static void savePosition() {
        System.out.print("\0337");
    }
    
     public static void clearFromPoint(int x, int y) {
        position(x, y);
        System.out.print(ANSI_ESC + "0K");
    }

    public static void clearFromStart(int x, int y) {
        position(x, y);
        System.out.print(ANSI_ESC + "1K");
    }

    public static void clearFromStart(int x, int startY, int endY) {
        clear(x, startY, endY, "1K");
    }

    public static void clearFromPoint(int x, int startY, int endY) {
        clear(x, startY, endY, "0K");
    }
    
     public static void clear(int x, int startY, int endY, String mode) {
        for (int y = startY; y <= endY; y++) {
            position(x, y);
            System.out.print(ANSI_ESC + mode);
        }
    }
}
