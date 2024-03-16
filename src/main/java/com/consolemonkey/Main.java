package com.consolemonkey;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
    public static void main(String[] args) {
        try{
            Terminal terminal = TerminalBuilder.builder()
            .jna(true)
            .system(true)
            .build();
            
            terminal.enterRawMode();
    var reader = terminal .reader();

    int read = reader.read();

    System.out.println((char)read);

    (new Orchestrator()).begin();

    reader.close();
    terminal.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
                
        System.out.println("Hello world!");
    }
}