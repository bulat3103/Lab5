package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfParametersException;
import managers.CollectionManager;

import java.util.List;

public class RemoveLowerKeyCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        super("remove_lower_key null", "удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfParametersException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            int key = Integer.parseInt(argument);
            List<Integer> keys = collectionManager.getLowerKey(key);
            for (Integer i : keys) {
                collectionManager.removeFromCollection(i);
            }
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки");
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
