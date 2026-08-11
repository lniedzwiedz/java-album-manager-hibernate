package pl.edu.agh.mwo.hibernate.filealbummanager.ui.console;

import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.io.IOException;

public class ConsoleMenu {

    private final ConsoleReader reader;
    private final ConsolePrinter printer;

    public ConsoleMenu(ConsoleReader reader, ConsolePrinter printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public MenuOption readMenuOption() throws IOException {
        printer.printMenu();
        Integer input = reader.readInteger();
        if (input == null)
            return null;
        return MenuOption.fromInt(input);
    }
}