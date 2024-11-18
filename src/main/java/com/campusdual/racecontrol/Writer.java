package com.campusdual.racecontrol;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Writer {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/resources/status.txt");
        try(PrintWriter pw = new PrintWriter(new FileWriter(filePath.toFile()))){
            pw.println("Program status");
            pw.println();


        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
