import commands.*;
import managers.*;

import java.util.Scanner;

/**
 * Main application class. Creates all instances and runs the program.
 * @author Khafizov Bulat
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла, в котором хранится коллекция: > ");
        final String fileName = scanner.nextLine().trim();
        FileManager fileManager = new FileManager(fileName);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        SpaceMarineBuilder builder = new SpaceMarineBuilder(scanner);
        CommandManager commandManager = new CommandManager(new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager, builder),
                new UpdateCommand(collectionManager, builder),
                new RemoveKeyCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new RemoveGreaterCommand(collectionManager, builder),
                new HistoryCommand(),
                new RemoveLowerKeyCommand(collectionManager),
                new RemoveAllByWeaponTypeCommand(collectionManager),
                new SumOfHealthCommand(collectionManager),
                new AverageOfHeartCountCommand(collectionManager));
        Console console = new Console(commandManager, scanner, builder);
        console.interactiveMode();
    }
}
