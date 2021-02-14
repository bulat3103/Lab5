package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfParametersException;
import managers.CollectionManager;

public class AverageOfHeartCountCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public AverageOfHeartCountCommand(CollectionManager collectionManager) {
        super("average_of_heart_count", "вывести среднее значение поля heartCount для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
            double average_of_heart_count = collectionManager.averageOfHealthCount();
            if (average_of_heart_count == 0) throw new EmptyCollectionException();
            System.out.println("Среднее значение поля heartCount всех космических десантов: " + average_of_heart_count);
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("У этой команды нет параметров!");
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
