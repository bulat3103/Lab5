package managers;

import data.SpaceMarine;
import data.Weapon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private TreeMap<Integer, SpaceMarine> marines = new TreeMap<>();
    private LocalDateTime lastInitTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    /**
     * @return The collecton itself.
     */
    public TreeMap<Integer, SpaceMarine> getCollection() {
        return marines;
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        marines = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * Adds a new marine to collection.
     * @param marine A marine to add.
     */
    public void addToCollection(int key, SpaceMarine marine) {
        marines.put(key, marine);
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */
    public int generateId() {
        if (marines.isEmpty()) return 1;
        int lastId = 0;
        for (SpaceMarine marine : marines.values()) {
            lastId = Math.max(lastId, marine.getId());
        }
        return lastId + 1;
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return marines.getClass().getName();
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        marines.clear();
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollection(marines);
    }

    /**
     * @param marine The marine used to take the all marines' keys.
     * @return A list of marines' keys.
     */
    public List<Integer> getGreater(SpaceMarine marine) {
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getValue().compareTo(marine) > 0) list.add(e.getKey());
        }
        return list;
    }

    /**
     * @param key The key used to take the all marines' keys, which are smaller than key in parameters.
     * @return A list of marines' keys.
     */
    public List<Integer> getLowerKey(int key) {
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getKey() < key) list.add(e.getKey());
        }
        return list;
    }

    /**
     * @param weapon The weapon used to take the marines' keys.
     * @return A list of marines' keys.
     */
    public List<Integer> getKeyByWeaponType(Weapon weapon) {
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getValue().getWeaponType().equals(weapon)) list.add(e.getKey());
        }
        return list;
    }

    /**
     * Removes a marine from collection.
     * @param key A key of marine.
     */
    public void removeFromCollection(int key) {
        marines.remove(key);
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return marines.size();
    }

    /**
     * @param key The key used to take the marine.
     * @return A marine's key.
     */
    public SpaceMarine getFromCollection(int key) {
        return marines.get(key);
    }

    /**
     * @param id ID, by which the key is found.
     * @return A marine's key.
     */
    public Integer getKeyById(int id) {
        for (Map.Entry<Integer, SpaceMarine> e : marines.entrySet()) {
            if (e.getValue().getId() == id) return e.getKey();
        }
        return null;
    }

    /**
     * @return Sum of all marines' health or 0 if collection is empty.
     */
    public double getSumOfHealth() {
        double sum_of_health = 0;
        for (SpaceMarine e : marines.values()) {
            sum_of_health += e.getHealth();
        }
        return sum_of_health;
    }

    /**
     * @return Average of healthCount.
     */
    public double averageOfHealthCount() {
        double sum = 0;
        for (SpaceMarine e : marines.values()) {
            sum += e.getHeartCount();
        }
        if (sum == 0) return 0;
        return sum / marines.size();
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    @Override
    public String toString() {
        if (marines.isEmpty()) return "Коллекция пуста!";
        String info = "";
        int count = 0;
        for (SpaceMarine marine : marines.values()) {
            info += marine.toString();
            if (count != marines.size() - 1) info += "\n\n";
            count++;
        }
        return info;
    }
}
