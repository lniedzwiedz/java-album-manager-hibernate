package pl.edu.agh.mwo.hibernate.filealbummanager.ui.console;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader {

    private final BufferedReader reader;

    public ConsoleReader(BufferedReader reader) {
        this.reader = reader;
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public Integer readInteger() throws IOException {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}