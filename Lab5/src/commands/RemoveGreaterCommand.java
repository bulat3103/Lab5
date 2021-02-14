package commands;

import data.SpaceMarine;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfParametersException;
import managers.CollectionManager;
import managers.SpaceMarineBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class RemoveGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private SpaceMarineBuilder builder;

    public RemoveGreaterCommand(CollectionManager collectionManager, SpaceMarineBuilder builder) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.builder = builder;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            SpaceMarine marineToCompare = new SpaceMarine(
                    collectionManager.generateId(),
                    builder.askName(),
                    builder.askCoordinates(),
                    LocalDateTime.now(),
                    builder.askHealth(),
                    builder.askHeartCount(),
                    builder.askAchievements(),
                    builder.askWeapon(),
                    builder.askChapter()
            );
            List<Integer> keys = collectionManager.getGreater(marineToCompare);
            for (Integer i : keys) {
                collectionManager.removeFromCollection(i);
            }
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("У этой команды нет параметров!");
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
