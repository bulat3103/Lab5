package commands;

import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfParametersException;
import managers.CollectionManager;

public class SumOfHealthCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SumOfHealthCommand(CollectionManager collectionManager) {
        super("sum_of_health", "вывести сумму значений поля health для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
            double sum_of_health = collectionManager.getSumOfHealth();
            if (sum_of_health == 0) throw new EmptyCollectionException();
            System.out.println("Сумма здоровья всех космических десантов: " + sum_of_health);
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("У этой команды нет параметров!");
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
