/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import org.foi.uzdiz.hbradvic_zad3.view.InputView;
import org.foi.uzdiz.hbradvic_zad3.view.observer.OutputObserver;

/**
 *
 * @author hEAT
 */
public class InputWindow extends SubWindow implements InputView<String>, OutputObserver {

    private InputHandler inputHandler = new InputHandler();

    public InputWindow(WindowProps options) {
        this(options, new FromPointEraser());
    }

    public InputWindow(WindowProps options, Eraser eraser) {
        super(options, eraser);
    }

    @Override
    public void display() {
        clear();
    }

    @Override
    public String getUserInput() {
        int x = props.getStartX();
        int y = props.getStartY();
        eraser.erase(x, x, y, y); // Clear current row before requesting
        return inputHandler.awaitInput(x, y);
    }

    @Override
    public void update(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void update() {
        inputHandler.updatePosition();
    }

}
