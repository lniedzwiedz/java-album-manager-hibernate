package pl.edu.agh.mwo.hibernate.filealbummanager.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationResources implements AutoCloseable {

    private final BufferedReader reader;

    public ApplicationResources() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot close reader.", e);
        }
    }
}