package commands;

import data.SpaceMarine;
import exceptions.WrongAmountOfParametersException;
import managers.CollectionManager;
import managers.SpaceMarineBuilder;

import java.time.LocalDateTime;

public class InsertCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private SpaceMarineBuilder builder;

    public InsertCommand(CollectionManager collectionManager, SpaceMarineBuilder builder) {
        super("insert null {element}", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
        this.builder = builder;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfParametersException();
            int key = Integer.parseInt(argument);
            collectionManager.addToCollection(key,new SpaceMarine(
                    collectionManager.generateId(),
                    builder.askName(),
                    builder.askCoordinates(),
                    LocalDateTime.now(),
                    builder.askHealth(),
                    builder.askHeartCount(),
                    builder.askAchievements(),
                    builder.askWeapon(),
                    builder.askChapter()

            ));
            System.out.println("Успешно добавлено в коллекцию!");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки");
        }
        return false;
    }
}
