package com.campusdual.racecontrol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Delete {
    public static void main(String[] args) {
        Path filePath = Paths.get("src/main/resources/status.txt");
        try {
            Files.deleteIfExists(filePath);
            System.out.println("File deleted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
