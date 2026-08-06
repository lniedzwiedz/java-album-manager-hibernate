package pl.edu.agh.mwo.hibernate.filealbummanager.config;

import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationResources implements AutoCloseable {

    private final Session session;
    private final BufferedReader reader;

    public ApplicationResources() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Session getSession() {
        return session;
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

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}