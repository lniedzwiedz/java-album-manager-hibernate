package pl.edu.agh.mwo.hibernate.filealbummanager;

import pl.edu.agh.mwo.hibernate.filealbummanager.application.ApplicationRunner;
import pl.edu.agh.mwo.hibernate.filealbummanager.config.ApplicationResources;
import pl.edu.agh.mwo.hibernate.filealbummanager.config.ApplicationConfig;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try (ApplicationResources resources = new ApplicationResources()) {

            ApplicationRunner applicationRunner =
                    new ApplicationConfig(resources.getSession())
                            .createApplication();

            applicationRunner.run();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}