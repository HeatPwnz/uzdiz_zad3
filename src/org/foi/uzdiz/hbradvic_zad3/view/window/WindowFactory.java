/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.view.window;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.view.observer.EraserObserver;

/**
 *
 * @author Hrvoje
 */
public class WindowFactory {

    private EraserObserver observer;

    List<Window> returnWindows(WindowProps props, EraserObserver observer) {
        this.observer = observer;
        InputWindow inputWindow = createInputWindow(props);
        OutputWindow outputWindow = createOutputWindow(props);
        outputWindow.setOutputObserver(inputWindow);
        List<Window> windows = new ArrayList<>();
        windows.add(outputWindow);
        windows.add(inputWindow);
        return windows;
    }

    private OutputWindow createOutputWindow(WindowProps props) {
        int width = props.getNoCols();
        int height = props.getNoRows() - 1;
        int x = x(props);
        int y = y(props, true);
        WindowProps o = new WindowProps(height, width, 0, y, x); // todo: Izmijeni nulu
        return new OutputWindow(o, new ToPointEraser(observer));
    }

    private InputWindow createInputWindow(WindowProps props) {
        int x = x(props);
        int y = y(props, false);
        int height = 1;
        int width = props.getNoCols();
        WindowProps wp = new WindowProps(height, width, 0, y, x); // todo: Izmijeni nulua
        return new InputWindow(wp);
    }

    private static int x(WindowProps props) {
        int spacing = 1;
        return spacing;
    }

    private static int y(WindowProps props, boolean isTop) {
        int spacing = !isTop
                ? props.getNoRows() -1
                : 1;
        return props.getStartY() + spacing;
    }

}
