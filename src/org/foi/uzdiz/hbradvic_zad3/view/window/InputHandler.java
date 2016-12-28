/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.Scanner;
import org.foi.uzdiz.hbradvic_zad3.view.printer.Printer;
import org.foi.uzdiz.hbradvic_zad3.view.printer.WindowPrinter;

/**
 *
 * @author hEAT
 */
public class InputHandler {

    private static final String INPUT_INDICATOR = "$> ";
    private static Scanner scanner;
    private int x;
    private int y;

    public InputHandler() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public String awaitInput(int x, int y) {
        this.x = x;
        this.y = y;
        WindowPrinter.print(INPUT_INDICATOR, x, y);
        this.x = x + INPUT_INDICATOR.length();
        Printer.position(this.x, this.y);
        return scanner.nextLine();
    }

    protected void updatePosition() {
        //int add = currentInput != null ? currentInput.length() : 0;
        //Printer.position(x + add, y);
        Printer.restorePosition();
        scanner.reset();
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

}
