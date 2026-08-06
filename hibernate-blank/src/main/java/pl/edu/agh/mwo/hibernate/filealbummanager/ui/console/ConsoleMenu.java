package pl.edu.agh.mwo.hibernate.filealbummanager.ui.console;

import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.io.IOException;

public class ConsoleMenu {

    private final ConsolePrinter printer;
    private final ConsoleReader reader;

    public ConsoleMenu(ConsolePrinter printer, ConsoleReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public MenuOption readMenuOption() throws IOException {
        printer.printMenu();
        Integer input = reader.readInteger();
        if (input == null)
            return null;
        return MenuOption.fromInt(input);
    }
}