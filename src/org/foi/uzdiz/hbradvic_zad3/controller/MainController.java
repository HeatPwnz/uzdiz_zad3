/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.hbradvic_zad3.controller;

import java.util.List;
import org.foi.uzdiz.hbradvic_zad3.controller.commands.DisplayDivers;
import org.foi.uzdiz.hbradvic_zad3.controller.commands.ExitProgram;
import org.foi.uzdiz.hbradvic_zad3.controller.commands.ReturnStateFromMemento;
import org.foi.uzdiz.hbradvic_zad3.model.pojo.Diver;
import org.foi.uzdiz.hbradvic_zad3.model.DiversClub;
import org.foi.uzdiz.hbradvic_zad3.view.InputView;

/**
 *
 * @author hEAT
 */
public class MainController {
    
    private DiversClub diversClub;
    private InputView inputView;
    private final CommandHandler commandHandler;
    private final InputParser inputParser;

    public MainController() {
        this.inputParser = new InputParser();
        this.commandHandler = new CommandHandler();
    }
    
    public void doSomething(){
        
        commandHandler.add("P", new DisplayDivers(diversClub));
        commandHandler.add("V", new ReturnStateFromMemento(diversClub));
        commandHandler.add("Q", new ExitProgram(diversClub));
        //commandHandler.add("\\w", new AddEquipementToDiver(diversClub));
        
        while(true){
            String input = (String) inputView.getUserInput();
            inputParser.setValue(input);
            if(inputParser.checkKey()){
                execute(input);
            }
        }
    }
    
    public void execute(String key){
        commandHandler.doIt(key);
    }

    public void setDiversClub(DiversClub diversClub) {
        this.diversClub = diversClub;
    }

    public void setInputView(InputView inputView) {
        this.inputView = inputView;
    }
    
}
