package commands;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import data.Weapon;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfParametersException;
import managers.CollectionManager;
import managers.SpaceMarineBuilder;

import java.time.LocalDateTime;

public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private SpaceMarineBuilder builder;

    public UpdateCommand(CollectionManager collectionManager, SpaceMarineBuilder builder) {
        super("update id {element}", "обновить значение элемента коллекции по id");
        this.collectionManager = collectionManager;
        this.builder = builder;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfParametersException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            int id = Integer.parseInt(argument);
            int key = collectionManager.getKeyById(id);
            SpaceMarine oldMarine = collectionManager.getFromCollection(key);
            String name = oldMarine.getName();
            Coordinates coordinates = oldMarine.getCoordinates();
            LocalDateTime creationDate = oldMarine.getCreationDate();
            int health = oldMarine.getHealth();
            Integer heartCount = oldMarine.getHeartCount();
            String achievements = oldMarine.getAchievements();
            Weapon weapon = oldMarine.getWeaponType();
            Chapter chapter = oldMarine.getChapter();
            collectionManager.removeFromCollection(key);
            if (builder.askAboutChangingField("Хотите изменить имя космического десантника?")) name = builder.askName();
            if (builder.askAboutChangingField("Хотите изменить координаты?")) coordinates = builder.askCoordinates();
            if (builder.askAboutChangingField("Хотите изменить здоровье?")) health = builder.askHealth();
            if (builder.askAboutChangingField("Хотите изменить кол-во сердец?")) heartCount = builder.askHeartCount();
            if (builder.askAboutChangingField("Хотите изменить достижение?")) achievements = builder.askAchievements();
            if (builder.askAboutChangingField("Хотите изменить оружие?")) weapon = builder.askWeapon();
            if (builder.askAboutChangingField("Хотите изменить орден?")) chapter = builder.askChapter();
            collectionManager.addToCollection(key, new SpaceMarine(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    health,
                    heartCount,
                    achievements,
                    weapon,
                    chapter
            ));
            System.out.println("Успешно изменено!");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            System.out.println("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки");
        } catch (EmptyCollectionException exception) {
            System.out.println("Коллекция пуста!");
        }
        return false;
    }
}
