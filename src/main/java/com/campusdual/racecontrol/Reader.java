package com.campusdual.racecontrol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reader {
    public static void main(String[] args) throws FileNotFoundException {
        Path filePath = Paths.get("src/main/resources/status.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
