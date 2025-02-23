package org.javastreams;

import java.io.*;
import java.nio.file.*;

public class OutputWriter {
    public static void saveOutput(String filename, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}