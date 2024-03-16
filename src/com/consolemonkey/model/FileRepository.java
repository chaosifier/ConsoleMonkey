package com.consolemonkey.model;

import java.io.*;
import java.util.logging.Logger;

public class FileRepository {
    private static final Logger LOG = Logger.getLogger(FileRepository.class.getName());
    public FileRepository(){}
    public void write(String fileName, String line){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(line);
        } catch(IOException e) {
            LOG.warning(STR."IOException attempting to write a file: \{e.getMessage()}");
        }
    }

    public void read(String fileName, String line){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String s =reader.readLine();
            System.out.println(s);
        } catch(IOException e) {
            LOG.warning(STR."IOException attempting to write a file: \{e.getMessage()}");
        }
    }
}
